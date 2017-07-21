package com.seven.designbox.designpatterns.patterns.proxy.download;
/*
 * Copyright 2016 Seven_Tang <yihongyuelan@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.seven.designbox.designpatterns.common.PLog;

import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Download one file once one time
 */
public class Downloader {
    private static final String TAG = "Downloader.java";
    private OkHttpClient mHttpClient;
    private String mUrl;
    private String mDownloadPath;
    private DownloaderListener mDownloaderListener;
    private Call mCall;

    public Downloader(OkHttpClient client,
                      String url,
                      String downloadPath,
                      DownloaderListener listener) {
        mHttpClient = client;
        mUrl = url;
        mDownloadPath = downloadPath;
        mDownloaderListener = listener;
    }

    private void checkParams(String url, String downloadPath, DownloaderListener listener) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(downloadPath) || listener == null) {
            throw new IllegalArgumentException("Download information is not complete.");
        }
    }

    public synchronized void start() {
        checkParams(mUrl, mDownloadPath, mDownloaderListener);

        final File file = new File(mDownloadPath);
        final File cache = new File(mDownloadPath + ".tmp");
        Request request = new Request.Builder().tag(mUrl).url(mUrl).build();
        if (cache.exists()) {
            //https://www.ibm.com/developerworks/cn/java/joy-down/index.html
            request = new Request.Builder().tag(mUrl).url(mUrl)
                    .header("Range", "bytes=" + cache.length() + "-")
                    .build();
        }

        mCall = mHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                mDownloaderListener.onError("Download file failed(Connecting):" + e.getMessage());
                mDownloaderListener.onStop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mDownloaderListener.onStart();
                FileOutputStream fOut = null;
                InputStream in = null;
                try {
                    final long cacheLen;
                    int rCode = response.code();
                    PLog.i(TAG, "Downloader response, code:" + rCode);
                    if (rCode == 200) {
                        //Common download or net support range.
                        cacheLen = 0;
                        fOut = new FileOutputStream(cache, false);
                    } else if (rCode == 206) {
                        //206 Partial content
                        cacheLen = cache.length();
                        fOut = new FileOutputStream(cache, true);
                    } else {
                        mDownloaderListener.onError("Server response is:" + rCode);
                        return;
                    }
                    ResponseBody body = response.body();
                    final long total = body.contentLength() + cacheLen;
                    in = body.byteStream();
                    PLog.i(TAG, "Downloader contentLength, cacheLen:" + cacheLen + ", total:" + total);
                    StreamCopyUtils.copy(in, fOut, new StreamCopyUtils.Listener() {
                        @Override
                        public void onBytesCopied(long len) {
                            mDownloaderListener.onProgress(cacheLen + len, total);
                        }
                    });
                    if (cache.renameTo(file)) {
                        mDownloaderListener.onSuccess(mUrl, file);
                    } else {
                        mDownloaderListener.onError("Rename Cache Error!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    mDownloaderListener.onError("Download file failed(Downloading):" + e.getMessage());
                } finally {
                    try {
                        if (fOut != null) {
                            fOut.close();
                        }
                        if (in != null) {
                            in.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mDownloaderListener.onStop();
                    PLog.i(TAG, "Downloader->IOException,finally---------------Done!");
                }

            }
        });
    }

    public synchronized void stop() {
        if (mCall != null && !mCall.isCanceled()) {
            mCall.cancel();
        }
        mCall = null;
    }

    public synchronized boolean isStopped() {
        return mCall == null;
    }
}

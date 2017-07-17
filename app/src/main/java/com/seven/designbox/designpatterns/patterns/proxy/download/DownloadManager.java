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

import com.seven.designbox.designpatterns.patterns.proxy.bean.User;

import android.content.Context;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static com.seven.designbox.designpatterns.patterns.proxy.download.DownloaderState.ERROR;
import static com.seven.designbox.designpatterns.patterns.proxy.download.DownloaderState.IDLE;
import static com.seven.designbox.designpatterns.patterns.proxy.download.DownloaderState.PROCESSING;
import static com.seven.designbox.designpatterns.patterns.proxy.download.DownloaderState.START;
import static com.seven.designbox.designpatterns.patterns.proxy.download.DownloaderState.STOPPED;
import static com.seven.designbox.designpatterns.patterns.proxy.download.DownloaderState.SUCCESS;

public class DownloadManager {
    private static final byte[] lock = new byte[0];
    private static final int CONNECT_TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 60;
    private static final int READ_TIMEOUT = 60;
    private static final int STEP_PREPARE = 0x0001;
    private static final int STEP_URL = 0x0010;
    private static final int STEP_FILENAME = 0x0100;
    private static final int STEP_LISTENER = 0x1000;
    private HashMap<String, DownloadItem> mHashMap;
    private OkHttpClient mHttpClient;
    private DownloadItem mDownloadItem;
    private AdapterListener mAdapterListener;
    private User mUser;
    private Context mContext;
    private int mStep = 0;

    private class DownloadItem {
        String url;
        String fileName;
        DownloaderState state = IDLE;
        DownloaderListener listener;
        Downloader downloader;
    }

    private class AdapterListener implements DownloaderListener {
        @Override
        public void onStart() {
            setDownloaderState(START);
            mDownloadItem.listener.onStart();
        }

        @Override
        public void onProgress(long current, long total) {
            setDownloaderState(PROCESSING);
            mDownloadItem.listener.onProgress(current, total);
        }

        @Override
        public void onError(String err) {
            setDownloaderState(ERROR);
            mDownloadItem.listener.onError(err);
            mHashMap.remove(mDownloadItem.url);
        }

        @Override
        public void onSuccess(String url, File file) {
            setDownloaderState(SUCCESS);
            mDownloadItem.listener.onSuccess(url, file);
            mHashMap.remove(mDownloadItem.url);
        }

        @Override
        public void onStop() {
            setDownloaderState(STOPPED);
            mDownloadItem.listener.onStop();
            mHashMap.remove(mDownloadItem.url);
        }
    }

    public DownloadManager(Context context) {
        mContext = context.getApplicationContext();
        mHashMap = new HashMap<>();
        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    public DownloadManager prepare(User user) {
        mUser = user;
        mDownloadItem = new DownloadItem();
        mAdapterListener = new AdapterListener();
        mStep |= STEP_PREPARE;
        return this;
    }

    public DownloadManager url(String url) {
        mDownloadItem.url = url;
        mStep |= STEP_URL;
        return this;
    }

    public DownloadManager fileName(String name) {
        mDownloadItem.fileName = mContext.getFilesDir().getAbsolutePath() + name;
        mStep |= STEP_FILENAME;
        return this;
    }

    public DownloadManager listener(DownloaderListener listener) {
        mDownloadItem.listener = listener;
        mStep |= STEP_LISTENER;
        return this;
    }

    public void startDownload() {
        if (mStep != 0x1111) {
            throw new IllegalArgumentException("Please call prepare() url() listener()");
        }
        mStep = 0;

        if (mHashMap.keySet().contains(mDownloadItem.url)) {
            //This url has being downloaded
            return;
        }

        if (!mUser.isVip()) {
            mAdapterListener.onError("NOT VIP");
            return;
        }

        mDownloadItem.downloader = new Downloader(
                mHttpClient,
                mDownloadItem.url,
                mDownloadItem.fileName,
                mAdapterListener);

        mHashMap.put(mDownloadItem.url, mDownloadItem);

        mDownloadItem.downloader.start();
    }

    private void setDownloaderState(DownloaderState state) {
        synchronized (lock) {
            mDownloadItem.state = state;
        }
    }

    public DownloaderState getDownloaderState(String url) {
        synchronized (lock) {
            DownloadItem item = mHashMap.get(url);
            if (item != null) {
                return item.state;
            }
            return null;
        }
    }

}

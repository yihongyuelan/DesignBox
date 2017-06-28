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

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class DownloadManager {
    private static final int CONNECT_TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 60;
    private static final int READ_TIMEOUT = 60;
    private static final int STEP_PREPARE = 0x0001;
    private static final int STEP_URL = 0x0010;
    private static final int STEP_FILENAME = 0x0100;
    private static final int STEP_LISTENER = 0x1000;
    private static DownloadManager sDownloadManager;
    private HashMap<String, Downloader> mHashMap;
    private OkHttpClient mHttpClient;
    private DownloadItem mDownloadItem;
    private AdapterListener mAdapterListener;
    private int mStep = 0;

    private class DownloadItem {
        String url;
        String fileName;
        DownloaderListener listener;
    }

    private class AdapterListener implements DownloaderListener {
        @Override
        public void onStart() {
            mDownloadItem.listener.onStart();
        }

        @Override
        public void onProgress(long current, long total) {
            mDownloadItem.listener.onProgress(current, total);
        }

        @Override
        public void onError(String err) {
            mDownloadItem.listener.onError(err);
            mHashMap.remove(mDownloadItem.url);
        }

        @Override
        public void onSuccess(String url, File file) {
            mDownloadItem.listener.onSuccess(url, file);
            mHashMap.remove(mDownloadItem.url);
        }

        @Override
        public void onStop() {
            mDownloadItem.listener.onStop();
            mHashMap.remove(mDownloadItem.url);
        }
    }


//    public static DownloadManager getInstance() {
//        if (sDownloadManager == null) {
//            synchronized (DownloadManager.class) {
//                if (sDownloadManager == null) {
//                    sDownloadManager = new DownloadManager();
//                }
//            }
//        }
//        return sDownloadManager;
//    }

    private void init() {
        if (sDownloadManager == null) {
            synchronized (DownloadManager.class) {
                if (sDownloadManager == null) {
                    sDownloadManager = new DownloadManager();
                }
            }
        }
    }

    private DownloadManager() {
        mHashMap = new HashMap<>();
        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    public DownloadManager prepare() {
        init();
        mDownloadItem = new DownloadItem();
        mAdapterListener = new AdapterListener();
        mStep |= STEP_PREPARE;
        return sDownloadManager;
    }

    public DownloadManager url(String url) {
        mDownloadItem.url = url;
        mStep |= STEP_URL;
        return sDownloadManager;
    }

    public DownloadManager fileName(String name) {
        mDownloadItem.fileName = name;
        mStep |= STEP_FILENAME;
        return sDownloadManager;
    }

    public DownloadManager listener(DownloaderListener listener) {
        mDownloadItem.listener = listener;
        mStep |= STEP_LISTENER;
        return sDownloadManager;
    }

    public void startDownload() {
        if (mStep != 0x1111) {
            throw new IllegalArgumentException("Please call prepare() url() listener()");
        }

        if (mHashMap.keySet().contains(mDownloadItem.url)) {
            //This url is being downloaded
            return;
        }

        Downloader downloader = new Downloader(
                mHttpClient,
                mDownloadItem.url,
                mDownloadItem.fileName,
                mAdapterListener);

        mHashMap.put(mDownloadItem.url, downloader);

        downloader.start();
    }

}

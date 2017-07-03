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

import android.support.annotation.NonNull;

import java.io.File;

import static com.google.common.base.Preconditions.checkNotNull;

public class DownloadPresenter implements DownloadContract.Presenter {

    private final DownloadContract.View mDownloadView;
    private final DownloadManager mDownloadManager;

    private DownloaderListener mDownloaderListener = new DownloaderListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onProgress(long current, long total) {

        }

        @Override
        public void onError(String err) {

        }

        @Override
        public void onSuccess(String url, File file) {

        }

        @Override
        public void onStop() {

        }
    };

    public DownloadPresenter(@NonNull DownloadManager manager, @NonNull DownloadContract.View downloadView) {
        mDownloadManager = checkNotNull(manager, "DownloadManager cannot be null");
        mDownloadView = checkNotNull(downloadView, "DownloadView cannot be null");
        mDownloadView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void startDownload() {
        mDownloadManager.prepare()
                .url("http://ovh.net/files/100Mio.dat")
                .fileName("test.dat")
                .listener(mDownloaderListener)
                .startDownload();
        mDownloadView.onDownloadProgress();
    }

}

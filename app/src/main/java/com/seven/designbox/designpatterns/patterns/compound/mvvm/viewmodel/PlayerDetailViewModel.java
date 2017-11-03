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
package com.seven.designbox.designpatterns.patterns.compound.mvvm.viewmodel;

import com.seven.designbox.R;
import com.seven.designbox.designpatterns.patterns.compound.model.PlayerManager;
import com.seven.designbox.designpatterns.patterns.compound.model.SongInfo;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;

public class PlayerDetailViewModel extends BaseObservable implements PlayerManager.PlayerManagerTransfer{
    public final ObservableField<String> mCurLyrics = new ObservableField<>();
    public final ObservableField<String> mCurSinger = new ObservableField<>();
    public final ObservableField<String> mCurName = new ObservableField<>();

    private final PlayerManager mPlayerManager;

    public PlayerDetailViewModel(Context context, PlayerManager manager) {
        mPlayerManager = manager;
        mPlayerManager.addTransferListener(this);
        updateUI(context.getString(R.string.compound_pattern_name),
                context.getString(R.string.compound_pattern_singer),
                context.getString(R.string.compound_pattern_lyrics));
    }

    public void onLastBtnClicked() {
        if (mPlayerManager != null) {
            mPlayerManager.lastSong();
        }
    }

    public void onNextBtnClicked() {
        if (mPlayerManager != null) {
            mPlayerManager.nextSong();
        }
    }

    private void updateUI(String name, String singer, String lyrics) {
        mCurName.set(name);
        mCurSinger.set(singer);
        mCurLyrics.set(lyrics);
    }

    @Override
    public void onSongChanged(SongInfo info) {
        updateUI(info.getName(), info.getSinger(), info.getLyrics());
    }
}

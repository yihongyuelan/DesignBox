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
package com.seven.designbox.designpatterns.patterns.compound.model;

import com.seven.designbox.R;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private List<PlayerManagerListener> mListeners;
    private String[] mSingerNames, mSongNames, mSongLyrics;

    public PlayerManager(Context context) {
        mListeners = new ArrayList<>();
        mSingerNames = context.getResources().getStringArray(R.array.singer_names);
        mSongNames = context.getResources().getStringArray(R.array.song_names);
        mSongLyrics = context.getResources().getStringArray(R.array.song_lyrics);
    }

    public interface PlayerManagerListener {
        void onSongChanged();
    }

    public void registerListener(PlayerManagerListener listener) {
        mListeners.add(listener);
    }

    public void unregisterListener(PlayerManagerListener listener) {
        mListeners.remove(listener);
    }

    public void lastSong() {
        for (PlayerManagerListener listener : mListeners) {
            listener.onSongChanged();
        }
    }

    public void nextSong() {
        for (PlayerManagerListener listener : mListeners) {
            listener.onSongChanged();
        }
    }

    public DetailsInfo getDetailsInfo() {
        return null;
    }
}

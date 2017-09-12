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
package com.seven.designbox.designpatterns.patterns.compound.controller;

import com.seven.designbox.designpatterns.patterns.compound.model.SongInfo;
import com.seven.designbox.designpatterns.patterns.compound.model.PlayerManager;
import com.seven.designbox.designpatterns.patterns.compound.view.PlayerDetailsViewMvc;
import com.seven.designbox.designpatterns.patterns.compound.view.PlayerDetailsViewMvcImpl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Controller
public class PlayerDetailFragment extends Fragment implements
        PlayerDetailsViewMvc.DetailsViewListener,
        PlayerManager.PlayerManagerListener {

    private PlayerDetailsViewMvc mViewMvc;
    private PlayerManager mPlayerManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPlayerManager = new PlayerManager(getContext());
        mViewMvc = new PlayerDetailsViewMvcImpl(inflater, container);
        mViewMvc.setListener(this);
        return mViewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPlayerManager.registerListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPlayerManager.unregisterListener(this);
    }

    @Override
    public void onLastBtnClicked() {
        mPlayerManager.lastSong();
    }

    @Override
    public void onNextBtnClicked() {
        mPlayerManager.nextSong();
    }

    @Override
    public SongInfo getSongInfo() {
        return mPlayerManager.getSongsInfo();
    }

    @Override
    public void onSongChanged() {
        mViewMvc.updateDetails();
    }
}

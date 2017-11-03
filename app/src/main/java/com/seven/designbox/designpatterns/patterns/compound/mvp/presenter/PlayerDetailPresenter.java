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
package com.seven.designbox.designpatterns.patterns.compound.mvp.presenter;

import com.seven.designbox.designpatterns.patterns.compound.model.PlayerManager;
import com.seven.designbox.designpatterns.patterns.compound.model.SongInfo;
import com.seven.designbox.designpatterns.patterns.compound.mvp.view.PlayerDetailContract;

public class PlayerDetailPresenter implements
        PlayerDetailContract.Presenter,
        PlayerManager.PlayerManagerListener {
    private PlayerManager mPlayerManager;
    private PlayerDetailContract.View mPlayerDetailView;

    public PlayerDetailPresenter(PlayerManager manager,
                                 PlayerDetailContract.View detailView) {
        mPlayerManager = manager;
        mPlayerManager.registerListener(this);
        mPlayerDetailView = detailView;
        mPlayerDetailView.setPresenter(this);
    }

    @Override
    public void stop() {
        if (mPlayerManager != null) {
            mPlayerManager.unregisterListener(this);
        }
    }

    @Override
    public void onLastBtnClicked() {
        if (mPlayerManager != null) {
            mPlayerManager.lastSong();
        }
    }

    @Override
    public void onNextBtnClicked() {
        if (mPlayerManager != null) {
            mPlayerManager.nextSong();
        }
    }

    @Override
    public void onSongChanged() {
        mPlayerManager.getSongsInfo(new PlayerManager.PlayerManagerCallback() {
            @Override
            public void onSongUpdated(SongInfo info) {
                mPlayerDetailView.updateDetails(info);
            }
        });
    }
}

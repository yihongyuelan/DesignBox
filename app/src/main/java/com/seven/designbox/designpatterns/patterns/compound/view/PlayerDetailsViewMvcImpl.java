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
package com.seven.designbox.designpatterns.patterns.compound.view;

import com.seven.designbox.R;
import com.seven.designbox.designpatterns.patterns.compound.model.SongInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PlayerDetailsViewMvcImpl implements PlayerDetailsViewMvc {
    private View mRootView;
    private DetailsViewListener mListener;
    private ButtonClickListener mClickListener;

    private TextView mNameTv, mSingerTv, mLyricsTv;
    private Button mLastBtn, mNextBtn;

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (mListener == null)
                return;
            switch (v.getId()) {
                case R.id.btn_last:
                    mListener.onLastBtnClicked();
                    break;
                case R.id.btn_next:
                    mListener.onNextBtnClicked();
                    break;
                default:
                    break;
            }
        }
    }

    public PlayerDetailsViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.player_details_mvc, container, false);
        initViews();
        initListeners();
    }

    private void initViews() {
        mNameTv = findViewById(R.id.tv_name);
        mSingerTv = findViewById(R.id.tv_singer);
        mLyricsTv = findViewById(R.id.tv_lyrics);
        mLastBtn = findViewById(R.id.btn_last);
        mNextBtn = findViewById(R.id.btn_next);
    }

    private void initListeners() {
        if (mClickListener == null) {
            mClickListener = new ButtonClickListener();
        }
        mLastBtn.setOnClickListener(mClickListener);
        mNextBtn.setOnClickListener(mClickListener);
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    public void setListener(DetailsViewListener listener) {
        mListener = listener;
    }

    @Override
    public void updateDetails() {
        SongInfo info = mListener.getSongInfo();
    }

    @SuppressWarnings("unchecked")
    private <T extends View> T findViewById(int id) {
        return (T) mRootView.findViewById(id);
    }

}

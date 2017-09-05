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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PlayerDetailsViewMvcImpl implements PlayerDetailsViewMvc {
    private View mRootView;
    private ShowDetailsViewListener mListener;
    private boolean mMoreInfoSupported = true;
    private ButtonClickListener mClickListener;

    private TextView mNameTv;
    private TextView mSingerTv;
    private TextView mLyricsTv;
    private Button mMoreInfoBtn, mLastBtn, mPlayBtn, mNextBtn;

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_more_info:
                    if (mListener != null) {
                        mListener.onMoreInfoClick();
                    }
                    break;
                case R.id.btn_last:
                    break;
                case R.id.btn_play:
                    break;
                case R.id.btn_next:
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
        mMoreInfoBtn = findViewById(R.id.btn_more_info);
        mLastBtn = findViewById(R.id.btn_last);
        mPlayBtn = findViewById(R.id.btn_play);
        mNextBtn = findViewById(R.id.btn_next);
    }

    private void initListeners() {
        if (mClickListener == null) {
            mClickListener = new ButtonClickListener();
        }
        mMoreInfoBtn.setOnClickListener(mClickListener);
        mLastBtn.setOnClickListener(mClickListener);
        mPlayBtn.setOnClickListener(mClickListener);
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
    public void moreInfoNotSupported() {
        mMoreInfoSupported = false;
    }

    @Override
    public void setListener(ShowDetailsViewListener listener) {
        mListener = listener;
    }

    @SuppressWarnings("unchecked")
    private <T extends View> T findViewById(int id) {
        try {
            return (T) mRootView.findViewById(id);
        } catch (ClassCastException e) {
            throw e;
        }
    }
}

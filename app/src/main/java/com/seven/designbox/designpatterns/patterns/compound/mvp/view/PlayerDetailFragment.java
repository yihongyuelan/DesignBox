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
package com.seven.designbox.designpatterns.patterns.compound.mvp.view;

import com.seven.designbox.R;
import com.seven.designbox.designpatterns.patterns.compound.model.SongInfo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class PlayerDetailFragment extends Fragment implements PlayerDetailContract.View {

    private View mRootView;
    private TextView mNameTv, mSingerTv, mLyricsTv;
    private Button mLastBtn, mNextBtn;
    private WeakHandler mWeakHandler;
    private ButtonClickListener mClickListener;
    private PlayerDetailContract.Presenter mPresenter;

    private static class WeakHandler extends Handler {
        private WeakReference<PlayerDetailFragment> mWeakReference;

        public WeakHandler(PlayerDetailFragment fragment) {
            mWeakReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.obj != null) {
                mWeakReference.get().updateUI((SongInfo) msg.obj);
            }
        }
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (mPresenter == null)
                return;
            switch (v.getId()) {
                case R.id.btn_last:
                    mPresenter.onLastBtnClicked();
                    break;
                case R.id.btn_next:
                    mPresenter.onNextBtnClicked();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void setPresenter(PlayerDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void updateDetails(SongInfo info) {
        mWeakHandler.sendMessage(mWeakHandler.obtainMessage(0, info));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.player_details_mvc, container, false);
        mWeakHandler = new WeakHandler(this);
        initViews();
        initListeners();
        return mRootView;
    }

    private void initViews() {
        mNameTv = findViewById(R.id.tv_name);
        mSingerTv = findViewById(R.id.tv_singer);
        mLyricsTv = findViewById(R.id.tv_lyrics);
        mLyricsTv.setMovementMethod(new ScrollingMovementMethod());
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

    @SuppressWarnings("unchecked")
    private <T extends View> T findViewById(int id) {
        return (T) mRootView.findViewById(id);
    }

    private void updateUI(SongInfo info) {
        if (info != null) {
            mSingerTv.setText(info.getSinger());
            mNameTv.setText(info.getName());
            mLyricsTv.setText(info.getLyrics());
        }
    }
}

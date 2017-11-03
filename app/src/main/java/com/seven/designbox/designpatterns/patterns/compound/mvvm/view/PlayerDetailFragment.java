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
package com.seven.designbox.designpatterns.patterns.compound.mvvm.view;

import com.seven.designbox.R;
import com.seven.designbox.databinding.PlayerDetailsMvvmBinding;
import com.seven.designbox.designpatterns.patterns.compound.model.PlayerManager;
import com.seven.designbox.designpatterns.patterns.compound.mvvm.viewmodel.PlayerDetailViewModel;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlayerDetailFragment extends Fragment {
    private PlayerDetailViewModel mDetailViewModel;
    private PlayerDetailsMvvmBinding mViewDataBinding;

    public static PlayerDetailFragment newInstance() {
        return new PlayerDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.player_details_mvvm, container, false);
        mViewDataBinding.tvLyrics.setMovementMethod(new ScrollingMovementMethod());
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewDataBinding.setViewmodel(mDetailViewModel);
    }

    public void setViewModel(PlayerDetailViewModel viewModel) {
        mDetailViewModel = viewModel;
    }
}

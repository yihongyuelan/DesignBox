package com.seven.designbox.designpatterns.patterns.compound;


import com.seven.designbox.R;
import com.seven.designbox.designpatterns.patterns.compound.model.PlayerManager;
import com.seven.designbox.designpatterns.patterns.compound.mvvm.view.PlayerDetailFragment;
import com.seven.designbox.designpatterns.patterns.compound.mvvm.viewmodel.PlayerDetailViewModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CompoundDemoFragmentMvvm extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.patterns_proxy, null, false);
        PlayerDetailFragment fragment = PlayerDetailFragment.newInstance();
        PlayerDetailViewModel viewModel = new PlayerDetailViewModel(getContext(), new PlayerManager(getContext()));
        fragment.setViewModel(viewModel);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.download_fg_container, fragment)
                .commit();
        return view;
    }

}

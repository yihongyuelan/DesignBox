package com.seven.designbox.designpatterns.patterns.compound;


import com.seven.designbox.R;
import com.seven.designbox.designpatterns.patterns.compound.model.PlayerManager;
import com.seven.designbox.designpatterns.patterns.compound.mvp.presenter.PlayerDetailPresenter;
import com.seven.designbox.designpatterns.patterns.compound.mvp.view.PlayerDetailFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CompoundDemoFragmentMvp extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.patterns_proxy, null, false);
        PlayerManager manager = new PlayerManager(getContext());
        PlayerDetailFragment fragment = new PlayerDetailFragment();
        new PlayerDetailPresenter(manager, fragment);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.download_fg_container, fragment)
                .commit();
        return view;
    }

}

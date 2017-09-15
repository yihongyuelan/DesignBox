package com.seven.designbox.designpatterns.patterns.compound;


import com.seven.designbox.R;
import com.seven.designbox.designpatterns.patterns.compound.controller.PlayerDetailFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CompoundDemoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.patterns_proxy, null, false);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.download_fg_container, new PlayerDetailFragment())
                .commit();
        return view;
    }

}

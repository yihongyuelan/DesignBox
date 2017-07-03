package com.seven.designbox.designpatterns.patterns.proxy;


import com.seven.designbox.R;
import com.seven.designbox.designpatterns.patterns.proxy.download.DownloadFragment;
import com.seven.designbox.designpatterns.patterns.proxy.download.DownloadManager;
import com.seven.designbox.designpatterns.patterns.proxy.download.DownloadPresenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProxyDemoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.patterns_proxy, null, false);
        //Module
        DownloadManager manager = new DownloadManager(getContext());
        //View
        DownloadFragment fragment = DownloadFragment.newInstance("Steven", false);
        //Presenter
        DownloadPresenter presenter = new DownloadPresenter(manager, fragment);

        getChildFragmentManager().beginTransaction()
                .replace(R.id.download_fg_container, fragment)
                .commit();
        return view;
    }

}

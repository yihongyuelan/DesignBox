package com.seven.designbox.designpatterns.patterns.proxy;


import com.seven.designbox.R;
import com.seven.designbox.designpatterns.patterns.proxy.download.DownloadFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProxyDemoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.patterns_proxy, null, false);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.download_fg_container, new DownloadFragment())
                .commit();
        return view;
    }

}

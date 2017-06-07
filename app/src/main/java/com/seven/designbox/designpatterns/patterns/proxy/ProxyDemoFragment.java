package com.seven.designbox.designpatterns.patterns.proxy;


import com.seven.designbox.R;

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

    @BindView(R.id.download_btn)
    Button mDownloadBtn;
    @BindView(R.id.download_des)
    TextView mTextView;
    private Unbinder mUnbinder;
    private boolean isDownloading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.patterns_proxy, null, false);
        mUnbinder = ButterKnife.bind(this, view);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @OnClick(R.id.download_btn)
    public void onDownloadClick() {
        if (isDownloading) {

        } else {
            //Just call the downloader directly, do not add dialog here
            AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
            dialog.setCancelable(false);
            dialog.setTitle("Suggest");
            dialog.setMessage("You are not currently VIP users, whether to try VIP service?");
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    isDownloading = false;
                }
            });
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    isDownloading = true;
                    //Change permission to VIP
                }
            });
            dialog.show();
        }
    }

}

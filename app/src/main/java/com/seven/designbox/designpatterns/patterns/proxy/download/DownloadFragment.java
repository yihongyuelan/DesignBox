package com.seven.designbox.designpatterns.patterns.proxy.download; /*
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

import com.seven.designbox.R;
import com.seven.designbox.designpatterns.patterns.proxy.bean.User;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

public class DownloadFragment extends Fragment implements DownloadContract.View {

    @BindView(R.id.download_btn)
    Button mDownloadBtn;

    private static final String ARGUMENT_NAME = "NAME";
    private static final String ARGUMENT_VIP = "VIP";
    private Unbinder mUnbinder;
    private DownloadContract.Presenter mPresenter;
    @NonNull
    private User mUser;

    public static DownloadFragment newInstance(@NonNull String name, boolean isVip) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_NAME, name);
        arguments.putBoolean(ARGUMENT_VIP, isVip);
        DownloadFragment fragment = new DownloadFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mUser = new User(bundle.getString(ARGUMENT_NAME), bundle.getBoolean(ARGUMENT_VIP, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.download_fragment, null, false);
        mUnbinder = ButterKnife.bind(this, root);
        ((TextView) root.findViewById(R.id.download_des)).setMovementMethod(new ScrollingMovementMethod());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    @OnClick(R.id.download_btn)
    public void onDownloadClick() {
        mPresenter.startDownload();
    }

    @Override
    public void setPresenter(@NonNull DownloadContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onDownloadStart() {
        updateDownloadingProgress("0%");
    }

    @Override
    public void onDownloadProgress(long current, long total) {
        updateDownloadingProgress(getPercentage(current, total));
    }

    @Override
    public void onDownloadSuccess() {
        restoreDownload();
    }

    @Override
    public void onDownloadStop() {
        restoreDownload();
    }

    @Override
    public void onDownloadError(String err) {
        if (err.equals("NOT VIP")) {
            tryVipService();
        }
        restoreDownload();
    }

    @Override
    public User getUser() {
        return mUser;
    }

    private void tryVipService() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setCancelable(false);
        dialog.setTitle("Suggest");
        dialog.setMessage("You are not currently VIP users, whether to try VIP service?");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mUser.tryVipService();
                mPresenter.startDownload();
                dialog.dismiss();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private String getPercentage(long current, long total) {
        return new DecimalFormat("0.0").format(100f * current / total);
    }

    private void updateDownloadingProgress(final String progress) {
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDownloadBtn.setText(progress);
            }
        });
    }

    private void restoreDownload() {
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDownloadBtn.setText("Click to download");
            }
        });
    }
}

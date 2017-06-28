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
import static com.google.common.base.Preconditions.checkNotNull;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

public class DownloadFragment extends Fragment implements DownloadContract.View {

    @BindView(R.id.download_btn)
    Button mDownloadBtn;

    @NonNull
    private static final String ARGUMENT_NAME = "NAME";
    private static final String ARGUMENT_VIP = "VIP";
    private Unbinder mUnbinder;
    private DownloadContract.Presenter mPresenter;

    public static DownloadFragment newInstance(@NonNull String name, boolean isVip) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_NAME, name);
        arguments.putBoolean(ARGUMENT_VIP, isVip);
        DownloadFragment fragment = new DownloadFragment();
        fragment.setArguments(arguments);
        return fragment;
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

    }

    @Override
    public void setPresenter(@NonNull DownloadContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

//    //Just call the downloader directly, do not add dialog here
//    AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
//        dialog.setCancelable(false);
//        dialog.setTitle("Suggest");
//        dialog.setMessage("You are not currently VIP users, whether to try VIP service?");
//        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            isDownloading = false;
//        }
//    });
//        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            isDownloading = true;
//            //Change permission to VIP
//        }
//    });
//        dialog.show();
}

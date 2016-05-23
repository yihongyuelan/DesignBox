package com.seven.demobox.designpatterns.patterns.factory;

import com.seven.demobox.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SampleFragment extends Fragment {
    private String title;
    private int page;
    public static SampleFragment newInstance(int page, String title) {
        final SampleFragment fragment = new SampleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("someInt", page);
        bundle.putString("someTitle", title);
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt");
        title = getArguments().getString("someTitle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.factory_fragment, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.page_tv);
        tvLabel.setText(page + " -- " + title);
        return view;
    }
}

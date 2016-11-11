package com.seven.designbox.designpatterns.patterns.composite;


import com.seven.designbox.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class CompositeDemoFragment extends Fragment {
    private ExpandableListView mListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patterns_composite_container, container, false);
        mListView = (ExpandableListView) view.findViewById(R.id.expanded_list);
        ExpandableListAdapter adapter = new ExpandableListAdapter(getActivity());
        mListView.setAdapter(adapter);
        return view;
    }
}

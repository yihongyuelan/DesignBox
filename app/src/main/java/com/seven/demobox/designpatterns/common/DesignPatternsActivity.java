package com.seven.demobox.designpatterns.common;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class DesignPatternsActivity extends LauncherActivity {

    private String[] patternNames = new String[PatternsConstant.PATTERN_LISTS.length];
    private Class[] patternClasses = new Class[PatternsConstant.PATTERN_LISTS.length];

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        for (int i = 0; i < PatternsConstant.PATTERN_LISTS.length; i++) {
            patternNames[i] = getResources().getString(PatternsConstant.PATTERN_LISTS[i].patterTitleId);
            patternClasses[i] = PatternsConstant.PATTERN_LISTS[i].patternActivityClass;
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, patternNames);
        setListAdapter(adapter);
    }

    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(this, patternClasses[position]);
    }
}

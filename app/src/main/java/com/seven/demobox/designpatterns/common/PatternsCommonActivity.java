package com.seven.demobox.designpatterns.common;

import com.seven.demobox.R;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class PatternsCommonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LinearLayout wrapper = new LinearLayout(this);
        View view = getLayoutInflater().inflate(layoutResID, wrapper, true);
        configureToolbar(view);
        super.setContentView(view);
    }

    protected boolean useToolbar() {
        return true;
    }

    private void configureToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            if (useToolbar()) {
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            } else {
                toolbar.setVisibility(View.GONE);
            }
        }
    }
}

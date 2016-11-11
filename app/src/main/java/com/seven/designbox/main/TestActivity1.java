package com.seven.designbox.main;

import android.content.Context;
import android.os.Bundle;

import com.seven.designbox.R;

public class TestActivity1 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMyTitle(this, "TestActivity1");
        setContentView(R.layout.test);
    }

    private void setMyTitle(Context context, String title) {
        setTitle(title);
    }
}

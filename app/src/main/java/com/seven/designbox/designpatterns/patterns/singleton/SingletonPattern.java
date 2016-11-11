package com.seven.designbox.designpatterns.patterns.singleton;

import com.seven.designbox.R;
import com.seven.designbox.designpatterns.common.PatternsCommonActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class SingletonPattern extends PatternsCommonActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patterns_singleton);
        setTitle(R.string.singleton_pattern_title);
    }
}

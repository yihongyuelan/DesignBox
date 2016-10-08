package com.seven.demobox.designpatterns.patterns.template;

import com.seven.demobox.R;
import com.seven.demobox.designpatterns.common.PatternsCommonActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class TemplatePattern extends PatternsCommonActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patterns_template);
        setTitle(R.string.template_pattern_title);
    }
}

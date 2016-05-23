package com.seven.demobox.main;

import com.seven.demobox.R;
import com.seven.demobox.designpatterns.common.DesignPatternsActivity;

public final class DemoDetailsList {

    /** This class should not be instantiated. */
    private DemoDetailsList() {
    }

    public static final DemoDetails[] DEMOS = {
            new DemoDetails(R.string.test_title1,
                    R.string.test_description1,
                    TestActivity1.class),
            new DemoDetails(R.string.design_pattern_title,
                    R.string.design_pattern_description,
                    DesignPatternsActivity.class),
    };
}

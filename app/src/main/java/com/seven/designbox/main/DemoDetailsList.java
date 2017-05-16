package com.seven.designbox.main;

import com.seven.designbox.R;
import com.seven.designbox.designpatterns.common.DesignPatternsActivity;

public final class DemoDetailsList {

    /** This class should not be instantiated. */
    private DemoDetailsList() {
    }

    public static final DemoDetails[] DEMOS = {
            createDetail(R.string.design_pattern_title,
                    R.string.design_pattern_description,
                    DesignPatternsActivity.class),
    };

    private static DemoDetails createDetail(int titleId, int desId, Class detail) {
        return new DemoDetails(titleId, desId, detail);
    }
}

package com.seven.designbox.designpatterns.patterns.decorator.role;

import com.seven.designbox.R;
import com.seven.designbox.designpatterns.common.GlobalApp;

public class Warlock extends Role {

    @Override
    public String description() {
        return GlobalApp.sContext.getString(R.string.warlock);
    }
}

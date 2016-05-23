package com.seven.demobox.designpatterns.patterns.decorator.role;

import com.seven.demobox.R;
import com.seven.demobox.designpatterns.common.GlobalApp;
import com.seven.demobox.designpatterns.patterns.decorator.role.Role;

public class Hunter extends Role {

    @Override
    public String description() {
        return GlobalApp.sContext.getString(R.string.hunter);
    }
}

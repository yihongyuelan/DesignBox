package com.seven.demobox.designpatterns.patterns.decorator.gender;

import com.seven.demobox.R;
import com.seven.demobox.designpatterns.common.GlobalApp;
import com.seven.demobox.designpatterns.patterns.decorator.role.Role;
import com.seven.demobox.designpatterns.patterns.decorator.role.RoleDecorator;

public class Female extends RoleDecorator {
    public Female(Role role) {
        super(role);
    }

    @Override
    public String description() {
        return GlobalApp.sContext.getString(R.string.female) + " " + super.description();
    }
}

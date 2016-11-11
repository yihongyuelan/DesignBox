package com.seven.designbox.designpatterns.patterns.decorator.gender;

import com.seven.designbox.R;
import com.seven.designbox.designpatterns.common.GlobalApp;
import com.seven.designbox.designpatterns.patterns.decorator.role.Role;
import com.seven.designbox.designpatterns.patterns.decorator.role.RoleDecorator;

public class Female extends RoleDecorator {
    public Female(Role role) {
        super(role);
    }

    @Override
    public String description() {
        return GlobalApp.sContext.getString(R.string.female) + " " + super.description();
    }
}

package com.seven.designbox.designpatterns.patterns.decorator.gender;

import com.seven.designbox.R;
import com.seven.designbox.designpatterns.common.GlobalApp;
import com.seven.designbox.designpatterns.patterns.decorator.role.Role;
import com.seven.designbox.designpatterns.patterns.decorator.role.RoleDecorator;

public class Male extends RoleDecorator {
    public Male(Role role) {
        super(role);
    }

    @Override
    public String description() {
        return GlobalApp.sContext.getString(R.string.male) + " " + super.description();
    }
}

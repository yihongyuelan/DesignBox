package com.seven.demobox.designpatterns.patterns.decorator.weapon;

import com.seven.demobox.R;
import com.seven.demobox.designpatterns.common.GlobalApp;
import com.seven.demobox.designpatterns.patterns.decorator.role.Role;
import com.seven.demobox.designpatterns.patterns.decorator.role.RoleDecorator;

public class Wand extends RoleDecorator {
    public Wand(Role role) {
        super(role);
    }

    @Override
    public String description() {
        return super.description() + " using " + GlobalApp.sContext.getString(R.string.wand);
    }
}

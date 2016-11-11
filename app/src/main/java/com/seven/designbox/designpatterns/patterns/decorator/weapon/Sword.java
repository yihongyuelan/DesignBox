package com.seven.designbox.designpatterns.patterns.decorator.weapon;

import com.seven.designbox.R;
import com.seven.designbox.designpatterns.common.GlobalApp;
import com.seven.designbox.designpatterns.patterns.decorator.role.Role;
import com.seven.designbox.designpatterns.patterns.decorator.role.RoleDecorator;

public class Sword extends RoleDecorator{
    public Sword(Role role) {
        super(role);
    }

    @Override
    public String description() {
        return super.description()+" using " + GlobalApp.sContext.getString(R.string.sword);
    }
}

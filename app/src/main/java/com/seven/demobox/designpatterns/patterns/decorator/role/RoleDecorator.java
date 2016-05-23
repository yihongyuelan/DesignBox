package com.seven.demobox.designpatterns.patterns.decorator.role;

public class RoleDecorator extends Role {

    private Role role;

    public RoleDecorator(Role role) {
        this.role = role;
    }

    @Override
    public String description() {
        return role.description();
    }
}

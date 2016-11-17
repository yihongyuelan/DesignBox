package com.seven.designbox.designpatterns.patterns.composite;
 /*
 * Copyright 2016 Seven_Tang <yihongyuelan@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.ArrayList;
import java.util.List;

public class Menu extends MenuComponent {
    private List<MenuComponent> mComponents = new ArrayList<>();
    private String name;
    private String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        mComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        mComponents.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return mComponents.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getCounts() {
        if (mComponents != null) {
            return mComponents.size();
        }
        return 0;
    }

    protected void addItem(String name,
                           String description,
                           boolean vegetarian,
                           double price) {
        MenuItem item = new MenuItem(name, description, vegetarian, price);
        //Call super method
        add(item);
    }

}

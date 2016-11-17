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


public class Waitress {
    private MenuComponent allMenus;

    public Waitress() {
        MenuComponent pancakeMenu = new MenuPancakeHouse("PANCAKE HOUSE MENU", "Breakfast");
        MenuComponent dinerMenu = new MenuDiner("DINER MENU", "Lunch");
        MenuComponent cafeMenu = new MenuCafe("CAFE MENU", "Dinner");
        MenuComponent dessertMenu = new Menu("DESSERT MENU", "Dessert");
        dessertMenu.add(new MenuItem(
                "Apple Pie",
                "Apple Pie with a flakey crust",
                true,
                3.89));

        dinerMenu.add(cafeMenu);

        allMenus = new Menu("ALL MENUS", "All menus combined");
        allMenus.add(pancakeMenu);
        allMenus.add(dinerMenu);
        allMenus.add(dessertMenu);
    }

    public MenuComponent getAllMenus() {
        return allMenus;
    }

    public int getAllMenusCounts() {
        return allMenus.getCounts();
    }
}

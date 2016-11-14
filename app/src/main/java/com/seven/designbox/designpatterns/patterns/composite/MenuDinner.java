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

public class MenuDinner extends Menu {

    public MenuDinner(String name, String description) {
        super(name, description);

        addItem("Vegetarian BLT",
                "Bacon with lettuce",
                true,
                2.99);
        addItem("BLT",
                "Bacon with lettuce & tomato",
                false,
                2.99);
        addItem("Soup of the day",
                "Soup of the day",
                false,
                3.29);
        addItem("Hotdog",
                "A hot dog, with saurkraut",
                false,
                3.05);
    }
}

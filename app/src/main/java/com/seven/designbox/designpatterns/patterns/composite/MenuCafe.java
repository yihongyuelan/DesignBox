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

public class MenuCafe extends Menu {

    public MenuCafe(String name, String description) {
        super(name, description);

        addItem("veggie Burger",
                "Veggie burger on a whole wheat bun",
                true,
                3.99);
        addItem("Soup of the day",
                "A cup of the soup of the day",
                false,
                3.69);
        addItem("Burrito",
                "A large burrito, with whole pinto beans",
                true,
                4.29);

    }
}

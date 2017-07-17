package com.seven.designbox.designpatterns.patterns.proxy.bean;
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

public class User {
    private UserBean mBean;

    public User(String name) {
        initUserBean();
        mBean.setName(name);
    }

    public User(String name, boolean isVip) {
        initUserBean();
        mBean.setName(name);
        mBean.setVip(isVip);
    }

    private void initUserBean() {
        if (mBean == null) {
            mBean = new UserBean();
        }
    }

    public boolean isVip() {
        return mBean.isVip();
    }

    public String getUserName() {
        return mBean.getName();
    }

    public void tryVipService() {
        if (!mBean.isVip()) {
            mBean.setVip(true);
        }
    }
}

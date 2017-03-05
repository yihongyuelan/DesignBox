package com.seven.designbox.designpatterns.patterns.state.beans;
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

import com.seven.designbox.designpatterns.common.PToast;

public class WinnerState implements IState {
    private GumballMachine mMachine;

    public WinnerState(GumballMachine machine) {
        mMachine = machine;
    }

    @Override
    public void insertQuarter() {
        PToast.showToast("Please wait, we're already giving you a gumball");
    }

    @Override
    public void ejectQuarter() {
        PToast.showToast("Sorry, you already turned the crank");
    }

    @Override
    public void turnCrank() {
        PToast.showToast("Turning twice doesn't get you another gumball!");
    }

    @Override
    public void dispense() {
        PToast.showToast("YOU'RE A WINNER! You get two gumballs for your quarter");
        mMachine.releaseBall();
        if (mMachine.getCounts() == 0) {
            mMachine.setState(mMachine.getSoldOutState());
        } else {
            mMachine.releaseBall();
            if (mMachine.getCounts() > 0) {
                mMachine.setState(mMachine.getNoQuarterState());
            } else {
                PToast.showToast("Oops, out of gumballs!");
                mMachine.setState(mMachine.getSoldOutState());
            }
        }
    }
}

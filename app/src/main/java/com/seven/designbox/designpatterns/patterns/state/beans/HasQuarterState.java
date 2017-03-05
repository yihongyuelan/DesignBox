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

import java.util.Random;

public class HasQuarterState implements IState {
    Random mRandom = new Random(System.currentTimeMillis());
    GumballMachine mMachine;

    public HasQuarterState(GumballMachine machine) {
        mMachine = machine;
    }

    @Override
    public void insertQuarter() {
        PToast.showToast("You can't insert another quarter");
    }

    @Override
    public void ejectQuarter() {
        PToast.showToast("Quarter returned");
        mMachine.setState(mMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        PToast.showToast("You turned...");
        int winner = mRandom.nextInt(10);
        if ((winner == 0) && (mMachine.getCounts() > 1)) {
            mMachine.setState(mMachine.getWinnerState());
        } else {
            mMachine.setState(mMachine.getSoldState());
        }
    }

    @Override
    public void dispense() {
        PToast.showToast("No gumball dispensed");
    }
}

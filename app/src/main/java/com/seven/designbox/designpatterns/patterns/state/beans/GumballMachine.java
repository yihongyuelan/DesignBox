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

public class GumballMachine {
    private IState mSoldState;
    private IState mSoldOutState;
    private IState mNoQuarterState;
    private IState mHasQuarterState;
    private IState mWinnerState;
    private StateChangeListener mChangeListener;

    private IState mState;
    private int mCounts;

    public GumballMachine(StateChangeListener listener) {
        mSoldState = new SoldState(this);
        mSoldOutState = new SoldOutState(this);
        mNoQuarterState = new NoQuarterState(this);
        mHasQuarterState = new HasQuarterState(this);
        mWinnerState = new WinnerState(this);
        mCounts = createGumballs();
        mState = mNoQuarterState;
        mChangeListener = listener;
    }

    private int createGumballs() {
        return new Random().nextInt(5) + 1;
    }

    public void insertQuarter() {
        mState.insertQuarter();
        updateState();
    }

    public void ejectQuarter() {
        mState.ejectQuarter();
        updateState();
    }

    public void turnCrank() {
        mState.turnCrank();
        mState.dispense();
        updateState();
    }

    private void updateState() {
        if (mState instanceof HasQuarterState) {
            mChangeListener.onHasQuarterState();
        } else if (mState instanceof NoQuarterState) {
            mChangeListener.onNoQuarterState();
        } else if (mState instanceof SoldOutState) {
            mChangeListener.onSoldOutState();
        }
    }

    public void refillGumballs() {
        if (mCounts == 0) {
            mCounts = createGumballs();
        }
    }

    public void releaseBall() {
        PToast.showToast("A gumball comes rolling out the slot...");
        if (mCounts != 0) {
            mCounts = mCounts - 1;
        }
    }

    public int getCounts() {
        return mCounts;
    }

    public void setState(IState state) {
        mState = state;
    }

    public IState getHasQuarterState() {
        return mHasQuarterState;
    }

    public IState getNoQuarterState() {
        return mNoQuarterState;
    }

    public IState getSoldState() {
        return mSoldState;
    }

    public IState getSoldOutState() {
        return mSoldOutState;
    }

    public IState getWinnerState() {
        return mWinnerState;
    }
}

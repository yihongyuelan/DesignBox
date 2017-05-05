package com.seven.designbox.designpatterns.patterns.proxy;


import com.seven.designbox.R;
import com.seven.designbox.designpatterns.patterns.state.beans.GumballMachine;
import com.seven.designbox.designpatterns.patterns.state.beans.StateChangeListener;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProxyDemoFragment extends Fragment implements StateChangeListener {
    private static final int P_UP = 0;
    private static final int P_DOWN = 1;
    private static final int P_LEFT = 2;
    private static final int P_RIGHT = 3;

    private static final int D_UP = 0;
    private static final int D_DOWN = 1;
    private static final int D_LEFT = 2;
    private static final int D_RIGHT = 3;
    @BindView(R.id.eject_Tv)
    TextView mEjectTv;
    @BindView(R.id.gumball_counts_Tv)
    TextView mGumballCountsTv;
    @BindView(R.id.quarter_counts_Tv)
    TextView mQuarterCountsTv;
    @BindView(R.id.refill_Tv)
    TextView mRefillTv;
    @BindView(R.id.insert_Tv)
    TextView mInsertTv;
    @BindView(R.id.turnCrank_Tv)
    TextView mTurnCrankTv;
    @BindView(R.id.dispenseEqualZero_Tv)
    TextView mDispenseEqZeroTv;
    @BindView(R.id.dispenseGreaterZero_Tv)
    TextView mDispenseGtZeroTv;
    @BindView(R.id.noGumball_Btn)
    Button mNoGumballBtn;
    @BindView(R.id.noQuarter_Btn)
    Button mNoQuarterBtn;
    @BindView(R.id.hasQuarter_Btn)
    Button mHasQuarterBtn;
    private Unbinder mUnbinder;
    private GumballMachine mMachine;
    private boolean hasQuarter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.patterns_state, null, false);
        mUnbinder = ButterKnife.bind(this, view);
        initGumballMachine();
        updateGumballCounts();
        return view;
    }

    private void initGumballMachine() {
        if (mMachine == null) {
            mMachine = new GumballMachine(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @OnClick(R.id.eject_Tv)
    public void onEjectClick() {
        if (hasQuarter) {
            mMachine.ejectQuarter();
            hasQuarter = false;
            updateQuarterCounts();

            dimNoQuarter(false);
            dimHasQuarter(true);
            dimNoGumball(true);

            dimDispenseGtZero(true);
            dimDispenseEqZero(true);

            dimInsert(false);
            dimEject(true);
            dimTurnCrank(true);
        }
    }

    @OnClick(R.id.refill_Tv)
    public void onRefillClick() {
        if (mMachine.refillGumballs()) {
            updateGumballCounts();
            dimRefill(true);
            dimDispenseEqZero(true);
            if (hasQuarter) {
                //Update for gumball state
                dimHasQuarter(false);
                dimNoGumball(true);
                dimInsert(true);
                dimEject(false);
                dimTurnCrank(false);
                mMachine.setState(mMachine.getHasQuarterState());
            } else {
                //Update for gumball state
                dimNoQuarter(false);
                dimHasQuarter(true);
                dimNoGumball(true);
                dimInsert(false);
                mMachine.setState(mMachine.getNoQuarterState());
            }
        }
    }

    @OnClick(R.id.insert_Tv)
    public void onInsertClick() {
        if (hasQuarter) {
            return;
        }
        hasQuarter = true;
        updateQuarterCounts();
        mMachine.insertQuarter();
    }

    @OnClick(R.id.turnCrank_Tv)
    public void onTurnCrankClick() {
        mMachine.turnCrank();
    }

    private void updateGumballCounts() {
        mGumballCountsTv.setText(String.valueOf(mMachine.getCounts()));
    }

    private void updateQuarterCounts() {
        mQuarterCountsTv.setText(hasQuarter ? "1" : "0");
    }

    private void dimInsert(boolean dim) {
        updateControllerBg(mInsertTv, dim);
        updateArrowBg(mInsertTv, P_DOWN, D_RIGHT, dim);
    }

    private void dimEject(boolean dim) {
        updateControllerBg(mEjectTv, dim);
        updateArrowBg(mEjectTv, P_DOWN, D_LEFT, dim);
    }

    private void dimTurnCrank(boolean dim) {
        updateControllerBg(mTurnCrankTv, dim);
        updateArrowBg(mTurnCrankTv, P_RIGHT, D_DOWN, dim);
    }

    private void dimRefill(boolean dim) {
        updateControllerBg(mRefillTv, dim);
        updateArrowBg(mRefillTv, P_DOWN, D_RIGHT, dim);
    }

    private void dimDispenseEqZero(boolean dim) {
        updateArrowBg(mDispenseEqZeroTv, P_LEFT, D_UP, dim);
    }

    private void dimDispenseGtZero(boolean dim) {
        updateArrowBg(mDispenseGtZeroTv, P_LEFT, D_UP, dim);
    }

    private void dimNoQuarter(boolean dim) {
        updateStateBg(mNoQuarterBtn, dim);
    }

    private void dimHasQuarter(boolean dim) {
        updateStateBg(mHasQuarterBtn, dim);
    }

    private void dimNoGumball(boolean dim) {
        updateStateBg(mNoGumballBtn, dim);
    }

    private void updateStateBg(Button button, boolean gray) {
        if (button == null)
            return;
        button.setBackgroundResource(gray ? R.drawable.gumball_state_bg_default : R.drawable.gumball_state_bg_yellow);
    }

    private void updateControllerBg(TextView textView, boolean transparent) {
        if (textView == null)
            return;
        textView.setBackgroundResource(transparent ?
                R.drawable.state_controller_selector_disable :
                R.drawable.state_controller_selector_enable);
    }

    private Drawable getDrawableFromDirection(int direction, boolean black) {
        int drawableId = 0;
        switch (direction) {
            case D_UP:
                drawableId = black ? R.drawable.arrow_up : R.drawable.arrow_up_green;
                break;
            case D_DOWN:
                drawableId = black ? R.drawable.arrow_down : R.drawable.arrow_down_green;
                break;
            case D_LEFT:
                drawableId = black ? R.drawable.arrow_left : R.drawable.arrow_left_green;
                break;
            case D_RIGHT:
                drawableId = black ? R.drawable.arrow_right : R.drawable.arrow_right_green;
                break;
        }
        return ContextCompat.getDrawable(getContext(), drawableId);
    }

    private void updateArrowBg(TextView textView, int position, int direction, boolean black) {
        if (textView == null)
            return;
        Drawable drawable = getDrawableFromDirection(direction, black);
        switch (position) {
            case P_UP:
                textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
                break;
            case P_DOWN:
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
                break;
            case P_LEFT:
                textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                break;
            case P_RIGHT:
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                break;
        }
    }

    @Override
    public void onNoQuarterState() {
        updateGumballCounts();
        hasQuarter = false;
        updateQuarterCounts();
        //Update for gumball state
        dimNoQuarter(true);
        dimHasQuarter(true);
        dimTurnCrank(true);
        dimNoGumball(true);
        dimDispenseGtZero(false);
        dimInsert(false);
        dimEject(true);

    }

    @Override
    public void onHasQuarterState() {
        //Update for gumball state
        dimNoQuarter(true);
        dimHasQuarter(false);
        dimNoGumball(true);
        //Update for gumball controller
        dimInsert(true);
        dimEject(false);
        dimTurnCrank(false);
        dimDispenseGtZero(true);
    }

    @Override
    public void onSoldOutState() {
        updateGumballCounts();
        //Update for gumball state
        dimNoQuarter(true);
        dimHasQuarter(true);
        dimTurnCrank(true);
        dimDispenseEqZero(false);
        dimNoGumball(false);
        dimRefill(false);
    }
}

package com.seven.demobox.designpatterns.patterns.command;

import com.seven.demobox.R;
import com.seven.demobox.designpatterns.common.PatternsCommonActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class CommandPattern extends PatternsCommonActivity {

    private CommandItemLayout mCommandOne;
    private CommandItemLayout mCommandTwo;
    private CommandItemLayout mCommandThr;
    private CommandItemLayout mCommandFou;
    private TextView mCommandInfo;
    private CommandStateManager mStateManager;
    private CommandChangedListener mListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patterns_command);


        initViews();
        initClickListeners();
        initOthers();
    }

    @Override
    protected void onDestroy() {
        //TODO: Need to destroy resources!!
        super.onDestroy();
        mStateManager.onRelease();
        mStateManager = null;
        mListener = null;
    }

    private void initOthers() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.command_pattern_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mStateManager = CommandStateManager.getInstance();
        mListener = new CommandChangedListener();
        mStateManager.setStateListener(mListener);
    }

    private void initViews() {
        mCommandOne = (CommandItemLayout) findViewById(R.id.item1);
        mCommandTwo = (CommandItemLayout) findViewById(R.id.item2);
        mCommandThr = (CommandItemLayout) findViewById(R.id.item3);
        mCommandFou = (CommandItemLayout) findViewById(R.id.item4);
        mCommandInfo = (TextView) findViewById(R.id.command_info_tv);
        mCommandInfo.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void initClickListeners() {
        final String nameOne = mCommandOne.getCommandName();
        mCommandOne.setBtnClickListener(mCommandOne.new CommandItemBtnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                ControllerProxy.getInstance().buttonOnClicked(nameOne);
            }
        }, mCommandOne.new CommandItemBtnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                ControllerProxy.getInstance().buttonOffClicked(nameOne);
            }
        });

        final String nameTwo = mCommandTwo.getCommandName();
        mCommandTwo.setBtnClickListener(mCommandTwo.new CommandItemBtnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                ControllerProxy.getInstance().buttonOnClicked(nameTwo);
            }
        }, mCommandTwo.new CommandItemBtnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                ControllerProxy.getInstance().buttonOffClicked(nameTwo);
            }
        });

        final String nameThr = mCommandThr.getCommandName();
        mCommandThr.setBtnClickListener(mCommandThr.new CommandItemBtnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                ControllerProxy.getInstance().buttonOnClicked(nameThr);
            }
        }, mCommandThr.new CommandItemBtnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                ControllerProxy.getInstance().buttonOffClicked(nameThr);
            }
        });

        final String nameFou = mCommandFou.getCommandName();
        mCommandFou.setBtnClickListener(mCommandFou.new CommandItemBtnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                ControllerProxy.getInstance().buttonOnClicked(nameFou);
            }
        }, mCommandFou.new CommandItemBtnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                ControllerProxy.getInstance().buttonOffClicked(nameFou);
            }
        });
    }

    class CommandChangedListener implements CommandStateListener {
        @Override
        public void onStateChanged(CommandState state) {

            final String lastInfo = mCommandInfo.getText().toString()
                    + "\n---------------------------\n";
            final String newInfo = lastInfo
                    + "Light:" + state.getLightState() + "\n"
                    + "Door:" + state.getDoorState() + "\n"
                    + "Fan:" + state.getFanState() + "\n"
                    + "Stereo:" + state.getStereoState();
            mCommandInfo.setText(newInfo);
        }
    }
}

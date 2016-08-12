package com.seven.demobox.designpatterns.patterns.command;

import com.seven.demobox.R;
import com.seven.demobox.designpatterns.common.PatternsCommonActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
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
//        initClickListeners();
        initOthers();
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
    }

    private void initClickListeners() {
        mCommandOne.setBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerProxy.getInstance().buttonOnClicked(mCommandOne.getCommandName());
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerProxy.getInstance().buttonOffClicked(mCommandOne.getCommandName());
            }
        });

        mCommandTwo.setBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerProxy.getInstance().buttonOnClicked(mCommandTwo.getCommandName());
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerProxy.getInstance().buttonOffClicked(mCommandTwo.getCommandName());
            }
        });

        mCommandThr.setBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerProxy.getInstance().buttonOnClicked(mCommandThr.getCommandName());
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerProxy.getInstance().buttonOffClicked(mCommandThr.getCommandName());
            }
        });

        mCommandFou.setBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerProxy.getInstance().buttonOnClicked(mCommandFou.getCommandName());
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerProxy.getInstance().buttonOffClicked(mCommandFou.getCommandName());
            }
        });
    }

    class CommandChangedListener implements CommandStateListener {
        @Override
        public void onStateChanged(CommandState state) {

            final String lastInfo = mCommandInfo.getText().toString()
                    + "\n-----------\n";
            final String newInfo = lastInfo
                    + "Light:" + state.getLightState() + "\n"
                    + "Door:" + state.getDoorState() + "\n"
                    + "Fan:" + state.getFanState() + "\n"
                    + "Stereo:" + state.getStereoState();
            mCommandInfo.setText(newInfo);
        }
    }
}

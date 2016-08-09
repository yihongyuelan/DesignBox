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
    private CommandController mController;

    private LightOnCommand mLightOnCommand;
    private LightOffCommand mLightOffCommand;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patterns_command);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.command_pattern_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        initListeners();
    }

    private void initCommands() {
        final Light light = new Light();
        mLightOnCommand = new LightOnCommand(light);
        mLightOffCommand = new LightOffCommand(light);
    }

    private void initViews() {
        mCommandOne = (CommandItemLayout) findViewById(R.id.item1);
        mCommandTwo = (CommandItemLayout) findViewById(R.id.item2);
        mCommandThr = (CommandItemLayout) findViewById(R.id.item3);
        mCommandFou = (CommandItemLayout) findViewById(R.id.item4);
        mCommandInfo = (TextView) findViewById(R.id.command_info_tv);
    }

    private void initListeners() {
        mCommandOne.setBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:BtnOn clicked
                final String commandName = mCommandOne.getCommandName();
                mController.setCommand(mLightOnCommand);
                mController.buttonClicked();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:BtnOff clicked
                mController.setCommand(mLightOffCommand);
                mController.buttonClicked();
            }
        });

        mCommandTwo.setBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:BtnOn clicked
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:BtnOff clicked
            }
        });

        mCommandThr.setBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:BtnOn clicked
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:BtnOff clicked
            }
        });

        mCommandFou.setBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:BtnOn clicked
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:BtnOff clicked
            }
        });
    }
}

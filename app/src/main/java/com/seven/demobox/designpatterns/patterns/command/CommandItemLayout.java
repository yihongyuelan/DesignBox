package com.seven.demobox.designpatterns.patterns.command;

import com.seven.demobox.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CommandItemLayout extends LinearLayout implements View.OnClickListener {

    private TextView mNameTv;
    private Button mBtnOn;
    private Button mBtnOff;

    public CommandItemLayout(Context context) {
        this(context, null);
    }

    public CommandItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommandItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CommandItemAttr);
        final String title = ta.getString(R.styleable.CommandItemAttr_item_title);
        final String btnOnStr = ta.getString(R.styleable.CommandItemAttr_item_on_name);
        final String btnOffStr = ta.getString(R.styleable.CommandItemAttr_item_off_name);
        ta.recycle();
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.command_custom_layout, this);
        mNameTv = (TextView) findViewById(R.id.tv_name);
        mBtnOn =(Button) findViewById(R.id.btn_on);
        mBtnOn.setOnClickListener(this);
        mBtnOff =(Button) findViewById(R.id.btn_off);
        mBtnOff.setOnClickListener(this);
        mNameTv.setText(title);
        mBtnOn.setText(btnOnStr);
        mBtnOff.setText(btnOffStr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_on:
                mBtnOn.setBackgroundResource(R.color.colorButtonGreen);
                mBtnOff.setBackgroundColor(Color.GRAY);
                break;
            case R.id.btn_off:
                mBtnOn.setBackgroundColor(Color.GRAY);
                mBtnOff.setBackgroundResource(R.color.colorButtonRed);
                break;
        }
    }

    private void setBtnOnClickListener(OnClickListener listener) {
        if (mBtnOn != null && listener != null) {
            mBtnOn.setOnClickListener(listener);
        }
    }

    private void setBtnOffClickListener(OnClickListener listener) {
        if (mBtnOff != null && listener != null) {
            mBtnOff.setOnClickListener(listener);
        }
    }

    public void setBtnClickListener(OnClickListener onListener, OnClickListener offListener) {
        setBtnOnClickListener(onListener);
        setBtnOffClickListener(offListener);
    }

    public String getCommandName() {
        return mNameTv.getText().toString();
    }
}

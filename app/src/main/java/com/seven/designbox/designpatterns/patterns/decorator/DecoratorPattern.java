package com.seven.designbox.designpatterns.patterns.decorator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.seven.designbox.R;
import com.seven.designbox.designpatterns.common.PatternsCommonActivity;

public class DecoratorPattern extends PatternsCommonActivity implements View.OnClickListener {

    private RadioGroup mRoleGroup;
    private RadioGroup mGenderGroup;
    private RadioGroup mWeaponGroup;
    private Button mGenerateButton;
    private TextView mHeroDetailsTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patterns_decorator);
        setTitle(R.string.decorator_pattern_title);

        mRoleGroup = (RadioGroup) findViewById(R.id.radio_group_role);
        mGenderGroup = (RadioGroup) findViewById(R.id.radio_group_gender);
        mWeaponGroup = (RadioGroup) findViewById(R.id.radio_group_weapon);
        mGenerateButton = (Button) findViewById(R.id.button_generate);
        mGenerateButton.setOnClickListener(this);
        mHeroDetailsTv = (TextView) findViewById(R.id.hero_details_tv);
    }

    @Override
    public void onClick(View view) {
        final int roleId = mRoleGroup.getCheckedRadioButtonId();
        final int genderId = mGenderGroup.getCheckedRadioButtonId();
        final int weaponId = mWeaponGroup.getCheckedRadioButtonId();
        StringBuilder builder = new StringBuilder();

        switch (genderId) {
            case R.id.radio_btn_male:
                builder.append(getString(R.string.male));
                break;
            case R.id.radio_btn_female:
                builder.append(getString(R.string.female));
                break;
            default:
                break;
        }
        builder.append(" ");
        switch (roleId) {
            case R.id.radio_btn_worrior:
                builder.append(getString(R.string.worrior));
                break;
            case R.id.radio_btn_mage:
                builder.append(getString(R.string.mage));
                break;
            case R.id.radio_btn_hunter:
                builder.append(getString(R.string.hunter));
                break;
            case R.id.radio_btn_warlock:
                builder.append(getString(R.string.warlock));
                break;
            default:
                break;
        }
        builder.append(" using ");
        switch (weaponId) {
            case R.id.radio_btn_sword:
                builder.append(getString(R.string.sword));
                break;
            case R.id.radio_btn_sledge:
                builder.append(getString(R.string.sledge));
                break;
            case R.id.radio_btn_wand:
                builder.append(getString(R.string.wand));
                break;
            case R.id.radio_btn_bowarrow:
                builder.append(getString(R.string.bowarrow));
                break;
            default:
                break;
        }
        mHeroDetailsTv.setText(builder.toString());
    }
}
package com.seven.designbox.designpatterns.patterns.factory;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.seven.designbox.R;
import com.seven.designbox.designpatterns.common.PatternsCommonActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

public class FactoryPattern extends PatternsCommonActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patterns_factory);
        setTitle(R.string.factory_pattern_title);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.factory_pattern_simple, SimpleFactoryFragment.class)
                .add(R.string.factory_pattern_method, FactoryMethodFragment.class)
                .add(R.string.factory_pattern_abstract, AbstractFactoryFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }
}

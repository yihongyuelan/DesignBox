package com.seven.demobox.designpatterns.patterns.composite;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.seven.demobox.R;
import com.seven.demobox.designpatterns.common.PatternsCommonActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

public class CompositePattern extends PatternsCommonActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patterns_composite);
        setTitle(R.string.composite_pattern_title);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.composite_pattern_des, CompositeDesFragment.class)
                .add(R.string.composite_pattern_demo, CompositeDemoFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }
}

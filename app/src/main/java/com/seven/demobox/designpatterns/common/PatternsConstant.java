package com.seven.demobox.designpatterns.common;

import com.seven.demobox.R;
import com.seven.demobox.designpatterns.patterns.adaptive.AdaptivePattern;
import com.seven.demobox.designpatterns.patterns.command.CommandPattern;
import com.seven.demobox.designpatterns.patterns.composite.CompositePattern;
import com.seven.demobox.designpatterns.patterns.singleton.SingletonPattern;
import com.seven.demobox.designpatterns.patterns.decorator.DecoratorPattern;
import com.seven.demobox.designpatterns.patterns.factory.FactoryPattern;
import com.seven.demobox.designpatterns.patterns.observer.ObserverPattern;
import com.seven.demobox.designpatterns.patterns.template.TemplatePattern;

public class PatternsConstant {
    public static final PatternsDetail[] PATTERN_LISTS = {
            new PatternsDetail(R.string.observer_pattern_title,
                    R.string.observer_pattern_description,
                    ObserverPattern.class),
            new PatternsDetail(R.string.decorator_pattern_title,
            R.string.decorator_pattern_description,
            DecoratorPattern.class),
            new PatternsDetail(R.string.factory_pattern_title,
                    R.string.factory_pattern_description,
                    FactoryPattern.class),
            new PatternsDetail(R.string.singleton_pattern_title,
            R.string.singleton_pattern_description,
            SingletonPattern.class),
            new PatternsDetail(R.string.command_pattern_title,
                    R.string.command_pattern_description,
                    CommandPattern.class),
            new PatternsDetail(R.string.adaptive_pattern_title,
            R.string.adaptive_pattern_description,
            AdaptivePattern.class),
            new PatternsDetail(R.string.template_pattern_title,
                    R.string.template_pattern_description,
                    TemplatePattern.class),
            new PatternsDetail(R.string.composite_pattern_title,
                    R.string.composite_pattern_description,
                    CompositePattern.class),
    };
}

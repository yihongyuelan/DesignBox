package com.seven.demobox.designpatterns.common;

import com.seven.demobox.R;
import com.seven.demobox.designpatterns.patterns.adaptive.AdaptivePattern;
import com.seven.demobox.designpatterns.patterns.command.CommandPattern;
import com.seven.demobox.designpatterns.patterns.singleton.SingletonPattern;
import com.seven.demobox.designpatterns.patterns.decorator.DecoratorPattern;
import com.seven.demobox.designpatterns.patterns.factory.FactoryPattern;
import com.seven.demobox.designpatterns.patterns.observer.ObserverPattern;
import com.seven.demobox.designpatterns.patterns.template.TemplatePattern;

public class PatternUtils {
    public static final PatternDetails[] PATTERN_LISTS = {
            new PatternDetails(R.string.observer_pattern_title,
                    R.string.observer_pattern_description,
                    ObserverPattern.class),
            new PatternDetails(R.string.decorator_pattern_title,
            R.string.decorator_pattern_description,
            DecoratorPattern.class),
            new PatternDetails(R.string.factory_pattern_title,
                    R.string.factory_pattern_description,
                    FactoryPattern.class),
            new PatternDetails(R.string.singleton_pattern_title,
            R.string.singleton_pattern_description,
            SingletonPattern.class),
            new PatternDetails(R.string.command_pattern_title,
                    R.string.command_pattern_description,
                    CommandPattern.class),
            new PatternDetails(R.string.adaptive_pattern_title,
            R.string.adaptive_pattern_description,
            AdaptivePattern.class),
            new PatternDetails(R.string.template_pattern_title,
                    R.string.template_pattern_description,
                    TemplatePattern.class),
    };
}

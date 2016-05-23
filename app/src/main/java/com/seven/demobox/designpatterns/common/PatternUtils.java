package com.seven.demobox.designpatterns.common;

import com.seven.demobox.R;
import com.seven.demobox.designpatterns.patterns.decorator.DecoratorPattern;
import com.seven.demobox.designpatterns.patterns.factory.FactoryPattern;
import com.seven.demobox.designpatterns.patterns.observer.ObserverPattern;

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
    };
}

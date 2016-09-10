package com.uzabase.accessibility.checker.service.wcag;

import com.uzabase.accessibility.checker.service.Rule;

/**
 * Created by Trung on 1/25/2016 10:14 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public abstract class AbstractWcagRule implements Rule {
    public String getSuiteName() {
        return AbstractWcagRule.class.getPackage().getName();
    }
}

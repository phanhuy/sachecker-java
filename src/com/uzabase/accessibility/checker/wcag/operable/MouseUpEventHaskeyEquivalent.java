package com.uzabase.accessibility.checker.wcag.operable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.MouseUpFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.MOUSE_UP_EVENT_HAS_KEY_EQUIVALENT;
import static com.uzabase.accessibility.checker.wcag.Shared.KEY_UP;

/**
 * Rule for mouse up having a key equivalent
 *
 * Created by Trung on 1/30/2016 1:35 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class MouseUpEventHaskeyEquivalent extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new MouseUpFilter();
    }

    public Issue check(Element element) {
        if (!element.hasAttr(KEY_UP))
            return new Issue(this.getClass().getSimpleName(),
                    MOUSE_UP_EVENT_HAS_KEY_EQUIVALENT,
                    ERROR,
                    element);
        return null;
    }
}

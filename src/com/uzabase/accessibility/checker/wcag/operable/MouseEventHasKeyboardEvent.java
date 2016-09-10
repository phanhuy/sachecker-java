package com.uzabase.accessibility.checker.wcag.operable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.MouseEventFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.MOUSE_EVENT_HAS_KEYBOARD_EVENT;
import static com.uzabase.accessibility.checker.wcag.Shared.*;

/**
 * Rule for mouse event having keyboard equivalent
 *
 * Created by Trung on 1/30/2016 1:31 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class MouseEventHasKeyboardEvent extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new MouseEventFilter();
    }

    public Issue check(Element element) {
        String[] keyboardEvents = new String[]{KEY_DOWN, KEY_PRESS, KEY_UP};

        for (String event : keyboardEvents)
            if (element.hasAttr(event))
                return null;

        return new Issue(this.getClass().getSimpleName(),
                MOUSE_EVENT_HAS_KEYBOARD_EVENT,
                ERROR,
                element);
    }
}

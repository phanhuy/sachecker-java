package com.uzabase.accessibility.checker.wcag.understandable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractUnderstandableRule;
import com.uzabase.accessibility.checker.utils.Message;
import com.uzabase.crawler.crawler.ElementFilter;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.InputControlFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.wcag.Shared.ID;
import static com.uzabase.accessibility.checker.wcag.Shared.getRootElement;

/**
 * Rule for control id uniqueness
 *
 * Created by Trung on 1/29/2016 6:03 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class ControlIdUnique extends AbstractUnderstandableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new InputControlFilter();
    }

    public Issue check(Element element) {
        if (!element.hasAttr(ID))
            return null;

        Element root = getRootElement(element);
        ElementFilter filter = new InputControlFilter();
        for (Element otherControl : filter.result(root)) {
            if (element.equals(otherControl))
                continue;;

            if (otherControl.hasAttr(ID) &&
                    element.attr(ID).equals(otherControl.attr(ID)))
                return new Issue(this.getClass().getSimpleName(),
                        Message.CONTROL_ID_UNIQUE,
                        ERROR,
                        element);
        }

        return null;
    }
}

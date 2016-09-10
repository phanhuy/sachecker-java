package com.uzabase.accessibility.checker.wcag.operable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.ClickFilter;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.List;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.ON_CLINK_IS_FOCUSABLE;
import static com.uzabase.accessibility.checker.wcag.Shared.*;

/**
 * Rule for on click elements also being focusable
 *
 * Created by Trung on 1/30/2016 1:46 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class OnClickIsFocusable extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new ClickFilter();
    }

    public Issue check(Element element) {
        if (notFocusable(element))
            return new Issue(this.getClass().getSimpleName(),
                    ON_CLINK_IS_FOCUSABLE,
                    ERROR,
                    element);

        return null;
    }

    private boolean notFocusable(Element element) {
        List<String> focusable = Arrays.asList("button", "input", "select", "textarea");

        if (focusable.contains(element.tagName()))
            return false;

        return !(ANCHOR.equals(element.tagName()) &&
                (element.hasAttr(HREF) ||
                        element.hasAttr(TAB_INDEX)) &&
                0 <= tryParse(element.attr(TAB_INDEX)));

    }

    private static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("Fucking Error when Parse Number");
            return -1;
        }
    }
}

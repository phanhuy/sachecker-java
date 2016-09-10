package com.uzabase.accessibility.checker.wcag.perceivable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractPerceivableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.ImageFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.WARNING;
import static com.uzabase.accessibility.checker.utils.Message.ALT_TEXT_LENGTH_REASONABLE;
import static com.uzabase.accessibility.checker.wcag.Shared.ALT_TEXT;
import static com.uzabase.accessibility.checker.wcag.Shared.isVisible;

/**
 * Rule for alt text a reasonable length.
 *
 * Created by Trung on 1/29/2016 4:48 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class AltTextLengthReasonable extends AbstractPerceivableRule {

    private static final int MIN_ALT_LENGTH = 7;
    private static final int MAX_ALT_LENGTH = 90;

    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new ImageFilter();
    }

    public Issue check(Element element) {
        if ((isVisible(element) &&
                element.hasAttr(ALT_TEXT)) &&
                (element.attr(ALT_TEXT).length() < MIN_ALT_LENGTH ||
                element.attr(ALT_TEXT).length() > MAX_ALT_LENGTH)) {
            return new Issue(this.getClass().getSimpleName(),
                    ALT_TEXT_LENGTH_REASONABLE,
                    WARNING,
                    element);
        }
        return null;
    }
}

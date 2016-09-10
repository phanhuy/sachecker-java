package com.uzabase.accessibility.checker.wcag.understandable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractUnderstandableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.ButtonFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.BUTTON_HAS_CONTENT;

/**
 * Created by Trung on 1/29/2016 5:21 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class ButtonHasContent extends AbstractUnderstandableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new ButtonFilter();
    }

    public Issue check(Element element) {
        if ((element.hasText() && !element.text().trim().isEmpty()) ||
                !element.children().isEmpty()) {
            return null;
        }

        return new Issue(this.getClass().getSimpleName(),
                BUTTON_HAS_CONTENT,
                ERROR,
                element);
    }
}

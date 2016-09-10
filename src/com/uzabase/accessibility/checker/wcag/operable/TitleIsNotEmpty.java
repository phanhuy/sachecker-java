package com.uzabase.accessibility.checker.wcag.operable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.HtmlFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.TITLE_IS_NOT_EMPTY;
import static com.uzabase.accessibility.checker.wcag.Shared.HEAD;
import static com.uzabase.accessibility.checker.wcag.Shared.TITLE;

/**
 * Rule for empty title element.
 *
 * Created by Trung on 1/30/2016 2:24 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class TitleIsNotEmpty extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new HtmlFilter();
    }

    public Issue check(Element element) {
        String IN_HRAD = HEAD + ">" + TITLE;

        if (1 != element.select(IN_HRAD).size() ||
                !element.select(IN_HRAD).first().hasText() ||
                element.select(IN_HRAD).first().text().trim().isEmpty())
            return new Issue(this.getClass().getSimpleName(),
                    TITLE_IS_NOT_EMPTY,
                    ERROR,
                    element);

        return null;
    }
}

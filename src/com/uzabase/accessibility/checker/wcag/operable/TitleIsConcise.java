package com.uzabase.accessibility.checker.wcag.operable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.TitleFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.TITLE_IS_CONCISE;

/**
 * Rule for concise title text
 *
 * Created by Trung on 1/30/2016 2:20 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class TitleIsConcise extends AbstractOperableRule {

    private static final int MAX_TITLE_LENGTH = 60;

    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new TitleFilter();
    }

    public Issue check(Element element) {
        if (element.hasText() && MAX_TITLE_LENGTH < element.text().length())
            return new Issue(this.getClass().getSimpleName(),
                    TITLE_IS_CONCISE,
                    ERROR,
                    element);

        return null;
    }
}

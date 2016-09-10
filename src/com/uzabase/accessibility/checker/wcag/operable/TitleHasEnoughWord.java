package com.uzabase.accessibility.checker.wcag.operable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.TitleFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.TITLE_HAS_ENOUGH_WORD;

/**
 * Created by Trung on 1/30/2016 2:17 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class TitleHasEnoughWord extends AbstractOperableRule {

    private static final int MIN_TITLE_WORDS = 2;

    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new TitleFilter();
    }

    public Issue check(Element element) {
        if (element.hasText() && MIN_TITLE_WORDS > element.text().split("\\W").length)
            return new Issue(this.getClass().getSimpleName(),
                    TITLE_HAS_ENOUGH_WORD,
                    ERROR,
                    element);

        return null;
    }
}

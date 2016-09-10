package com.uzabase.accessibility.checker.wcag.understandable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractUnderstandableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.FieldSetFilter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.FILED_SET_HAS_LEGEND;
import static com.uzabase.accessibility.checker.wcag.Shared.LEGEND;

/**
 * Rule for field set elements having a legend
 *
 * Created by Trung on 1/29/2016 8:40 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class FieldSetHasLegend extends AbstractUnderstandableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new FieldSetFilter();
    }

    public Issue check(Element element) {
        Elements legends = element.select(LEGEND);

        if (legends.isEmpty())
            return new Issue(this.getClass().getSimpleName(),
                    FILED_SET_HAS_LEGEND,
                    ERROR,
                    element);

        return null;
    }
}

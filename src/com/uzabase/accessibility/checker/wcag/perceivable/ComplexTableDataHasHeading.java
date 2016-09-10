package com.uzabase.accessibility.checker.wcag.perceivable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractPerceivableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.TableFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.COMPLEX_TABLE_DATA_HAS_HEADING;
import static com.uzabase.accessibility.checker.wcag.Shared.*;

/**
 * Complex table rule
 *
 * Created by Trung on 1/29/2016 5:28 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class ComplexTableDataHasHeading extends AbstractPerceivableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new TableFilter();
    }

    public Issue check(Element element) {
        if (notComplexTable(element))
            return null;
        for (Element td : element.select(TD)) {
            if (!td.hasAttr(HEADERS))
                return new Issue(this.getClass().getSimpleName(),
                        COMPLEX_TABLE_DATA_HAS_HEADING,
                        ERROR,
                        element);
        }
        return null;
    }
}

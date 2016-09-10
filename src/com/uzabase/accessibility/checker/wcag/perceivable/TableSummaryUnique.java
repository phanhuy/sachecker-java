package com.uzabase.accessibility.checker.wcag.perceivable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractPerceivableRule;
import com.uzabase.crawler.crawler.ElementFilter;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.TableFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.TABLE_SUMMARY_UNIQUE;
import static com.uzabase.accessibility.checker.wcag.Shared.SUMMARY;
import static com.uzabase.accessibility.checker.wcag.Shared.getRootElement;

/**
 * Rule for table summary uniqueness
 *
 * Created by Trung on 1/30/2016 2:10 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class TableSummaryUnique extends AbstractPerceivableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new TableFilter();
    }

    public Issue check(Element element) {
        if (!element.hasAttr(SUMMARY))
            return null;

        Element root = getRootElement(element);
        ElementFilter filter = new TableFilter();

        for (Element otherTable : filter.result(root)) {
            if (element.equals(otherTable))
                continue;

            if (!otherTable.hasAttr(SUMMARY))
                continue;

            if (element.attr(SUMMARY).equals(otherTable.attr(SUMMARY)))
                return new Issue(this.getClass().getSimpleName(),
                        TABLE_SUMMARY_UNIQUE,
                        ERROR,
                        element);

        }

        return null;
    }
}

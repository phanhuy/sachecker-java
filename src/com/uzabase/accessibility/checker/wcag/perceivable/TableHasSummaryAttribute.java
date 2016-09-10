package com.uzabase.accessibility.checker.wcag.perceivable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractPerceivableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.TableFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.TABLE_HAS_SUMMARY_ATTRIBUTE;
import static com.uzabase.accessibility.checker.wcag.Shared.SUMMARY;

/**
 * Rule for table summary attribute presence
 *
 * Created by Trung on 1/30/2016 2:07 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class TableHasSummaryAttribute extends AbstractPerceivableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new TableFilter();
    }

    public Issue check(Element element) {
        if (!element.hasAttr(SUMMARY)) {
            return new Issue(this.getClass().getSimpleName(),
                    TABLE_HAS_SUMMARY_ATTRIBUTE,
                    ERROR,
                    element);
        }

        return null;
    }
}

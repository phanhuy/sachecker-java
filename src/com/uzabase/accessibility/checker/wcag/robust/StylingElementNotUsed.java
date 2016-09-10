package com.uzabase.accessibility.checker.wcag.robust;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractRobustRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.StyleElementFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.STYLING_ELEMENT_NOT_USED;

/**
 * Rule for styling element not present
 *
 * Created by Trung on 1/30/2016 2:01 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class StylingElementNotUsed extends AbstractRobustRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new StyleElementFilter();
    }

    public Issue check(Element element) {
        return new Issue(this.getClass().getSimpleName(),
                STYLING_ELEMENT_NOT_USED,
                ERROR,
                element);
    }
}

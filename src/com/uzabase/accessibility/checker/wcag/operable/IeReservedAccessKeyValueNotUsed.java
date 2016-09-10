package com.uzabase.accessibility.checker.wcag.operable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.AccessKeyFilter;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.List;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.IE_RESERVED_ACCESS_KEY_VALUE_NOT_USED;
import static com.uzabase.accessibility.checker.wcag.Shared.ACCESS_KEY;

/**
 * Rule for MS internet explorer reserved access key value usage
 *
 * Created by Trung on 1/30/2016 12:44 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class IeReservedAccessKeyValueNotUsed extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new AccessKeyFilter();
    }

    public Issue check(Element element) {
        List<String> internetExplorerKeys = Arrays.asList("a", "e", "f", "h", "t","v");

        if (internetExplorerKeys.contains(element.attr(ACCESS_KEY).toLowerCase()))
            return new Issue(this.getClass().getSimpleName(),
                    IE_RESERVED_ACCESS_KEY_VALUE_NOT_USED,
                    ERROR,
                    element);

        return null;
    }
}

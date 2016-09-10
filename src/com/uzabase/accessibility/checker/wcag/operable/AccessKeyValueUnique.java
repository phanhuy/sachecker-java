package com.uzabase.accessibility.checker.wcag.operable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.crawler.ElementFilter;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.AccessKeyFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.ACCESS_KEY_VALUE_UNIQUE;
import static com.uzabase.accessibility.checker.wcag.Shared.ACCESS_KEY;
import static com.uzabase.accessibility.checker.wcag.Shared.getRootElement;

/**
 * Created by Trung on 1/27/2016 12:03 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class AccessKeyValueUnique extends AbstractOperableRule {

    public String getRuleName() {
        return "AccessKeyValueUnique";
    }

    public Filter getFilter() {
        return new AccessKeyFilter();
    }

    public Issue check(Element element) {
        Element root = getRootElement(element);
        ElementFilter filter = new AccessKeyFilter();

        for (Element other : filter.result(root)) {
            if (other.equals(element))
                continue;

            if (element.attr(ACCESS_KEY).equals(other.attr(ACCESS_KEY))) {
                return new Issue(this.getClass().getSimpleName(),
                        ACCESS_KEY_VALUE_UNIQUE,
                        ERROR,
                        element);
            }
        }
        return null;
    }
}

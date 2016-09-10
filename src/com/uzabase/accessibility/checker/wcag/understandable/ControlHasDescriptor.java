package com.uzabase.accessibility.checker.wcag.understandable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractUnderstandableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.InputControlFilter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.CONTROL_HAS_DESCRIPTOR;
import static com.uzabase.accessibility.checker.wcag.Shared.*;

/**
 * Page control has a description rule.
 *
 * Created by Trung on 1/29/2016 5:49 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class ControlHasDescriptor extends AbstractUnderstandableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new InputControlFilter();
    }

    public Issue check(Element element) {
        if (isImageInput(element))
            return null;

        if (element.hasAttr(TITLE) &&
                !element.attr(TITLE).trim().isEmpty())
            return null;

        if (element.hasAttr(ID) &&
                !element.attr(ID).trim().isEmpty()) {
            Element root = getRootElement(element);
            Elements label = root.select("label[for=" + element.attr(ID) +
                    "]");
            if (!label.isEmpty())
                return null;
        }
        return new Issue(this.getClass().getSimpleName(),
                CONTROL_HAS_DESCRIPTOR,
                ERROR,
                element);
    }
}

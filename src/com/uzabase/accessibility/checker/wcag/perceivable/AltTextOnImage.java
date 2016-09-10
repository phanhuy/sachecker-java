package com.uzabase.accessibility.checker.wcag.perceivable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractPerceivableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.ImageFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.ALT_TEXT_ON_IMAGE;
import static com.uzabase.accessibility.checker.wcag.Shared.ALT_TEXT;
import static com.uzabase.accessibility.checker.wcag.Shared.isVisible;

/**
 * Created by Trung on 1/29/2016 5:08 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class AltTextOnImage extends AbstractPerceivableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
       return new ImageFilter();
    }

    public Issue check(Element element) {
        if (isVisible(element) &&
                (!element.hasAttr(ALT_TEXT) ||
                element.attr(ALT_TEXT).trim().isEmpty())) {
            return new Issue(this.getClass().getSimpleName(),
                    ALT_TEXT_ON_IMAGE,
                    ERROR,
                    element);
        }
        return null;
    }
}

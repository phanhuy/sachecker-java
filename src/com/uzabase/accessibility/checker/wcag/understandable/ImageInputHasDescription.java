package com.uzabase.accessibility.checker.wcag.understandable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractUnderstandableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.InputControlFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.IMAGE_INPUT_HAS_DESCRIPTION;
import static com.uzabase.accessibility.checker.wcag.Shared.*;

/**
 * Created by Trung on 1/30/2016 12:49 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class ImageInputHasDescription extends AbstractUnderstandableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new InputControlFilter();
    }

    public Issue check(Element element) {
        if (!isImageInput(element))
            return null;

        if (element.hasAttr(ALT_TEXT) || element.hasAttr(TITLE))
            return null;

        return new Issue(this.getClass().getSimpleName(),
                IMAGE_INPUT_HAS_DESCRIPTION,
                ERROR,
                element);
    }
}

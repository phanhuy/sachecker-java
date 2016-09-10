package com.uzabase.accessibility.checker.wcag.perceivable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractPerceivableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.ImageFilter;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.List;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.ALT_TEXT_ON_IMAGE_NOT_BAD;
import static com.uzabase.accessibility.checker.wcag.Shared.ALT_TEXT;

/**
 * Rule for alt text badness
 *
 * Created by Trung on 1/29/2016 5:13 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class AltTextOnImageNotBad extends AbstractPerceivableRule {

    private static final List<String> BAD_ALT_WORDS = Arrays.asList("image", "picture", "graph", "photo");

    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new ImageFilter();
    }

    public Issue check(Element element) {
        if (element.hasAttr(ALT_TEXT) &&
                !element.attr(ALT_TEXT).trim().isEmpty() &&
                BAD_ALT_WORDS.contains(element.attr(ALT_TEXT).toLowerCase())) {
         return new Issue(this.getClass().getSimpleName(),
                 ALT_TEXT_ON_IMAGE_NOT_BAD,
                 ERROR,
                 element);
        }
        return null;
    }
}

package com.uzabase.accessibility.checker.wcag.operable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.FrameFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.FRAME_HAS_TITLE;
import static com.uzabase.accessibility.checker.wcag.Shared.TITLE;

/**
 * Rule for frame having title
 *
 * Created by Trung on 1/29/2016 8:48 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class FrameHasTitle extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new FrameFilter();
    }

    public Issue check(Element element) {
        if (!element.hasAttr(TITLE) || element.attr(TITLE).trim().isEmpty())
            return new Issue(this.getClass().getSimpleName(),
                    FRAME_HAS_TITLE,
                    ERROR,
                    element);

        return null;
    }
}

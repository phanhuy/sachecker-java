package com.uzabase.accessibility.checker.wcag.operable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.crawler.ElementFilter;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.FrameFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.FRAME_TITLE_UNIQUE;
import static com.uzabase.accessibility.checker.wcag.Shared.TITLE;
import static com.uzabase.accessibility.checker.wcag.Shared.getRootElement;

/**
 * Rule for frame title uniqueness
 *
 * Created by Trung on 1/29/2016 9:09 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class FrameTitleUnique extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new FrameFilter();
    }

    public Issue check(Element element) {
        if (!element.hasAttr(TITLE))
            return null;

        Element root = getRootElement(element);
        ElementFilter filter = new FrameFilter();
        for (Element otherFrame : filter.result(root)) {
            if (element.equals(otherFrame))
                continue;;

            if (otherFrame.hasAttr(TITLE) && element.attr(TITLE).equals(otherFrame.attr(TITLE)))
                return new Issue(this.getClass().getSimpleName(),
                        FRAME_TITLE_UNIQUE,
                        ERROR,
                        element);
        }
        return null;
    }
}

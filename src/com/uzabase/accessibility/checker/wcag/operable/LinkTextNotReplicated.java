package com.uzabase.accessibility.checker.wcag.operable;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.crawler.ElementFilter;
import com.uzabase.crawler.crawler.Filter;
import com.uzabase.crawler.crawler.filter.LinkFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.accessibility.checker.service.Issue.Severity.ERROR;
import static com.uzabase.accessibility.checker.utils.Message.LINK_TEXT_NOT_REPLICATED;
import static com.uzabase.accessibility.checker.wcag.Shared.*;

/**
 * Rule for link text not replicated on different targets
 *
 * Created by Trung on 1/30/2016 12:55 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class LinkTextNotReplicated extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new LinkFilter();
    }

    public Issue check(Element element) {
        Element root = getRootElement(element);
        ElementFilter filter = new LinkFilter();

        for (Element otherLink : filter.result(root)) {
            if (element.equals(otherLink))
                continue;
            if (containedText(element).equalsIgnoreCase(containedText(otherLink)) && !sameHref(element, otherLink))
                return new Issue(this.getClass().getSimpleName(),
                        LINK_TEXT_NOT_REPLICATED,
                        ERROR,
                        element);
        }

        return  null;
    }

    private boolean sameHref(Element link, Element otherLink) {
        if (link.hasAttr(HREF) && otherLink.hasAttr(HREF)) {
            if (!link.baseUri().isEmpty())
                return link.absUrl(HREF).equals(otherLink.absUrl(HREF));
            else return link.attr(HREF).equals(otherLink.absUrl(HREF));
        }
        else return false;
    }
}

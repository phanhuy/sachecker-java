package com.uzabase.crawler.crawler.filter;

import com.uzabase.crawler.crawler.ElementFilter;
import com.uzabase.crawler.crawler.store.ElementStore;
import org.jsoup.nodes.Element;

/**
 * Created by Trung on 1/23/2016 3:30 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class EventFilter extends ElementFilter {
    @Override
    public Iterable<Element> result(Element root) {
        return root.select(ElementStore.EVENT_ELEMENT_SELECTOR);
    }
}

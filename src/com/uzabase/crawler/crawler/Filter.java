package com.uzabase.crawler.crawler;

import org.jsoup.nodes.Element;

/**
 * Created by Trung on 1/23/2016 3:12 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public interface Filter {

    public Iterable<Element> result(Element root);
}

package com.uzabase.crawler.crawler;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 1/23/2016 3:27 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class ElementFilter implements Filter {
    public Iterable<Element> result(Element root) {
        List<Element> results = new ArrayList<Element>(1);
        results.add(root);
        return results;
    }
}

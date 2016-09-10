package com.uzabase.crawler.crawler.filter;

import com.uzabase.crawler.crawler.ElementFilter;
import org.jsoup.nodes.Element;

/**
 * Created by Trung on 1/23/2016 3:35 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class ImageFilter extends ElementFilter {
    @Override
    public Iterable<Element> result(Element root) {
        return root.select("img");
    }
}

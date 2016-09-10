package com.uzabase.accessibility.checker.service;

import com.uzabase.crawler.crawler.Filter;
import org.jsoup.nodes.Element;

/**
 * Created by Trung on 1/25/2016 10:00 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public interface Rule {

    public String getSuiteName();

    public String getGroupName();

    public String getRuleName();

    public Filter getFilter();

    public Issue check(Element element);
}

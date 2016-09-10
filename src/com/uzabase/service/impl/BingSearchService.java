package com.uzabase.service.impl;

import com.uzabase.entity.SearchRequest;
import com.uzabase.entity.SearchResult;
import com.uzabase.service.SearchService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Trung on 3/29/2016 12:18 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
@Service
public class BingSearchService implements SearchService {

    @Value("${bing.base_url}")
    private String baseUrl;

    @Value("${jsoup.user_agent}")
    private String userAgent;

    @Value("${jsoup.referrer}")
    private String referrer;

    @Override
    public Set<SearchResult> search(SearchRequest request) throws IOException {
        Set<SearchResult> res = new HashSet<>();

        // Jsoup part
        Document doc = Jsoup.connect(baseUrl + request.getKeyword())
                .userAgent(userAgent)
                .referrer(referrer)
                .get();
        Elements results = doc.select(".b_algo");
        for(Element e : results) {
            Element title = e.select("a").first();
            String text = title.text();
            String url = title.attr("href");

            Element caption = e.select(".b_caption").first();
            String description = caption.select("p").first().text();

            res.add(new SearchResult(text, url, description));
        }
        return res;
    }
}

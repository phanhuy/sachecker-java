package com.uzabase.service;

import com.uzabase.entity.SearchRequest;
import com.uzabase.entity.SearchResult;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Trung on 3/28/2016 9:08 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public interface SearchService {

    Set<SearchResult> search(SearchRequest request) throws IOException;
}

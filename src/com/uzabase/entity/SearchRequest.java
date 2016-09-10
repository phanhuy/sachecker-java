package com.uzabase.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by Trung on 3/28/2016 8:59 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class SearchRequest {

    private String keyword;

    public SearchRequest() {  }

    public SearchRequest(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchRequest that = (SearchRequest) o;

        if (keyword != null ? !keyword.equals(that.keyword) : that.keyword != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return keyword != null ? keyword.hashCode() : 0;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("keyword", keyword).
                toString();
    }
}

package com.uzabase.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by Trung on 3/28/2016 9:05 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class SearchResult {

    private String title;
    private String titleLink;
    private String description;

    public SearchResult() { }

    public SearchResult(String title, String titleLink, String description) {
        this.title = title;
        this.titleLink = titleLink;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleLink() {
        return titleLink;
    }

    public void setTitleLink(String titleLink) {
        this.titleLink = titleLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchResult that = (SearchResult) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (titleLink != null ? !titleLink.equals(that.titleLink) : that.titleLink != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (titleLink != null ? titleLink.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("title", title).
                append("titleLink", titleLink).
                append("description", description).
                toString();
    }
}

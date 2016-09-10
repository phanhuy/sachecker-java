package com.uzabase.entity;

/**
 * Created by Trung on 4/4/2016 10:36 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class Url {
    private String url;

    public Url(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Url url1 = (Url) o;

        return url != null ? url.equals(url1.url) : url1.url == null;

    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Url{" +
                "url='" + url + '\'' +
                '}';
    }
}

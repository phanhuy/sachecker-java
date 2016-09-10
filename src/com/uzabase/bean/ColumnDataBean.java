package com.uzabase.bean;

import org.codehaus.jackson.map.annotate.JsonRootName;

import java.util.List;

/**
 * Created by Trung on 3/30/2016 2:42 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
@JsonRootName("columnDataBean")
public class ColumnDataBean {

    private String id;
    private String title;
    private List<ColumnSeriesBean> series;

    public ColumnDataBean(String id, String title, List<ColumnSeriesBean> series) {
        this.id = id;
        this.title = title;
        this.series = series;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ColumnSeriesBean> getSeries() {
        return series;
    }

    public void setSeries(List<ColumnSeriesBean> series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColumnDataBean that = (ColumnDataBean) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return series != null ? series.equals(that.series) : that.series == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (series != null ? series.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ColumnDataBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", series=" + series +
                '}';
    }
}

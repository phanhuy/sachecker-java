package com.uzabase.bean;

import org.codehaus.jackson.map.annotate.JsonRootName;

import java.util.List;

/**
 * Created by Trung on 3/29/2016 12:41 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
@JsonRootName("pieDataBean")
public class PieDataBean {
    private String id;
    private String title;
    private List<PieSeriesBean> series;

    public PieDataBean(String id, String title, List<PieSeriesBean> series) {
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

    public List<PieSeriesBean> getSeries() {
        return series;
    }

    public void setSeries(List<PieSeriesBean> series) {
        this.series = series;
    }
}

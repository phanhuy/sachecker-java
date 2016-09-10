package com.uzabase.bean;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Trung on 3/29/2016 12:40 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class PieSeriesBean {
    @JsonProperty("name")
    private String name;
    @JsonProperty("data")
    private double data;

    public PieSeriesBean(String name, double data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
}

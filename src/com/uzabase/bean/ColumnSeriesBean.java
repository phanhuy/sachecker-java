package com.uzabase.bean;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Arrays;

/**
 * Created by Trung on 3/30/2016 2:42 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class ColumnSeriesBean {

    @JsonProperty
    private String name;
    @JsonProperty
    private double[] data;

    public ColumnSeriesBean(String name, double[] data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColumnSeriesBean that = (ColumnSeriesBean) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return Arrays.equals(data, that.data);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "ColumnSeriesBean{" +
                "name='" + name + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}

package com.uzabase.accessibility.checker.utils;

/**
 * Created by Trung on 4/3/2016 8:35 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class RuleResultCounter {

    private int total;
    private int error;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public void increaseTotal() {
        this.total ++;
    }

    public void increaseError() {
        this.error ++;
    }

    @Override
    public String toString() {
        return "total=" + total +
                ", error=" + error +
                '}';
    }
}

package com.uzabase.accessibility.checker.service;

import org.jsoup.nodes.Element;

/**
 * Created by Trung on 1/25/2016 10:02 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class Issue {

    public enum Severity {
        ERROR {
            @Override
            public String toString() {
                return "error";
            }
        },
        WARNING {
            @Override
            public String toString() {
                return "warning";
            }
        }
    }

    private static final String EMPTY = "\"\"";

    private String testname = null, description = null;
    private Severity severity;
    private Element element = null;

    public Issue(String testname, String description, Severity severity, Element element) {
        this.testname = testname;
        this.description = description;
        this.severity = severity;
        this.element = element;
    }

    public String getTestname() {
        return testname;
    }

    public String getDescription() {
        return description;
    }

    public Severity getSeverity() {
        return severity;
    }

    public Element getElement() {
        return element;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "testname='" + testname + '\'' +
                ", description='" + description + '\'' +
                ", severity=" + severity +
                ", element=" + element +
                '}';
    }
}

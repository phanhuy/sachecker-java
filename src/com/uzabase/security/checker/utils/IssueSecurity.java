package com.uzabase.security.checker.utils;

/**
 * Created by Trung on 4/7/2016 10:50 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 *
 * This class description for a result of checking in security
 */
public class IssueSecurity {

    public enum Severity {
        NORMAL {
            @Override
            public String toString() {
                return "normal";
            }
        },
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

    private SecurityType type;
    private Severity severity;
    private String vector;
    private String input;
    public IssueSecurity(SecurityType type, Severity severity, String vector, String input) {
        this.type = type;
        this.severity = severity;
        this.vector = vector;
        this.input = input;
    }

    public SecurityType getType() {
        return type;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getVector() {
        return vector;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssueSecurity that = (IssueSecurity) o;

        if (type != that.type) return false;
        if (severity != that.severity) return false;
        if (vector != null ? !vector.equals(that.vector) : that.vector != null) return false;
        return input != null ? input.equals(that.input) : that.input == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (severity != null ? severity.hashCode() : 0);
        result = 31 * result + (vector != null ? vector.hashCode() : 0);
        result = 31 * result + (input != null ? input.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IssueSecurity{" +
                "type=" + type +
                ", severity=" + severity +
                ", vector='" + vector + '\'' +
                ", input='" + "<![CDATA[" + input + "]]>" +'\'' +
                '}';
    }
}

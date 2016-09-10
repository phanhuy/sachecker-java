package com.uzabase.security.checker.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 4/7/2016 12:52 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 *
 * This class includes all setting about fuzzing: home, login, password and fuzzing data...
 */
public class Settings {
    private String home;
    private String completeness;
    private int timeGap;
    private List<String> sensitiveData;
    private List<String> usernames;
    private List<String> passwords;
    private List<String> pageguesses;
    private List<String> xssFuzzVectors;
    private List<String> integerOverflowFuzzVectors;
    private List<String> sqlInjectionFuzzVectors;
    private List<String> ldapInjectionFuzzVectors;
    private List<String> xpathInjectionFuzzVectors;
    private List<String> xmlInjectionFuzzVectors;

    Settings() {}

    public String getHome() {
        return home;
    }

    public List<String> getSensitiveData() {
        return sensitiveData;
    }

    public String getCompleteness() {
        return completeness;
    }

    public int getTimeGap() {
        return timeGap;
    }

    void setTimeGap(int milliseconds) {
        this.timeGap = milliseconds;
    }

    void setCompleteness(String completeness) {
        this.completeness = completeness;
    }

    void setHome(String home) {
        this.home = home;
    }

    void setSensitiveData(List<String> sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    public List<String> getXssFuzzVectors() {
        return xssFuzzVectors;
    }

    void setXssFuzzVectors(List<String> xssFuzzVectors) {
        this.xssFuzzVectors = xssFuzzVectors;
    }

    public List<String> getIntegerOverflowFuzzVectors() {
        return integerOverflowFuzzVectors;
    }

    void setIntegerOverflowFuzzVectors(
            List<String> integerOverflowFuzzVectors) {
        this.integerOverflowFuzzVectors = integerOverflowFuzzVectors;
    }

    public List<String> getSqlInjectionFuzzVectors() {
        return sqlInjectionFuzzVectors;
    }

    void setSqlInjectionFuzzVectors(List<String> sqlInjectionFuzzVectors) {
        this.sqlInjectionFuzzVectors = sqlInjectionFuzzVectors;
    }

    public List<String> getLdapInjectionFuzzVectors() {
        return ldapInjectionFuzzVectors;
    }

    void setLdapInjectionFuzzVectors(List<String> ldapInjectionFuzzVectors) {
        this.ldapInjectionFuzzVectors = ldapInjectionFuzzVectors;
    }

    public List<String> getXpathInjectionFuzzVectors() {
        return xpathInjectionFuzzVectors;
    }

    void setXpathInjectionFuzzVectors(List<String> xpathInjectionFuzzVectors) {
        this.xpathInjectionFuzzVectors = xpathInjectionFuzzVectors;
    }

    public List<String> getXmlInjectionFuzzVectors() {
        return xmlInjectionFuzzVectors;
    }

    void setXmlInjectionFuzzVectors(List<String> xmlInjectionFuzzVectors) {
        this.xmlInjectionFuzzVectors = xmlInjectionFuzzVectors;
    }

    public List<String> getCommonUsernames() {
        return usernames;
    }

    void setCommonUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public List<String> getCommonPasswords() {
        return passwords;
    }

    void setCommonPasswords(List<String> passwords) {
        this.passwords = passwords;
    }

    void setPagesGuesses(List<String> pageguesses){
        this.pageguesses = pageguesses;
    }

    public List<String> getPageGuesses(){
        return pageguesses;

    }

    public List<String> getAllFuzzVectors() {
        List<String> vectors = new ArrayList<String>();

        vectors.addAll(integerOverflowFuzzVectors);
        vectors.addAll(ldapInjectionFuzzVectors);
        vectors.addAll(sqlInjectionFuzzVectors);
        vectors.addAll(xmlInjectionFuzzVectors);
        vectors.addAll(xpathInjectionFuzzVectors);
        vectors.addAll(xssFuzzVectors);

        return vectors;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "home='" + home + '\'' +
                ", completeness='" + completeness + '\'' +
                ", timeGap=" + timeGap +
                ", sensitiveData=" + sensitiveData +
                ", usernames=" + usernames +
                ", passwords=" + passwords +
                ", pageguesses=" + pageguesses +
                ", xssFuzzVectors=" + xssFuzzVectors +
                ", integerOverflowFuzzVectors=" + integerOverflowFuzzVectors +
                ", sqlInjectionFuzzVectors=" + sqlInjectionFuzzVectors +
                ", ldapInjectionFuzzVectors=" + ldapInjectionFuzzVectors +
                ", xpathInjectionFuzzVectors=" + xpathInjectionFuzzVectors +
                ", xmlInjectionFuzzVectors=" + xmlInjectionFuzzVectors +
                '}';
    }
}

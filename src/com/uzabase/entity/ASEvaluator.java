package com.uzabase.entity;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.uzabase.accessibility.checker.Evaluator;
import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.security.checker.Fuzzer;
import com.uzabase.security.checker.utils.IssueSecurity;
import com.uzabase.security.checker.utils.RuntimeSettings;
import com.uzabase.security.checker.utils.Settings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by Trung on 4/5/2016 11:45 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class ASEvaluator {
    public static Url url;
    public static Document document;
    public static Evaluator evaluator;
    public static List<Issue> issues;
    public static List<IssueSecurity> issueSecurities;
    public static WebClient webClient;
    public static Settings settings;
    public static Fuzzer fuzzer;

    private ASEvaluator() {}

    public static void load(Url url) throws IOException {
    	//System.out.println(url.getUrl());
        ASEvaluator.url= url;
        System.out.println("Start crawWebsite");
        //System.out.println("start test");
        
//        Document doc = Jsoup.connect("http://jsoup.org").get();
//        Element link = doc.select("a").first();
//        String relHref = link.attr("href"); // == "/"
//        String absHref = link.attr("abs:href");
//        System.out.println(relHref+" "+absHref);
//        Document doc = Jsoup.connect("http://www.sis.hust.edu.vn").get();
//        Element link = doc.select("a").first();
//        //String title = doc.title();
//        String absHref = link.attr("abs:href");
//        System.out.println(absHref);
        
        //System.out.println("end test");
//        System.out.println("title: "+title);
        
        crawlWebsite();
        System.out.println("End crawWebsite");
        webClient = new WebClient();
        webClient.setJavaScriptEnabled(false);
        webClient.setThrowExceptionOnScriptError(false);
        webClient.setThrowExceptionOnFailingStatusCode(false);
        webClient.setPrintContentOnFailingStatusCode(false);
        webClient.setJavaScriptTimeout(1000);
        webClient.setTimeout(1000);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        settings = RuntimeSettings.getRuntimeSettings(url);
        System.out.println("End config WebClient");
        fuzzer = new Fuzzer(webClient, settings);
        System.out.println("End create Fuzzer");
    }

    public static Url getUrl() {
        return url;
    }

    public static void setUrl(Url url) {
        ASEvaluator.url = url;
    }

    public static void checkAccessibility() {
        evaluator.addPackage("");
        issues = evaluator.collectIssues(document);
    }

    public static void checkSecurity() throws IOException {
        fuzzer.crawl();
        for (IssueSecurity issue : fuzzer.getIssues())
            if (issue.getSeverity() == IssueSecurity.Severity.ERROR)
                System.out.println(issue);

        webClient.closeAllWindows();
    }

    
    public static void crawlWebsite() throws IOException{// 
        evaluator = new Evaluator();                
        int TIME_OUT = 30000;
        document = Jsoup.parse(new URL(url.getUrl()), TIME_OUT);
        
    }
    
    public static String getUrlSource(String url) throws IOException {
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();

        return a.toString();
    }
}

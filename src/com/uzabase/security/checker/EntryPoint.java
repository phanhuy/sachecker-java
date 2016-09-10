package com.uzabase.security.checker;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.uzabase.entity.Url;
import com.uzabase.security.checker.utils.IssueSecurity;
import com.uzabase.security.checker.utils.RuntimeSettings;
import com.uzabase.security.checker.utils.Settings;

import java.io.IOException;

/**
 * Created by Trung on 4/7/2016 2:38 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class EntryPoint {
    public static void main(String[] args) {

        WebClient webClient = new WebClient();
        webClient.setJavaScriptEnabled(false);
        webClient.setThrowExceptionOnScriptError(false);
        webClient.setThrowExceptionOnFailingStatusCode(false);
        webClient.setPrintContentOnFailingStatusCode(false);
        webClient.setJavaScriptTimeout(800);
        webClient.setTimeout(800);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        Settings settings = null;
        try {        	
            //settings = RuntimeSettings.getRuntimeSettings(new Url("http://localhost:8080/bodgeit/"));
        	settings = RuntimeSettings.getRuntimeSettings(new Url("http://24h.com.vn/"));
        	System.out.println("here1");
        } catch (IOException e) {
        	System.out.println("here2");
            e.printStackTrace();
        }

        Fuzzer fuzzer = new Fuzzer(webClient, settings);

        fuzzer.crawl();
        int error = 0, warning = 0, total =0;
        for (IssueSecurity issue : fuzzer.getIssues()) {
            if (issue.getSeverity() == IssueSecurity.Severity.ERROR)
                error++;
            else if (issue.getSeverity() == IssueSecurity.Severity.WARNING)
                warning++;
            total++;
        }
        System.out.println("Total: " + total + ", Warning: " + warning + ", Error: " + error);
        webClient.closeAllWindows();
    }
}

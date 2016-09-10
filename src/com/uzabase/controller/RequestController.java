package com.uzabase.controller;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.entity.*;
import com.uzabase.security.checker.utils.IssueSecurity;
import com.uzabase.service.ChartService;
import com.uzabase.service.SearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.security.Principal;
import java.util.Set;

/**
 * Created by Trung on 3/29/2016 12:45 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
@Controller
public class RequestController {

    private static final Logger logger = Logger.getLogger(RequestController.class);

    @Value("${message.input.failed}")
    private String inputErrorMessage;

    @Autowired
    private SearchService searchService;

    @Autowired
    private ChartService chartService;

    @Value("${message.search.failed}")
    private String searchFailedMessage;

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public String search(Model model, Principal principal, @ModelAttribute("request") SearchRequest request) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }

        try {
            Set<SearchResult> searchResult = searchService.search(request);
            model.addAttribute("search", searchResult);
        } catch (IOException e) {
            model.addAttribute("error", searchFailedMessage);
            logger.error(searchFailedMessage);
        }

        return "result";
    }

    @RequestMapping(value = {"/check"}, method = RequestMethod.POST)
    public String check(Model model, Principal principal, @ModelAttribute("request") SearchRequest request, @ModelAttribute("option") Option option) {
        System.out.println("Start check page");
        if (principal != null) {
            model.addAttribute("url", request.getKeyword());
        }
        try {
        	System.out.println("hello1"); 
            chartService.check(new Url(request.getKeyword()), option);
            System.out.println("hello2"); 
            StringBuilder stringBuilder = new StringBuilder();
            for (Issue issue: ASEvaluator.issues)
            	stringBuilder.append(issue.toString() + "<br>");
            model.addAttribute("accessibility_errors", stringBuilder);

            StringBuilder stringSC = new StringBuilder();
            for (IssueSecurity issue: ASEvaluator.fuzzer.getIssues())
                stringSC.append(issue.toString() + "<br>");
            model.addAttribute("security_errors", stringSC);

        } catch (IOException e) {
        	System.out.println(e);
            model.addAttribute("error", inputErrorMessage);
            return "home";
        }
        return "index";
    }
}

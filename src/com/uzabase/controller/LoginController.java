package com.uzabase.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.text.MessageFormat;

/**
 * Created by Trung on 3/28/2016 9:19 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
@Controller
@RequestMapping(method = RequestMethod.GET)
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Value("${message.auth.login_failed}")
    private String loginFailedMessage;

    @Value("${message.auth.login_success}")
    private String loginSuccessMessage;

    @RequestMapping(value = {"/login", "/logout"})
    public String login() {
        //return "login";
        return "home";
    }

    @RequestMapping(value = {"/", "/home"})
    public String home(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("username", username.toUpperCase());
            logger.debug(MessageFormat.format(loginSuccessMessage, username));
        }
        return "home";
    }

    @RequestMapping(value = {"/loginfailed"})
    public String loginfailed(Model model) {
        model.addAttribute("error", loginFailedMessage);
        logger.error(loginFailedMessage);
        //return "login";
        return "home";
    }
}
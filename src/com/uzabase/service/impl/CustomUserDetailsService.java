package com.uzabase.service.impl;

import com.uzabase.entity.CustomUserDetails;
import com.uzabase.entity.User;
import com.uzabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by Trung on 3/29/2016 12:44 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
//@Service(value = "customUserDetailsService")
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        User user = userService.getUserByUsername(username);
//        UserDetails userDetails = new CustomUserDetails(user);
//        return userDetails;
//    }
//}
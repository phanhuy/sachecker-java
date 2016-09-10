package com.uzabase.service.impl;

import com.uzabase.dao.UserDAO;
import com.uzabase.entity.User;
import com.uzabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Trung on 3/29/2016 12:18 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}
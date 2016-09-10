package com.uzabase.dao.impl;

import com.uzabase.dao.UserDAO;
import com.uzabase.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Trung on 3/28/2016 9:10 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
@Service
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUserByUsername(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :username");
        query.setParameter("username", username);
        return (User) query.list().get(0);
    }
}
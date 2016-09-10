package com.uzabase.dao;

import com.uzabase.entity.User;

/**
 * Created by Trung on 3/28/2016 9:09 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public interface UserDAO {

    User getUserByUsername(String username);
}
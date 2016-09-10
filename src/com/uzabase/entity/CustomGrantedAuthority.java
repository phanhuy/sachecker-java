package com.uzabase.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Trung on 3/28/2016 9:02 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    public CustomGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
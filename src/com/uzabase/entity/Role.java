package com.uzabase.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Trung on 3/28/2016 8:53 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleAsString() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        if (!id.equals(role1.id)) return false;
        if (!role.equals(role1.role)) return false;
        if (users != null ? !users.equals(role1.users) : role1.users != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", id).
                append("role", role).
                toString();
    }
}


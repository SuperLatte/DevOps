package com.devops.dao;

import com.devops.entity.User;

/**
 * Created by super on 2016/11/6.
 */
public interface UserDao {

    public User getUser(String username, String password);

    public User getUser(String uid);

}

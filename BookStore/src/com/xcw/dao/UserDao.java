package com.xcw.dao;

import com.xcw.pojo.User;

public interface UserDao {
    User queryByName(String name);

    User queryByNameAndPassword(String name, String password);

    int insertUser(User user);
}

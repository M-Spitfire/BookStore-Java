package com.xcw.service;

import com.xcw.pojo.User;

public interface UserService {
    boolean isExistUsername(String username);

    boolean login(User user);

    boolean register(User user);
}

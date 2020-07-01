package com.xcw.service.impl;

import com.xcw.dao.impl.UserDaoImpl;
import com.xcw.pojo.User;
import com.xcw.service.UserService;

public class UserServiceImpl implements UserService {

    UserDaoImpl dao = new UserDaoImpl();

    {
        System.out.println("UserDaoImpl is created at UserServiceImpl.");
    }

    @Override
    public boolean isExistUsername(String username) {
        User user = dao.queryByName(username);
        if(user == null){
            return false;
        }
        return true;
    }

    @Override
    public int login(User user) {
        User queryResult = dao.queryByNameAndPassword(user.getUsername(), user.getPassword());
        if(queryResult != null){
            System.out.println("login success");
            return queryResult.getId();
        }
        System.out.println("login failed");
        return -1;
    }

    @Override
    public boolean register(User user) {
        int effectedRow = dao.insertUser(user);
        if(effectedRow == 1){
//            System.out.println("register success");
            return true;
        }
//        System.out.println("register failed");
        return false;
    }
}

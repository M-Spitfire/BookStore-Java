package com.xcw.test;

import com.xcw.dao.impl.UserDaoImpl;
import com.xcw.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    @Test
    public void queryByName() {
        UserDaoImpl dao = new UserDaoImpl();
        User user = dao.queryByName("虚竹");
        if(user == null){
            System.out.println("you can use this name.");
        }
        else{
            System.out.println("this name is used.");
            System.out.println(user);
        }

    }

    @Test
    public void queryByNameAndPassword() {
        UserDaoImpl dao = new UserDaoImpl();
        User user = dao.queryByNameAndPassword("虚竹", "123456");
        if(user == null){
            System.out.println("failed");
        }
        else{
            System.out.println("success");
            System.out.println(user);
        }
    }
}
package com.xcw.dao.impl;

import com.xcw.dao.UserDao;
import com.xcw.pojo.User;
import com.xcw.utils.jdbcUtils;

import java.sql.Connection;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryByName(String name) {
        String sql = "select id, username, password, email from users where username = ?";
        Connection connection = jdbcUtils.getConnection();
//        System.out.println("*************************");
//        System.out.println(connection);
//        System.out.println("*************************");
        User user = queryForOne(User.class, connection, sql, name);
        jdbcUtils.releaseConnection(connection);
        return user;
    }

    @Override
    public User queryByNameAndPassword(String name, String password) {
        String sql = "select id, username, password, email from users where username = ? and password = ?";
        Connection connection = jdbcUtils.getConnection();
        User user = queryForOne(User.class, connection, sql, name, password);
        jdbcUtils.releaseConnection(connection);
        return user;
    }

    @Override
    public int insertUser(User user) {
        String sql = "insert into users(username,password,email) values(?,?,?);";
        Connection connection = jdbcUtils.getConnection();
        int n = update(connection, sql, user.getUsername(), user.getPassword(), user.getEmail());
        jdbcUtils.releaseConnection(connection);
        return n;
    }
}

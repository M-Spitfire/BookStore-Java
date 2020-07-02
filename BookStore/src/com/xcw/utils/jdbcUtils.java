package com.xcw.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcUtils {

    private static DataSource ds;
    private static ThreadLocal<Connection> conn = new ThreadLocal<>();

    static{
//        System.out.println("****************\n****************");
        try {
            Properties info = new Properties();
//            System.out.println("1");
            info.load(jdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
//            info.load();
//            System.out.println("2");
//            String user = info.getProperty("username");
//            System.out.println(user);
            ds = DruidDataSourceFactory.createDataSource(info);
//            System.out.println(ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection connection = conn.get();
        if(connection == null){
            try {
                connection = ds.getConnection();
                conn.set(connection);
                connection.setAutoCommit(false);//关闭数据库自动提交，开始一个事务
            }
            catch (SQLException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public static void commitAndRelease() {
        Connection connection = conn.get();
        if(connection != null){
            try {
                connection.commit();
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        conn.remove();
    }

    public static void rollbackAndRelease() {
        Connection connection = conn.get();
        if(connection != null){
            try {
                connection.rollback();
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        conn.remove();
    }


    public static void releaseConnection(Connection connection){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

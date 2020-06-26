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
        try {
            return ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

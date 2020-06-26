package com.xcw.test;

import com.xcw.utils.jdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class UtilsTest {
    @Test
    public void TestGetConnection(){
        for(int i = 0; i < 20; i++){
            Connection connection = jdbcUtils.getConnection();
            System.out.println(connection);
            jdbcUtils.releaseConnection(connection);
        }
    }
}

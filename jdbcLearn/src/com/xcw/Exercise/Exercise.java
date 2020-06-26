package com.xcw.Exercise;

import com.xcw.utils.JDBCUtils;
import jdk.internal.util.xml.impl.Input;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Exercise {
    @Test
    public void insertToCustomer(){
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into customers(name,email,birth) values(?,?,?);";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "大威天龙");
            ps.setString(2, "123@123.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("1999-09-09");
            ps.setObject(3, new java.sql.Date(date.getTime()));

            ps.execute();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.free(connection, ps);
        }
    }
}

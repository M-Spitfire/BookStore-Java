package com.xcw.preparedStatement.crud;

import com.sun.org.apache.bcel.internal.generic.DMUL;
import com.xcw.utils.JDBCUtils;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class preparedStatementTest {
    @Test
    public void insertTest() {
        InputStream is = null;
        Properties info = null;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "insert into customers(name,email,birth) values(?,?,?);";
           ps = connection.prepareCall(sql);

            ps.setString(1, "哪吒");
            ps.setString(2, "nezha@outlook.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("1000-10-01");
            ps.setDate(3, new java.sql.Date(date.getTime()));

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.free(connection, ps);
        }

    }

    @Test
    public void update(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "update customers set  name = ? where id = 1";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "汪峰啦啦啦");
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.free(connection, ps);
        }
    }

    @Test
    public void testAlertTable(){
        String sql = "update customers set  name = ? where id = ?";
        alterTable(sql,"汪峰", "1");
    }

    public void alterTable(String sql, Object... args){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i = 1; i <= args.length; i++){
                ps.setObject(i, args[i - 1]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.free(connection, ps);
        }
    }

    @Test
    public  void insert1000(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            connection = JDBCUtils.getConnection();
            String sql = "insert into goods(name) values(?);";
            ps = connection.prepareStatement(sql);
            for(int i = 1; i <= 1000; i++){
                ps.setString(1, "name_" + i);
                ps.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("time scape:" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.free(connection, ps);
        }
    }

    @Test
    public  void insert10002(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            connection = JDBCUtils.getConnection();
            String sql = "insert into goods(name) values(?);";
            ps = connection.prepareStatement(sql);

            connection.setAutoCommit(false);

            for(int i = 1; i <= 1000; i++){
                ps.setString(1, "name_" + i);
                ps.addBatch();
                if(i % 500 == 0){
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }

            connection.commit();

            long end = System.currentTimeMillis();
            System.out.println("time scape:" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.free(connection, ps);
        }
    }
}

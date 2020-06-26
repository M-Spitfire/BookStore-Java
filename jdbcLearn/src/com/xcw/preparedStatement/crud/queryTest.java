package com.xcw.preparedStatement.crud;

import com.xcw.bean.Customer;
import com.xcw.utils.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

public class queryTest {
    @Test
    public void queryTest1(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select id,name,email,birth from customers where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "1");
            resultSet = ps.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = (Date)resultSet.getObject(4);
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.free(connection, ps, resultSet);
        }
    }

    @Test
    public void queryOneTest(){
        String sql = "select id,name,email,birth from customers where id = ?";
        Customer customer = queryOne(sql, 2);
        System.out.println(customer);
    }

    public Customer queryOne(String sql, Object... args){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i = 0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }
            ResultSetMetaData rsmd = ps.getMetaData();
            int n = rsmd.getColumnCount();
            rs = ps.executeQuery();
            if(rs.next()){
                Customer customer = new Customer();
                for(int i = 0; i < n; i++){
                    Object columnValue = rs.getObject(i + 1);
                    String columnName = rsmd.getColumnName(i + 1);
                    Field field = Customer.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(customer, columnValue);
                }
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.free(connection, ps, rs);
        }
        return null;
    }
}

package com.xcw.dao.impl;

import com.xcw.utils.jdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

public abstract class BaseDao {
    private QueryRunner runner = new QueryRunner();

    {
        System.out.println("QueryRunner is created at BaseDao.");
    }

    public int update(Connection connection, String sql, Object...args){
        try {
            return runner.update(connection, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }

    public <T> T queryForOne(Class<T> type, Connection connection, String sql, Object...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            BeanHandler<T> handler = new BeanHandler<>(type);
            return runner.query(connection, sql, handler, args);
//            ps = connection.prepareStatement(sql);
//            for(int i = 0; i < args.length; i++){
//                ps.setObject(i + 1, args[i]);
//            }
//            rs = ps.executeQuery();
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int n = rsmd.getColumnCount();
//            if(rs.next()){
//                T temp = type.newInstance();
//                for(int i = 0; i < n; i++){
//                    Object columnValue = rs.getObject(i + 1);
//                    String columnName = rsmd.getColumnLabel(i + 1);
//                    Field field = type.getDeclaredField(columnName);
//                    field.setAccessible(true);
//                    field.set(temp, columnValue);
//                }
//                return temp;
//            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    public <T> List<T> queryForList(Class<T> type, Connection connection, String sql, Object...args){
        try {
            BeanListHandler<T> handler = new BeanListHandler<>(type);
            return runner.query(connection, sql, handler, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    public Object queryForValue( Connection connection, String sql, Object...args){
        try {
            ScalarHandler handler = new ScalarHandler();
            return runner.query(connection, sql, handler, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
}

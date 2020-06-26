package com.xcw.Dao;

import com.xcw.utils.JDBCUtils;
import jdk.management.resource.ResourceMeter;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    public int update(Connection connection, String sql, Object... args){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            for(int i = 1; i <= args.length; i++){
                ps.setObject(i,args[i - 1]);
            }
            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.free(null, ps);
        }
        return 0;
    }

    public <E> E queryForValue(Connection connection, String sql, Object...args){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            for(int i = 0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return (E) rs.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.free(null, ps);
        }
        return null;
    }

    public <T> List<T> queryForList(Class<T> clazz, Connection connection, String sql, Object... args){
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<T> returnList = null;
        try {
            ps = connection.prepareStatement(sql);
            for(int i = 1; i <= args.length; i++){
                ps.setObject(i, args[i - 1]);
            }
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int n = rsmd.getColumnCount();
            returnList = new ArrayList<>();
            while(rs.next()){
                T temp = clazz.newInstance();
                for(int i = 0; i < n; i++){
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(temp, columnValue);
                }
                returnList.add(temp);
            }
            return returnList;
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.free(null, ps, rs);
        }
        return null;
    }
}

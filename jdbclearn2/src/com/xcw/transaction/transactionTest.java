package com.xcw.transaction;

import com.xcw.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class transactionTest {

    @Test
    public void updateTest() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();

            connection.setAutoCommit(false);

            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            update(connection, sql1, "AA");

            //模拟网络出问题
            int n = 1 / 0;

            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update(connection, sql2, "BB");

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            JDBCUtils.free(connection, null);
        }

    }


    public void update(Connection connection, String sql, Object... args){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            for(int i = 1; i <= args.length; i++){
                ps.setObject(i,args[i - 1]);
            }
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.free(null, ps);
        }
    }
}

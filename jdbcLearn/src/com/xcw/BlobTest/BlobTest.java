package com.xcw.BlobTest;

import com.xcw.bean.Customer;
import com.xcw.utils.JDBCUtils;
import org.junit.Test;

import java.io.*;
import java.sql.*;

public class BlobTest {
    @Test
    public void testInsertBlob(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into customers(name,email,birth,photo) values(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "虚竹");
            ps.setString(2, "xuzhu@qq.com");
            ps.setObject(3, "1991-01-01");
            FileInputStream fis = new FileInputStream(new File("src/img/diamond.jpg"));
            ps.setBlob(4, fis);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.free(connection, ps);
        }
    }

    @Test
    public void queryTest(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from customers where id = ?;";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, 21);

            rs = ps.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date date = rs.getDate("birth");
                Blob img = rs.getBlob("photo");

                Customer customer = new Customer(id,name,email,date);
                System.out.println(customer);

                is = img.getBinaryStream();
                fos = new FileOutputStream("download.jpg");
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) != -1){
                    fos.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            JDBCUtils.free(connection, ps, rs);
        }
    }
}

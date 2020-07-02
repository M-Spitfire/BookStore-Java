package com.xcw.dao.impl;

import com.xcw.dao.OrderDao;
import com.xcw.pojo.Order;
import com.xcw.utils.jdbcUtils;

import java.sql.Connection;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public void saveOrder(Order order) {
        String sql = "insert into orders values(?,?,?,?,?);";
        Connection connection = jdbcUtils.getConnection();
        update(connection, sql,order.getOrderId(), order.getUserId(), order.getCreateTime(), order.getPrice(), order.getStatus());
//        jdbcUtils.releaseConnection(connection);
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select * from orders;";
        Connection connection = jdbcUtils.getConnection();
        return queryForList(Order.class, connection,sql);
    }

    @Override
    public void changeOrderStatus(String orderId, Integer status) {
        String sql = "update orders set status = ? where orderId = ?;";
        Connection connection = jdbcUtils.getConnection();
        update(connection,sql, status, orderId);
//        jdbcUtils.releaseConnection(connection);
    }

    @Override
    public List<Order> queryByUserId(Integer userId) {
        String sql = "select * from orders where userId = ?;";
        Connection connection = jdbcUtils.getConnection();
        return queryForList(Order.class, connection,sql, userId);
    }
}

package com.xcw.dao.impl;

import com.xcw.dao.OrderItemDao;
import com.xcw.pojo.Order;
import com.xcw.pojo.OrderItem;
import com.xcw.utils.jdbcUtils;

import java.sql.Connection;
import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public void saveOrderItem(OrderItem item) {
        String sql = "insert into orderitems(name,count,price,totalPrice,orderId) values(?,?,?,?,?);";
        Connection connection = jdbcUtils.getConnection();
        update(connection, sql,item.getName(),item.getCount(),item.getPrice(),item.getTotalPrice(),item.getOrderId());
//        jdbcUtils.releaseConnection(connection);
    }

    @Override
    public List<OrderItem> queryByOrderId(String orderId) {
        String sql = "select * from orderitems where orderId = ?;";
        Connection connection = jdbcUtils.getConnection();
        return queryForList(OrderItem.class, connection,sql,orderId);
    }
}

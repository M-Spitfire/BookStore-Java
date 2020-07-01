package com.xcw.dao;

import com.xcw.pojo.Order;

import java.util.List;

public interface OrderDao {
    void saveOrder(Order order);
    List<Order> queryOrders();
    void changeOrderStatus(String orderId, Integer status);
    List<Order> queryByUserId(Integer userId);
}

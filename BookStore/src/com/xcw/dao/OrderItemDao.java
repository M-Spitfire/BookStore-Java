package com.xcw.dao;

import com.xcw.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    void saveOrderItem(OrderItem item);
    List<OrderItem> queryByOrderId(String orderId);
}

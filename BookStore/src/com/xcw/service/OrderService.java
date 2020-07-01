package com.xcw.service;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.xcw.pojo.Cart;
import com.xcw.pojo.Order;
import com.xcw.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    String createOrder(Cart cart, Integer userId);
    List<Order> showAllOrders();
    void sendOrder(String OrderId);
    List<OrderItem> showOrderDetail(String orderId);
    List<Order> showMyOrders(Integer userId);
    void receiveOrder(String orderId);
}

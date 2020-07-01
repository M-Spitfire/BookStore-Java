package com.xcw.test;

import com.xcw.pojo.Cart;
import com.xcw.pojo.CartItem;
import com.xcw.pojo.Order;
import com.xcw.pojo.OrderItem;
import com.xcw.service.OrderService;
import com.xcw.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class OrderServiceImplTest {
    OrderService service = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Map<String, CartItem> map = new HashMap<>();
        CartItem item1 = new CartItem(1, "java从入门到放弃", 2, new BigDecimal(80.00));
        CartItem item2 = new CartItem(2, "数据结构与算法", 1, new BigDecimal(78.50));
        map.put("1", item1);
        map.put("2", item2);
        Cart cart = new Cart(map);
        System.out.println(cart);
        service.createOrder(cart, 1);
    }

    @Test
    public void showAllOrders() {
        List<Order> list = service.showAllOrders();
        for(Order order : list){
            System.out.println(order);
        }
    }

    @Test
    public void sendOrder() {
        service.sendOrder("15935393914431");
    }

    @Test
    public void showOrderDetail() {
        List<OrderItem> list = service.showOrderDetail("15935393914431");
        for(OrderItem item : list){
            System.out.println(item);
        }
    }

    @Test
    public void showMyOrders() {
        List<Order> list = service.showMyOrders(1);
        for(Order order : list){
            System.out.println(order);
        }
    }

    @Test
    public void receiveOrder() {
        service.receiveOrder("15935393914431");
    }
}
package com.xcw.test;

import com.xcw.dao.OrderDao;
import com.xcw.dao.impl.OrderDaoImpl;
import com.xcw.pojo.Order;
import org.junit.Test;


import java.lang.invoke.SerializedLambda;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoImplTest {
    OrderDao service = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        String orderId = System.currentTimeMillis() + "00";
        Order order = new Order(orderId, new Date(), new BigDecimal(100.01), 0, 1);
        orderId = System.currentTimeMillis() + "00";
        Order order1 = new Order(orderId, new Date(), new BigDecimal(99.99), 0, 2);
        service.saveOrder(order);
        service.saveOrder(order1);
    }

    @Test
    public void queryOrders() {
        saveOrder();
        List<Order> list = service.queryOrders();
        for(Order order : list){
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        service.changeOrderStatus("159351221429400", 1);
    }

    @Test
    public void queryByUserId() {
        List<Order> list = service.queryByUserId(1);
        for(Order order : list){
            System.out.println(order);
        }
    }
}
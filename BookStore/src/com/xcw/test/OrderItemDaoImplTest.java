package com.xcw.test;

import com.xcw.dao.OrderItemDao;
import com.xcw.dao.impl.OrderItemDaoImpl;
import com.xcw.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {
    OrderItemDao service = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        OrderItem item = new OrderItem(null, "母猪的产后护理", 1, new BigDecimal(99.99), new BigDecimal(99.99), "159351221429400");
        service.saveOrderItem(item);
    }

    @Test
    public void queryByOrderId() {
        List<OrderItem> list = service.queryByOrderId("159351221429400");
        for(OrderItem item : list){
            System.out.println(item);
        }
    }
}
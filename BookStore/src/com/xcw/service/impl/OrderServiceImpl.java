package com.xcw.service.impl;

import com.xcw.dao.BookDao;
import com.xcw.dao.OrderDao;
import com.xcw.dao.OrderItemDao;
import com.xcw.dao.impl.BookDaoImpl;
import com.xcw.dao.impl.OrderDaoImpl;
import com.xcw.dao.impl.OrderItemDaoImpl;
import com.xcw.pojo.*;
import com.xcw.service.OrderService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderServiceImpl implements OrderService {
    OrderDao orderService = new OrderDaoImpl();
    OrderItemDao orderItemService = new OrderItemDaoImpl();
    BookDao bookService = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //生成orderId
        String orderId = System.currentTimeMillis() + "" + userId;
        //创建Order对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //将Order存入数据库
        orderService.saveOrder(order);
        //将购物车中的所有物品存入数据库
        Set<Map.Entry<String, CartItem>> books = cart.getBooks().entrySet();
        for(Map.Entry<String, CartItem> entry : books){
            CartItem item = entry.getValue();
            OrderItem orderItem = new OrderItem(item.getId(),item.getName(),item.getCount(),item.getPrice(), item.getPrice().multiply(new BigDecimal(item.getCount())),orderId);
            orderItemService.saveOrderItem(orderItem);

            //更改销量和库存
            Book book = bookService.queryBookById(item.getId());
            book.setSales(item.getCount() + book.getSales());
            book.setStock(book.getStock() - item.getCount());
            bookService.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderService.queryOrders();
    }

    @Override
    public void sendOrder(String OrderId) {
        orderService.changeOrderStatus(OrderId, 1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemService.queryByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderService.queryByUserId(userId);
    }

    @Override
    public void receiveOrder(String orderId) {
        orderService.changeOrderStatus(orderId,2);
    }
}

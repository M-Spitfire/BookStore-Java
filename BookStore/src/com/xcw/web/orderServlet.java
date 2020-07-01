package com.xcw.web;

import com.alibaba.fastjson.JSON;
import com.xcw.pojo.Cart;
import com.xcw.pojo.Order;
import com.xcw.pojo.OrderItem;
import com.xcw.service.OrderService;
import com.xcw.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderService")
public class orderServlet extends BaseServlet {
    private OrderService service = new OrderServiceImpl();

    /*
    生成订单并保存到数据库，将订单号发送给前端
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp, String data) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Integer userId = (Integer)session.getAttribute("userId");
        String orderId = service.createOrder(cart, userId);
        resp.getWriter().write("{\"orderId\":" + orderId + "}");
    }
    /*
    将所有订单信息发送给前端订单管理界面
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp, String data) throws ServletException, IOException {
        List<Order> list = service.showAllOrders();
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(JSON.toJSONString(list));
    }
    /*
    修改订单状态为已发货
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp, String data) throws ServletException, IOException {
        String orderId = JSON.parseObject(data).getString("orderId");
        service.sendOrder(orderId);
    }
    /*
    将订单中的所有书发送给前端
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp, String data) throws ServletException, IOException {
        String orderId = JSON.parseObject(data).getString("orderId");
        List<OrderItem> list = service.showOrderDetail(orderId);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(JSON.toJSONString(list));

    }
    /*
    将指定用户的所有订单发送给前端
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp, String data) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("userId");
        List<Order> list = service.showMyOrders(userId);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(JSON.toJSONString(list));
    }
    /*
    修改订单状态为已收货
     */
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp, String data) throws ServletException, IOException {
        String orderId = JSON.parseObject(data).getString("orderId");
        service.receiveOrder(orderId);
    }
}

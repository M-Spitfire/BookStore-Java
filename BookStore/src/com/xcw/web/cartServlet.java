package com.xcw.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import com.xcw.pojo.Cart;
import com.xcw.pojo.CartItem;
import sun.util.resources.cldr.ti.CalendarData_ti_ER;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cartService")
public class cartServlet extends BaseServlet {

    protected void addToCart(HttpServletRequest request, HttpServletResponse response, String json) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        CartItem item = JSON.parseObject(json, CartItem.class);
        cart.addItem(item);
        System.out.println(cart);

        response.setCharacterEncoding("utf-8");
        response.getWriter().write("{\"info\":\"add to cart...\"}");
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response, String json){
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Integer id = JSON.parseObject(json).getInteger("id");
//        System.out.println(id);
        cart.deleteItem(id);
    }

    protected void clear(HttpServletRequest request, HttpServletResponse response, String json){
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.clear();
    }

    protected void getCart(HttpServletRequest request, HttpServletResponse response, String json) throws IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(cart));
    }
}

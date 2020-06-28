package com.xcw.web;

import com.alibaba.fastjson.JSON;
import com.xcw.pojo.User;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseServlet extends HttpServlet {

    {
        System.out.println("BaseServlet is created.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("go to doPost()");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");


        //读取json流
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String str;
        StringBuilder sb = new StringBuilder();
        while((str = br.readLine()) != null){
            sb.append(str);
        }
//        User user = JSON.parseObject(sb.toString(), User.class);
//        System.out.println(user);
//        String action = user.getAction();
        String action = (String) JSON.parseObject(sb.toString()).get("action");
//        System.out.println(action);
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class, String.class);//            System.out.println(method);
            method.setAccessible(true);
            method.invoke(this, request, response, sb.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }



}

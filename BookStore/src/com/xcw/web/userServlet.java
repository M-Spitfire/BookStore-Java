package com.xcw.web;

import com.alibaba.fastjson.JSON;
import com.xcw.pojo.User;
import com.xcw.service.impl.UserServiceImpl;
import com.xcw.utils.beanUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

    @WebServlet("/userService")
public class userServlet extends BaseServlet {
        private UserServiceImpl userService = new UserServiceImpl();

        {
            System.out.println("UserServiceImpl is created at userServlet.");
        }

        //json中的state:1:注册成功,2:用户名已存在,3:验证码错误,4:登录成功,5:登陆失败

    protected void register(HttpServletRequest request, HttpServletResponse response, String json)throws IOException, ServletException{
//        System.out.println("registerServlet is visited.");

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//        String code = request.getParameter("code");

//        System.out.println("username:" + username + " password:" + password +
//                " email:" + email + " code:" + code);

//        User user = beanUtils.copyParamToBean(request.getParameterMap(), new User());
//        System.out.println(user);

        User user = JSON.parseObject(json, User.class);
        String code = user.getCode();
//        System.out.println(code);
        String username = user.getUsername();

        response.setCharacterEncoding("utf-8");
        Writer writer = response.getWriter();

        if("abcde".equals(code)){
            if(userService.isExistUsername(username)){
//                System.out.println("username is used");
                writer.write("{\"info\":\"用户名已存在\",\"state\":2}");
//                request.getRequestDispatcher("/pages/user/regist.html").forward(request, response);
            }
            else{
                userService.register(user);
//                System.out.println("register success.");
                writer.write("{\"info\":\"注册成功\",\"state\":1}");
                //页面的跳转交给前端去做了
//                response.sendRedirect("http://localhost:8080/BookStore/pages/user/regist_success.html");
            }
        }
        else{
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("{\"info\":\"验证码错误\",\"state\":3}");
//            request.getRequestDispatcher("/pages/user/regist.html").forward(request, response);
        }
    }

    protected void login(HttpServletRequest request, HttpServletResponse response, String json)throws ServletException,IOException{
        User user = JSON.parseObject(json, User.class);

        String username = user.getUsername();
        String password = user.getPassword();


        boolean isLogin = userService.login(user);
        if(isLogin){
            response.getWriter().write("{\"info\":\"login success\",\"state\":4}");
//            response.sendRedirect("http://localhost:8080/BookStore/pages/user/login_success.html");
        }
        else{
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("{\"info\":\"登陆失败\",\"state\":5}");
//            request.getRequestDispatcher("/pages/user/login.html").forward(request, response);
        }
    }
}

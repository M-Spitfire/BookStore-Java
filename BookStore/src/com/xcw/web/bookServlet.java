package com.xcw.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xcw.pojo.Book;
import com.xcw.pojo.Page;
import com.xcw.service.BookService;
import com.xcw.service.impl.BookServiceImpl;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet("/manager")
public class bookServlet extends BaseServlet{
    BookService service = new BookServiceImpl();

    {
        System.out.println("BookServiceImpl is created at bookServlet");
    }

    protected void addBook(HttpServletRequest request, HttpServletResponse response, String json) throws ServletException, IOException {
        Book book = JSON.parseObject(json, Book.class);
        service.addBook(book);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("{\"info\":\"添加成功\",\"state\":1}");
    }

    protected void deleteBook(HttpServletRequest request, HttpServletResponse response, String json) throws ServletException, IOException {
        Book book = JSON.parseObject(json, Book.class);
        int n = service.deleteBook(book);
        response.setCharacterEncoding("utf-8");
        Writer writer = response.getWriter();
        if(n < 0){
            writer.write("{\"info\":\"操作失败\",\"state\":-1}");
        }
        else{
            writer.write("{\"info\":\"操作成功\",\"state\":0}");
        }
    }

    protected void updateBook(HttpServletRequest request, HttpServletResponse response, String json) throws ServletException, IOException {
        Book book = JSON.parseObject(json, Book.class);
        int n = service.updateBook(book);
        response.setCharacterEncoding("utf-8");
        Writer writer = response.getWriter();
        if(n < 0){
            writer.write("{\"info\":\"保存失败\",\"state\":-1}");
        }
        else{
            writer.write("{\"info\":\"保存成功\",\"state\":0}");
        }
    }

    protected void queryOne(HttpServletRequest request, HttpServletResponse response, String json) throws ServletException, IOException {

    }

    protected void queryByPrice(HttpServletRequest request, HttpServletResponse response, String json) throws ServletException, IOException {
        Page page = JSON.parseObject(json, Page.class);
        System.out.println(page);
        List<Book> list = service.queryByPrice(page);
        System.out.println(JSON.toJSONString(list));
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(list));
    }

    protected void queryForPage(HttpServletRequest request, HttpServletResponse response, String json) throws ServletException, IOException {
        Page page = JSON.parseObject(json, Page.class);
        System.out.println("queryForPage" + page);
        List<Book> list = service.queryForPage(page);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(list));
    }

    protected void getNumOfBooks(HttpServletRequest request, HttpServletResponse response, String json) throws ServletException, IOException {
//        System.out.println("counting...");
        Page page = JSON.parseObject(json, Page.class);
        System.out.println("getNumOfBooks" + page);
        int n = service.getNumOfBooks(page.getMin(), page.getMax());
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(("{\"num\":" + n + '}'));
    }

    protected void getAll(HttpServletRequest request, HttpServletResponse response, String json) throws ServletException, IOException {
//        System.out.println("getAll()");
        List<Book> list = service.queryAll();
        json = JSON.toJSONString(list);
//        System.out.println(json);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);
    }
}

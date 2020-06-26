package com.xcw.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@WebServlet("/download")
public class downloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取文件的文件名（这里写死了）
        String fileName = "user_table.png";

        //4. 读取文件的内容
        ServletContext servletContext = getServletContext();
        InputStream is = servletContext.getResourceAsStream("/img/" + fileName);

        //2. 通过响应头告诉浏览器数据的类型
        String fileType = servletContext.getMimeType("/img/" + fileName);
        response.setContentType(fileType);

        //3. 通过响应头告诉浏览器这是下载的数据
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("数据库表.png", "utf-8"));

        //5. 将文件内容发送出去
        OutputStream os = response.getOutputStream();
        byte[] buffer = new byte[8096];
        int len = 0;
        while((len = is.read(buffer)) != -1){
            os.write(buffer, 0, len);
        }


    }
}

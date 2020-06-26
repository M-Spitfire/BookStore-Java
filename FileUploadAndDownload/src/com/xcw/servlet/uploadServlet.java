package com.xcw.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/fileupload")
public class uploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
        //判断是否是多段二进制流上传，只有文件上传会使用这种形式
        if(ServletFileUpload.isMultipartContent(request)){
            //1. 创建工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();

            //2. 创建解析上传数据的工具类
            ServletFileUpload service = new ServletFileUpload(fileItemFactory);
            service.setHeaderEncoding("UTF-8");

            //3. 解析上传的数据
            List<FileItem> list = null;
            try {
                list = service.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }

            //4. 处理数据
            for(FileItem fileItem : list){
                if(fileItem.isFormField()){
                    System.out.println(fileItem.getFieldName() + ":" + fileItem.getString("UTF-8"));
                }
                else{
                    String fileName = fileItem.getName();
                    System.out.println(fileItem.getFieldName() + ":" + fileName);
                    try {
                        fileItem.write(new File("d:\\upload\\" + fileItem.getName()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

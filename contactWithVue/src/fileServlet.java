import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/file")
public class fileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("receive something");
        if(ServletFileUpload.isMultipartContent(request)){
            System.out.println("receive file upload");
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload service = new ServletFileUpload(fileItemFactory);
            service.setHeaderEncoding("utf-8");
            List<FileItem> list = null;
            try {
                list = service.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            for(FileItem item : list){
                if(item.isFormField()){
                    System.out.println(item.getFieldName() + ":" + item.getString());
                }
                else{
                    try {
                        item.write(new File("d:\\upload\\" + item.getName()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

import com.alibaba.fastjson.JSON;
import jdk.internal.dynalink.linker.ConversionComparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@WebServlet("/chatServlet")
public class chatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("receive a message from post");
        System.out.println(request.getContentType());
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String buffer;
        StringBuilder sb = new StringBuilder();
        while((buffer = br.readLine()) != null){
            sb.append(buffer);
        }
        User user = JSON.parseObject(sb.toString(), User.class);
        System.out.println(user);
        String jsonStr = JSON.toJSONString(user);

        response.getWriter().write(jsonStr);


//        response.getWriter().write("{\"username\":\"john\", \"state\":\"login\",\"infoObject\":{\"username\":\"tom\", \"state\":\"logout\"}}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("receive a message from get");
    }
}

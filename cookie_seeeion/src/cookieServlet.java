import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

    @WebServlet("/cookieServlet")
public class cookieServlet extends BaseServlet {
    protected void createCookie(HttpServletRequest request, HttpServletResponse response, String json) throws ServletException, IOException {
//        System.out.println("cookie is visited");
//        Cookie[] temp = request.getCookies();
//        for( Cookie cookie : temp){
//            System.out.println("cookie-name:" + cookie.getName() + " value:" + cookie.getValue());
//        }

        response.setCharacterEncoding("utf-8");
        // 允许跨域访问的域名：若有端口需写全（协议+域名+端口），若没有端口末尾不用加'/'
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081/");

        // 允许前端带认证cookie：启用此项后，上面的域名不能为'*'，必须指定具体的域名，否则浏览器会提示
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // 提示OPTIONS预检时，后端需要设置的两个常用自定义头
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");

        //创建cookie
        Cookie cookie = new Cookie("name", "john-1");
        //设置cookie的生命长度：0：立即删除，正数：指定秒数之后删除，负数：浏览器关闭之后删除
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
        response.getWriter().write("{\"info\":\"created a cookie\"}");
    }
}

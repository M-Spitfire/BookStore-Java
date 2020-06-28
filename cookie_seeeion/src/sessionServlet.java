import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/sessionServlet")
public class sessionServlet extends BaseServlet {

    protected void createSession(HttpServletRequest request, HttpServletResponse response, String json) throws ServletException, IOException {
        System.out.println("creating session...");

        HttpSession session = request.getSession();

        session.setAttribute("loginStatus", 1);
        System.out.println(session.getAttribute("loginStatus"));
        session.setMaxInactiveInterval(3);
//        session.invalidate();

        Map map = new HashMap();
        map.put("isNew", session.isNew());
        map.put("id", session.getId());
        map.put("loginStatus", session.getAttribute("loginStatus"));
        map.put("maxLive", session.getMaxInactiveInterval());

        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }
}

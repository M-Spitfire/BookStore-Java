import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpSession session = request.getSession();
        boolean loginStatus = (boolean)session.getAttribute("loginStatus");
        if(loginStatus){
            chain.doFilter(req, resp);
        }
        else{
            HttpServletResponse response = (HttpServletResponse) resp;
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("权限不足");
        }
    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {

    }

}

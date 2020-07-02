package com.xcw.filter;

import com.xcw.utils.jdbcUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.cert.TrustAnchor;

@javax.servlet.annotation.WebFilter(filterName = "adminFilter", urlPatterns = "/orderService")
public class adminFilter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {

        try {
            chain.doFilter(req, resp);
            jdbcUtils.commitAndRelease();
        }
        catch (Exception e){
            jdbcUtils.rollbackAndRelease();
            e.printStackTrace();
        }


    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {

    }

}

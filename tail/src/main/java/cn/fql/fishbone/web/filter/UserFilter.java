package cn.fql.fishbone.web.filter;

import cn.fql.fishbone.ErrorInfoEnum;
import cn.fql.fishbone.util.JsonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by fuquanlin on 2016/9/27.
 */
public class UserFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            chain.doFilter(request,response);
        }else{
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            respondWithError(httpRequest,httpResponse, ErrorInfoEnum.AUTHORIZATION_ERROR.getCode(),ErrorInfoEnum.AUTHORIZATION_ERROR.getDesc());
        }

    }

    private void respondWithError(HttpServletRequest request, HttpServletResponse response, int errorCode, String error) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json");
        HashMap result = new HashMap();
        result.put("success", Boolean.valueOf(false));
        result.put("hasError", Boolean.valueOf(true));
        result.put("errorCode", Integer.valueOf(errorCode));
        result.put("error", error);
        response.getWriter().write(JsonUtil.serializeObject(result));
        response.getWriter().flush();
    }

    @Override
    public void destroy() {

    }
}

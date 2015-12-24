package me.daoke.renrenfm.interceptors;


import me.daoke.renrenfm.vo.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 用户登录拦截器
 * @author zhoaym
 * @date 2015/8/19
 */
public class LoginInterceptor implements HandlerInterceptor{

    private Logger logger = Logger.getLogger(LoginInterceptor.class.getName());

    private static String[] urls = {"/sys/login","/sys/toLogin","/sys/loginCheck"};

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse rsp, Object handler) throws Exception {
        UserInfo userInfo = (UserInfo)req.getSession().getAttribute("user");
        if( userInfo == null ){
               logger.log(Level.INFO,"no user information");
               rsp.sendRedirect(req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/sys/login");
               return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse rsp, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse rsp, Object o, Exception e) throws Exception {

    }

}

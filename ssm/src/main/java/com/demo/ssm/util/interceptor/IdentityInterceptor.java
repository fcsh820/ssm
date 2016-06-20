package com.demo.ssm.util.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by fcs on 2015/11/28.
 * 身份认证拦截器
 */
public class IdentityInterceptor implements HandlerInterceptor {

    /**
     * 进行身份认证，在handler执行之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object obj) throws Exception {


        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        //判断是否为公开地址
        String url = request.getRequestURL().toString();
        if(url.contains("login.")) {
            return true;//是公开地址则放行
        }
        //判断用户是否登录
        else if(username != null) {
            return true;
        }
        else {
            //不是公开地址则重定向到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

}

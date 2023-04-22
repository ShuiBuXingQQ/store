package com.cy.store.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;

//定义一个拦截器
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象中是否有uid数据，如果有则放行，如果没有则重定向到login页面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器（url+controller:映射）
     * @return 如果返回值为true 表示放行当前请求，如果为false 则表示拦截当前请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //HttpServletRequest 来获取session对象
        Object obj = request.getSession().getAttribute("uid");
        if (obj == null) {
            //说明用户没有登录过系统，则重定向到login.html页面
            response.sendRedirect("/web/login.html");
            //结束后续调用
            return false;
        }
        //请求放行
        return true;
    }
}

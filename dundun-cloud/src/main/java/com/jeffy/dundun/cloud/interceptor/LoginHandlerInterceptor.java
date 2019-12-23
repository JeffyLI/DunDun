package com.jeffy.dundun.cloud.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginHandlerInterceptor implements HandlerInterceptor  {
    /**
     * preHandle在执行Controller之前执行，返回true，则继续执行Contorller
     * 返回false则请求中断。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("account") != null) {
            return true;
        } else {
            response.sendRedirect("/login/loginPage");//重定向到登陆界面
            return false;
        }
    }

    /**
     * Controller 方法调用之后执行，但是它会在DispatcherServlet 进行视图返回渲染之前被调用，
     *            所以我们可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 顾名思义，该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。
     * 这个方法的主要作用是用于进行资源清理工作的。
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}

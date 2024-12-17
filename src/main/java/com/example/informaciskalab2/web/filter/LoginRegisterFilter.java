//package com.example.informaciskalab2.web.filter;
//import com.example.informaciskalab2.model.User;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebFilter
//public class LoginRegisterFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        User user = (User)request.getSession().getAttribute("user");
//
//        String path = request.getServletPath();
//        if(!"/login".equals(path) && !"/register".equals(path) && !"/main.css".equals(path) && user==null) {
//            response.sendRedirect("/login");
//        }else{
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}

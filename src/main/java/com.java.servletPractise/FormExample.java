package com.java.servletPractise;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FormExample implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest.getParameter("first_name") == null)
        {
            return;
        }
//        preProcessing

        filterChain.doFilter(servletRequest,servletResponse);
//        postProcessing
    }

    @Override
    public void destroy() {
    }
}

package com.nelbosco.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import ch.qos.logback.core.net.SyslogOutputStream;

@Component
public class AdminCheckInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
        if (session.getAttribute("r5T29Ut6IBJ5") == null) {
            response.sendRedirect("/admin/login");            
            return false;
        }else{
        	if(!session.getAttribute("r5T29Ut6IBJ5").equals("vegemil")) {
            	response.sendRedirect("/admin/login");            	
                return false;
            }	
        }
               
        return true;
	}
	
	

}

package com.member.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;

/**
 * Servlet Filter implementation class LoginFilter
 */
//@WebFilter(urlPatterns = { "/*" })
public class LoginFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			String servletPath = req.getServletPath();
			System.out.println("servletPath" + servletPath);
			if (servletPath.startsWith("/resources")||servletPath.startsWith("/login")|| checkLogin(req)) {
				chain.doFilter(request, response);
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("target", req.getServletPath());
				//換成首頁
				RequestDispatcher rd = request.getRequestDispatcher("/login/login.jsp");
				rd.forward(req, resp);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean checkLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO loginToken = (MemberVO) session.getAttribute("user");
		if (loginToken == null) {
			return false;
		} else {
			return true;
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}

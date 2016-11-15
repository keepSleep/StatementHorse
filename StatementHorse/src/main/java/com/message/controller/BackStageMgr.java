package com.message.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsoup.GetMgr;

public class BackStageMgr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int stockno =new Integer(request.getParameter("stockno"));
		int year = new Integer(request.getParameter("year"));
		int month = new Integer(request.getParameter("month"));
		
		GetMgr.insertMgr(year, month, stockno);
		
	}

}

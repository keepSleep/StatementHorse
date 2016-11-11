package com.tracklisting.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.listingdetails.model.ListingDetailsHibernateDAO;
import com.listingdetails.model.ListingDetailsVO;

@WebServlet("/DeleteStock")
public class DeleteStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteStock() {
       
    	super();  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String stockNo = request.getParameter("stockNo");
		String listingNo = request.getParameter("listingNo");
		
		ListingDetailsHibernateDAO lddao = new ListingDetailsHibernateDAO();
		
		lddao.delete(Integer.parseInt(listingNo), Integer.parseInt(stockNo));

		response.getWriter().write("取消追蹤成功");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

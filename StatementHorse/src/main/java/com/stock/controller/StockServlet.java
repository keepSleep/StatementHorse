package com.stock.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stock.model.StockService;
import com.stock.model.StockVO;

/**
 * Servlet implementation class StockServlet
 */
@WebServlet("/StockServlet.do")
public class StockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		PrintWriter out=response.getWriter();
		
		if("getStockName".equals(action)){
		Integer stockNo = new Integer(request.getParameter("stockNo"));
		StockService sS = new StockService();
		StockVO sVO = sS.getOneStock(stockNo);
		out.println(sVO.getStockName());
		out.close();
		}
	}

}

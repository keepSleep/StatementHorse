package com.selectstockmessage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.balancesheet.model.BalanceSheetService;
import com.balancesheet.model.BalanceSheetVO;
import com.incomestatement.model.IncomeStatementService;
import com.incomestatement.model.IncomeStatementVO;
import com.stock.model.StockService;
import com.stock.model.StockVO;

/**
 * Servlet implementation class SelectStatementServlet
 */
@WebServlet("/SelectStatementServlet")
public class SelectStatementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("content-type", "text/html;charset=UTF-8");
		String statementDate = req.getParameter("statement_date");
		String stock = req.getParameter("stock_no");
		
		Integer stockNo = Integer.parseInt(stock);
		StockService stockSvc=new StockService();
		StockVO stockVO=stockSvc.getOneStock(stockNo);
		req.setAttribute("stockVO", stockVO);
	
			IncomeStatementService svc = new IncomeStatementService();
			IncomeStatementVO incomeStatementVO=svc.getOneStockIncomeStatement(stockNo, statementDate);
			req.setAttribute("IncomeStatementVO",incomeStatementVO);
			BalanceSheetService svc1 = new BalanceSheetService();
			BalanceSheetVO balanceSheetVO=svc1.getOneBalanceSheet(stockNo, statementDate);
			req.setAttribute("BalanceSheetVO",balanceSheetVO);
			RequestDispatcher successView = req.getRequestDispatcher("stock/ShowStatement.jsp");
			successView.forward(req, resp);
			return;
		
		
		
	}

}

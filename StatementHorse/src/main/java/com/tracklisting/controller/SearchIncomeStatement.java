package com.tracklisting.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;

import com.financialstatements.model.FinancialStatementsVO;
import com.incomestatement.model.IncomeStatementHibernateDAO;
import com.incomestatement.model.IncomeStatementVO;
import com.listingdetails.model.ListingDetailsHibernateDAO;
import com.listingdetails.model.ListingDetailsVO;

@WebServlet("/SearchIncomeStatement")
public class SearchIncomeStatement extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchIncomeStatement() {
    	
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//接收使用者選擇的清單編號
		String listingNo = request.getParameter("ListingNo");
		
		//使用到的 DAO
		ListingDetailsHibernateDAO lddao = new ListingDetailsHibernateDAO();
		IncomeStatementHibernateDAO isdao = new IncomeStatementHibernateDAO();
		
		//使用到的集合
		ArrayList l1 = new ArrayList();
		
		//查詢清單編號內含股號		
		List<ListingDetailsVO> tdvo = lddao.getAllByListing(Integer.parseInt(listingNo));
		for (ListingDetailsVO tdvo2 : tdvo) {
	
		List<IncomeStatementVO> isvo = isdao.getByStockNo(tdvo2.getStockVO().getStockNo());
		for (IncomeStatementVO isvo2 : isvo) {
			
			Map m1 = new HashMap();
			m1.put("StockNo", isvo2.getStockVO().getStockNo());
			m1.put("StockName", isvo2.getStockVO().getStockName());
			m1.put("StatementDate", isvo2.getStatementDate());
//			m1.put("OperatingRevenue", isvo2.getOperatingRevenue());
//			m1.put("NetIncome", isvo2.getNetIncome());
			l1.add(m1);
					
		 }
			
		}
		
		String jsonString = JSONValue.toJSONString(l1);
		response.getWriter().println(jsonString);
	}

}

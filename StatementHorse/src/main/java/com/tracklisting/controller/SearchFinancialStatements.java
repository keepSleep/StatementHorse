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

import com.financialstatements.model.FinancialStatementsDAO;
import com.financialstatements.model.FinancialStatementsVO;
import com.listingdetails.model.ListingDetailsHibernateDAO;
import com.listingdetails.model.ListingDetailsVO;
import com.stock.model.StockService;
import com.stock.model.StockVO;

@WebServlet("/SearchFinancialStatements")
public class SearchFinancialStatements extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchFinancialStatements() {
    	
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
		FinancialStatementsDAO fsdao = new FinancialStatementsDAO();
		
		//使用到的集合
		ArrayList l1 = new ArrayList();
		ArrayList stockNoArrayList = new ArrayList();
		
		//查詢清單編號內含股號		
		List<ListingDetailsVO> tdvo = lddao.getAllByListing(Integer.parseInt(listingNo));
		for (ListingDetailsVO tdvo2 : tdvo) {

			stockNoArrayList.add(tdvo2.getStockVO().getStockNo());
	
		}
		
		List<Object[]> list = fsdao.getByStockNoByShao(stockNoArrayList);
		
		for(Object[] aArray : list ){
			
			Map m1 = new HashMap();
			m1.put("StockNo", aArray[0]);
			m1.put("StockName",aArray[1]);
			m1.put("StatementDate", aArray[2]);
			m1.put("PostDate", aArray[3].toString());
			l1.add(m1);
			
		}
		
		String jsonString = JSONValue.toJSONString(l1);
		response.getWriter().println(jsonString);
				
	}

}

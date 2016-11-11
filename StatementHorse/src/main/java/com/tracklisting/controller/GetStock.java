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

import com.listingdetails.model.ListingDetailsHibernateDAO;
import com.listingdetails.model.ListingDetailsVO;
import com.stock.model.StockDAO;
import com.stock.model.StockVO;

@WebServlet("/GetStock")
public class GetStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetStock() {
    	
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);

		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		StockDAO sdao = new StockDAO();
		ArrayList l1 = new ArrayList();
		List<StockVO> stock = sdao.getAll();
		
		for (StockVO svo : stock) {
			
			Map m1 = new HashMap();
			m1.put("StockNo", svo.getStockNo());
			m1.put("StockName", svo.getStockName());
			l1.add(m1);		
			
		}
		
		String jsonString = JSONValue.toJSONString(l1);
		response.getWriter().println(jsonString);

	}

}

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
import com.news.model.NewsHibernateDAO;
import com.news.model.NewsVO;
import com.stocknews.model.StockNewsHibernateDAO;
import com.stocknews.model.StockNewsVO;

@WebServlet("/SearchNews")
public class SearchNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SearchNews() {
    	
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
		StockNewsHibernateDAO isdao = new StockNewsHibernateDAO();
		NewsHibernateDAO ndao = new NewsHibernateDAO();
		
		//使用到的集合
		ArrayList l1 = new ArrayList();
		
		//查詢清單編號內含股號		
		List<ListingDetailsVO> tdvo = lddao.getAllByListing(Integer.parseInt(listingNo));
		for (ListingDetailsVO tdvo2 : tdvo) {
			
		List<StockNewsVO> snvo	= isdao.getAllByStockNo(tdvo2.getStockVO().getStockNo());
		for (StockNewsVO snvo2 : snvo) {
		
			Map m1 = new HashMap();
//			m1.put("StockNo", snvo2.getStockVO().getStockNo());
//			m1.put("StockName", snvo2.getStockVO().getStockName());
			m1.put("NewsDate",snvo2.getNewsVO().getNewsDate().toString().substring(0,10));
			m1.put("NewsTitle", snvo2.getNewsVO().getNewsTitle());
			m1.put("NewsWebaddress", snvo2.getNewsVO().getNewsWebaddress());
			l1.add(m1);
		
		}
			
		}
		
		String jsonString = JSONValue.toJSONString(l1);
		response.getWriter().println(jsonString);
		
	}

}
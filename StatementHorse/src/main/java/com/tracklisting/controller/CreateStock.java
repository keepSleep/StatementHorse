package com.tracklisting.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.listingdetails.model.ListingDetailsHibernateDAO;
import com.listingdetails.model.ListingDetailsVO;
import com.stock.model.StockVO;
import com.tracklisting.model.TrackListingHibernateDAO;
import com.tracklisting.model.TrackListingVO;

@WebServlet("/CreateStock")
public class CreateStock extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateStock() {
    	
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String stockNo = request.getParameter("stockNo");
		String listingNo = request.getParameter("listingNo");

		ListingDetailsHibernateDAO lddao = new ListingDetailsHibernateDAO();
		ListingDetailsVO ldvo = new ListingDetailsVO();
		StockVO svo = new StockVO();
		
		try{
			
		svo.setStockNo(Integer.parseInt(stockNo.substring(0, 4)));
		ldvo.setListingNo(Integer.parseInt(listingNo));
		ldvo.setStockVO(svo);;
		lddao.insert(ldvo);
		
		response.getWriter().write("追蹤個股成功");

		}catch(Exception e){
			
			response.getWriter().write("查無此個股!!");
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);	
		
	}

}

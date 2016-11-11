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

@WebServlet("/GetListingDetails")
public class GetListingDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetListingDetails() {
        
    	super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String ListingNo = request.getParameter("ld");
		ListingDetailsHibernateDAO dao = new ListingDetailsHibernateDAO();
		
		List<ListingDetailsVO> tdVO = dao.getAllByListing(Integer.parseInt(ListingNo));
		
		ArrayList l1 = new ArrayList();
		
		for (ListingDetailsVO tdVO2 : tdVO) {

			
			Map m1 = new HashMap();
			m1.put("ListingNo", tdVO2.getTrackListingVO().getListingNo());
			m1.put("StockNo", tdVO2.getStockVO().getStockNo());
			m1.put("StockName", tdVO2.getStockVO().getStockName());
			l1.add(m1);

		}
		
		String jsonString = JSONValue.toJSONString(l1);
		response.getWriter().println(jsonString);

	}

}

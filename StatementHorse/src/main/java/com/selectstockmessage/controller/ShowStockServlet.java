package com.selectstockmessage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.listingdetails.model.ListingDetailsHibernateDAO;
import com.listingdetails.model.ListingDetailsVO;
import com.stock.model.StockService;
import com.stock.model.StockVO;
import com.tojsonarray.model.ToJsonArray;
import com.tracklisting.model.TrackListingHibernateDAO;
import com.tracklisting.model.TrackListingVO;

public class ShowStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setHeader("content-type", "text/html;charset=UTF-8");
		String action = req.getParameter("action");
		String json = req.getParameter("json");
		String need = req.getParameter("need");
		String memberId = req.getParameter("member_id");
		String listNo = req.getParameter("listNo");
		String insert_or_delete = req.getParameter("insert_or_delete");
		Integer stockNo = new Integer(req.getParameter("stock_no"));
	

	
		if ("stock".equals(action)) {
			StockService stockService=new StockService();
			StockVO stockVO=new StockVO();
			stockVO=stockService.getOneStock(stockNo);
			TrackListingHibernateDAO trackdao = new TrackListingHibernateDAO();
			List<TrackListingVO> list = trackdao.getAllByMember(memberId);
			Set<ListingDetailsVO> set = null;
			String listingName=null;
			Set stock_set=null;
			for (TrackListingVO VO1 : list) {
				listingName=VO1.getListingName();
				if(listingName==null){
					VO1.setListingName("我的追蹤清單");
				}
				set = VO1.getLds();
				for (ListingDetailsVO VO2 : set) {
					Integer stock_list = VO2.getStockVO().getStockNo();
					stock_set=new HashSet();
					stock_set.add(stock_list);
				}
			}
			req.setAttribute("stockVO", stockVO);
			req.setAttribute("list", list);
			req.setAttribute("stock_set", stock_set);
			req.setAttribute("StockNo", stockNo);
			RequestDispatcher successView = req.getRequestDispatcher("stock/show.jsp");
			successView.forward(req, resp);
			return;
		}
		if(listNo!=null&&listNo.trim().length()!=0){
//			System.out.println("testqqqqq");
			Integer list_no=new Integer(listNo);
			ListingDetailsHibernateDAO listingdetailsdao = new ListingDetailsHibernateDAO();
			ListingDetailsVO listingDetailsVO=new ListingDetailsVO();
			if("insert".equals(insert_or_delete)){
			TrackListingVO trackListingVO=new TrackListingVO();
			trackListingVO.setListingNo(list_no);
			listingDetailsVO.setTrackListingVO(trackListingVO);
			StockVO stockVO=new StockVO();
			stockVO.setStockNo(stockNo);
			listingDetailsVO.setStockVO(stockVO);
			listingdetailsdao.insert(listingDetailsVO);
			}else if("delete".equals(insert_or_delete)){
				listingdetailsdao.delete(list_no, stockNo);
			}else if("select".equals(insert_or_delete)){
//				System.out.println("good");
				PrintWriter out=resp.getWriter();
				listingDetailsVO=listingdetailsdao.findByPrimaryKey(list_no, stockNo);
				if(listingDetailsVO!=null){
				out.print(listingDetailsVO.getStockVO().getStockNo());
				}else{
					out.print("null");
				}
				
				out.close();
			
			}
		}
		if ("mgrjson".equals(json)) {
			PrintWriter out = resp.getWriter();
			ToJsonArray tojson = new ToJsonArray();
			JSONArray list = tojson.mgrToJson(stockNo);
			out.print(list);
			out.close();
			// http://localhost:8081/Statement_Horse_Hibernate1029/showStockServlet?json=mgrjson&stock_no=2330
		}
		if ("pricejson".equals(json)) {
			PrintWriter out = resp.getWriter();
			ToJsonArray tojson = new ToJsonArray();
			JSONArray list = tojson.priceToJson(stockNo);
			out.print(list);
			out.close();
		}if("incomestatementjson".equals(json)){
			PrintWriter out = resp.getWriter();
			ToJsonArray tojson=new ToJsonArray();
//			System.out.println("test");
			JSONArray list=tojson.incomeStatementToJson(stockNo,need);
			out.print(list);	
			out.close();
		}if("dividendjson".equals(json)){
			PrintWriter out = resp.getWriter();
			ToJsonArray tojson=new ToJsonArray();
			JSONArray list=tojson.dividendToJson(stockNo);
			out.print(list);	
			out.close();
		}
		if("PERjson".equals(json)){
			PrintWriter out = resp.getWriter();
			ToJsonArray tojson=new ToJsonArray();
			JSONArray list=tojson.PERToJson(stockNo);
			out.print(list);	
			out.close();
		}
		if("operatingRevenuejson".equals(json)){
			PrintWriter out = resp.getWriter();
			ToJsonArray tojson=new ToJsonArray();
			JSONArray list=tojson.incomeStatementToJson(stockNo,need);
			out.print(list);	
			out.close();
		}

		if("balancesheetjson".equals(json)){
			PrintWriter out = resp.getWriter();
			ToJsonArray tojson=new ToJsonArray();
			JSONArray list=tojson.balanceSheetToJson(stockNo,need);
			out.print(list);	
			out.close();
		}if("assetsjson".equals(json)){
			PrintWriter out = resp.getWriter();
			ToJsonArray tojson=new ToJsonArray();
			JSONArray list=tojson.AssetsToJson(stockNo,need);
			out.print(list);	
			out.close();
		}
		if("incomejson".equals(json)){
			PrintWriter out = resp.getWriter();
			ToJsonArray tojson=new ToJsonArray();
			JSONArray list=tojson.IncomeToJson(stockNo,need);
			out.print(list);	
			out.close();
		}
	}

}

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
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.listingdetails.model.ListingDetailsHibernateDAO;
import com.listingdetails.model.ListingDetailsVO;
import com.member.model.MemberVO;
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
		String listNo = req.getParameter("listNo");
		String insert_or_delete = req.getParameter("insert_or_delete");
		String stock = req.getParameter("stock_no");
		Integer stockNo = null;
		try {
			if (stock.trim().length() == 4) {
				stockNo = new Integer(stock);
			} else {
				StockService svc = new StockService();
				stockNo = svc.getStockNoByStockName(stock);
			}
			HttpSession session = req.getSession();
			MemberVO memberVO=(MemberVO) session.getAttribute("user");
			String memberId = memberVO.getMemberId();
			

			if ("stock".equals(action)) {
				
				StockService stockService = new StockService();
				StockVO stockVO = new StockVO();
				stockVO = stockService.getOneStock(stockNo);
				try {
					System.out.println(stockVO.getStockNo());
					TrackListingHibernateDAO trackdao = new TrackListingHibernateDAO();
					List<TrackListingVO> list = trackdao.getAllByMember(memberId);
					Set<ListingDetailsVO> set = null;
					String listingName = null;
					Set stock_set = null;
					for (TrackListingVO VO1 : list) {
						listingName = VO1.getListingName();
						if (listingName == null) {
							VO1.setListingName("我的追蹤清單");
						}
						Integer listingno = VO1.getListingNo();
						// set = VO1.getLds();
						// for (ListingDetailsVO VO2 : set) {
						// Integer stock_list = VO2.getStockVO().getStockNo();
						// stock_set=new HashSet();
						// stock_set.add(stock_list);
						// }
						ListingDetailsHibernateDAO listingDetails = new ListingDetailsHibernateDAO();
						List<ListingDetailsVO> listing = listingDetails.getAllByListing(listingno);
						for (ListingDetailsVO VO2 : listing) {
							Integer stock_list = VO2.getStockVO().getStockNo();
							// stock_set=new HashSet();
							// stock_set.add(stock_list);
						}
					}
					req.setAttribute("stockVO", stockVO);
					req.setAttribute("list", list);
					req.setAttribute("stock_set", stock_set);
					req.setAttribute("StockNo", stockNo);
					RequestDispatcher successView = req.getRequestDispatcher("stock/show.jsp");
					successView.forward(req, resp);
					return;
				} catch (NullPointerException e) {
					RequestDispatcher successView = req.getRequestDispatcher("stock/noshow.jsp");
					successView.forward(req, resp);
					return;
				}

			}

			if (listNo != null && listNo.trim().length() != 0) {
				// System.out.println("testqqqqq");
				Integer list_no = new Integer(listNo);
				ListingDetailsHibernateDAO listingdetailsdao = new ListingDetailsHibernateDAO();
				ListingDetailsVO listingDetailsVO = new ListingDetailsVO();
				if ("insert".equals(insert_or_delete)) {
					TrackListingVO trackListingVO = new TrackListingVO();
					trackListingVO.setListingNo(list_no);
					listingDetailsVO.setTrackListingVO(trackListingVO);
					StockVO stockVO = new StockVO();
					stockVO.setStockNo(stockNo);
					listingDetailsVO.setStockVO(stockVO);
					listingdetailsdao.insert(listingDetailsVO);
				} else if ("delete".equals(insert_or_delete)) {
					listingdetailsdao.delete(list_no, stockNo);
				} else if ("select".equals(insert_or_delete)) {
					// System.out.println("good");
					PrintWriter out = resp.getWriter();
					listingDetailsVO = listingdetailsdao.findByPrimaryKey(list_no, stockNo);
					if (listingDetailsVO != null) {
						out.print(listingDetailsVO.getStockVO().getStockNo());
					} else {
						out.print("null");
					}

					out.close();

				}
			}
			if ("getVO".equals(json)) {
				PrintWriter out = resp.getWriter();
				ToJsonArray tojson = new ToJsonArray();
				JSONArray list = tojson.getVO(stockNo, need);
				out.print(list);
				out.close();

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
			}
			if ("incomestatementjson".equals(json)) {
				PrintWriter out = resp.getWriter();
				ToJsonArray tojson = new ToJsonArray();
				// System.out.println("test");
				JSONArray list = tojson.incomeStatementToJson(stockNo, need);
				out.print(list);
				out.close();
			}
			if ("dividendjson".equals(json)) {
				PrintWriter out = resp.getWriter();
				ToJsonArray tojson = new ToJsonArray();
				JSONArray list = tojson.dividendToJson(stockNo);
				out.print(list);
				out.close();
			}
//			if ("PERjson".equals(json)) {
//				PrintWriter out = resp.getWriter();
//				ToJsonArray tojson = new ToJsonArray();
//				JSONArray list = tojson.PERToJson(stockNo);
//				out.print(list);
//				out.close();
//			}
			if ("operatingRevenuejson".equals(json)) {
				PrintWriter out = resp.getWriter();
				ToJsonArray tojson = new ToJsonArray();
				JSONArray list = tojson.incomeStatementToJson(stockNo, need);
				out.print(list);
				out.close();
			}

			if ("balancesheetjson".equals(json)) {
				PrintWriter out = resp.getWriter();
				ToJsonArray tojson = new ToJsonArray();
				JSONArray list = tojson.balanceSheetToJson(stockNo, need);
				out.print(list);
				out.close();
			}
			if ("assetsjson".equals(json)) {
				PrintWriter out = resp.getWriter();
				ToJsonArray tojson = new ToJsonArray();
				JSONArray list = tojson.AssetsToJson(stockNo, need);
				out.print(list);
				out.close();
			}
			if ("incomejson".equals(json)) {
				PrintWriter out = resp.getWriter();
				ToJsonArray tojson = new ToJsonArray();
				JSONArray list = tojson.IncomeToJson(stockNo, need);
				out.print(list);
				out.close();
			}
		} catch (IllegalArgumentException e) {
			RequestDispatcher successView = req.getRequestDispatcher("stock/noshow.jsp");
			successView.forward(req, resp);
			return;
		}
	}
}

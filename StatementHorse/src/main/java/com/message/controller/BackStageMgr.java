package com.message.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.type.descriptor.java.CalendarTypeDescriptor;
import org.json.JSONArray;

import com.balancesheet.model.BalanceSheetVO;
import com.financialstatements.model.FinancialStatementsService;
import com.jsoup.GetIcomeBalanceSheet;
import com.jsoup.GetMgr;
import com.jsoup.GetPrice;
import com.message.model.MsgService;

@WebServlet("/backstage/BackStageMgr")
public class BackStageMgr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		FinancialStatementsService financialstatementservice=new FinancialStatementsService();
		MsgService msgdao = new MsgService();
		
		
		// 營收的爬蟲
		if ("mgr".equals(action)) {
			String stockno = request.getParameter("stockno");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
//			System.out.println(stockno + ":" + year + "/" + month);
			GetMgr.insertMgr(new Integer(year), new Integer(month), new Integer(stockno));
			List list = msgdao.findmgrbystockno(stockno);
			JSONArray json = new JSONArray(list);
			out.print(json);
		}
		
		//歷史股價的爬蟲
		if ("price".equals(action)) {
			String stockno = request.getParameter("stockno");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
//			System.out.println(stockno + ":" + year + "/" + month);
			GetPrice.insertPrice(new Integer(stockno),new Integer(year), new Integer(month),new Integer(day));
			List list = msgdao.findpricebystocknobypricedate(stockno);
			JSONArray json = new JSONArray(list);
			out.print(json);
		}
		
		// 財報的爬蟲
		if ("financialstatements".equals(action)) {
			Integer stockno = new Integer(request.getParameter("stockno"));
			Integer year = new Integer(request.getParameter("year"));
			Integer season =new Integer(request.getParameter("season"));
			System.out.println(stockno + ":" + year + "/" + season);
			JSONArray json=null;
			JSONArray json1=null;
			try {

				financialstatementservice.addFinancialStatements(stockno, (year-1911)+"0"+season, new java.sql.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()).toString().substring(11,16)) ;
					
				
				
				GetIcomeBalanceSheet.Parsing(year, season, stockno);
				List list = msgdao.findbalancesheetbystockno(stockno.toString());
				json = new JSONArray(list);
				List list1 = msgdao.findincomestatementbystockno(stockno.toString());
				json1 = new JSONArray(list);
				out.print(json);
				out.print(json1);
			} catch (Exception e) {
				e.printStackTrace();
				json1 = new JSONArray();
				out.print(json);
			}
		}
		//資產負債表的顯示依時間
		if ("balancesheet".equals(action)) {
			String stockno = request.getParameter("stockno");
			List list = msgdao.findbalancesheetbystockno(stockno);
			JSONArray json = new JSONArray(list);
			out.print(json);
		}
		//損益表的顯示
		if ("incomestatement".equals(action)) {
			String stockno = request.getParameter("stockno");
			List list = msgdao.findincomestatementbystockno(stockno);
			JSONArray json = new JSONArray(list);
			out.print(json);
		}
		//營收的按鈕顯示
		if ("mgrdemoclick".equals(action)) {
			String stockno = request.getParameter("stockno");
			List list = msgdao.findmgrbystockno(stockno);
			JSONArray json = new JSONArray(list);
			out.print(json);
		}
		//營收的失焦顯示
		if ("mgrdemoblur".equals(action)) {
			String stockno = request.getParameter("stockno");
			List list = msgdao.findmgrbystocknobyrevencedate(stockno);
			JSONArray json = new JSONArray(list);
			out.print(json);
		}
		//歷史股價的失焦
		if ("priceblur".equals(action)) {
			String stockno = request.getParameter("stockno");
			List list = msgdao.findpricebystocknobypricedate(stockno);
			JSONArray json = new JSONArray(list);
			out.print(json);
		}
		
	}

}

package com.message.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.financialstatements.model.FinancialStatementsService;
import com.jsoup.GetIcomeBalanceSheet;
import com.jsoup.GetMgr;

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
		String action = request.getParameter("action");
		FinancialStatementsService financialstatementservice=new FinancialStatementsService();
		
		// 營收的爬蟲
		if ("mgr".equals(action)) {
			String stockno = request.getParameter("stockno");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			System.out.println(stockno + ":" + year + "/" + month);
			GetMgr.insertMgr(new Integer(year), new Integer(month), new Integer(stockno));
		}

		// 財報的爬蟲
		if ("financialstatements".equals(action)) {
			Integer stockno = new Integer(request.getParameter("stockno"));
			Integer year = new Integer(request.getParameter("year"));
			Integer season =new Integer(request.getParameter("season"));
			System.out.println(stockno + ":" + year + "/" + season);
			Date postDate=null;
			switch(season){
			case 1:
					postDate=Date.valueOf(year+"-05-01");
					break;
			case 2:
				postDate=Date.valueOf(year+"-08-01");
				break;
			case 3:
				postDate=Date.valueOf(year+"-10-01");
				break;
			case 4:
				postDate=Date.valueOf((year+1)+"-03-01");
				break;
			}
			
			try {
				
				financialstatementservice.addFinancialStatements(stockno, (year-1911)+"0"+season, postDate, "00:00:00");
				
				GetIcomeBalanceSheet.Parsing(year, season, stockno);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

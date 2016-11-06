package com.financialstatements.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.financialstatements.model.FinancialStatementsService;
import com.financialstatements.model.FinancialStatementsVO;
import com.tojsonarray.model.ToJsonArray;

//@WebServlet("/FinancialStatementsServlet")
public class FinancialStatementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FinancialStatementsServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out=res.getWriter();
		String action = req.getParameter("action");
		Long dayLong = null;

		if ("financialstatements".equals(action)) {

			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/

			// 接收日期(格式yyyy-mm-dd)(字串)
			String dayStr = req.getParameter("date");
			java.sql.Date dateParse=null;
			try {
				 dateParse = java.sql.Date.valueOf(dayStr);
			} catch (IllegalArgumentException ie) {

			}
			// Date dateParse = sdf.parse(dayStr);

			// java.sql.Timestamp dateParse =
			// java.sql.Timestamp.valueOf(dayStr);
			System.out.println(dateParse);

			/*************************** 2.開始查詢資料 *****************************************/
			FinancialStatementsService financialStatementsSvc = new FinancialStatementsService();
			List<FinancialStatementsVO> financialStatementsVO=null;
			try{
			financialStatementsVO = financialStatementsSvc.getByPostDate(dateParse);
			}catch(org.hibernate.exception.SQLGrammarException e){
				String url = "InputDate.jsp";
				RequestDispatcher nothingHappend =req.getRequestDispatcher(url);
				nothingHappend.forward(req, res);
			}
			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			if(!financialStatementsVO.isEmpty()){
				req.setAttribute("financialStatementsVO", financialStatementsVO);
				String url = "listFinancialStatements.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} else{
				String url = "NullFinancialStatementsDate.jsp";
				RequestDispatcher nullView = req.getRequestDispatcher(url);
				nullView.forward(req, res);
			}
		}
		
		
		//財報比較查詢最新一筆
		if("findStockFSs".equals(action)){
			Integer stockNo = new Integer(req.getParameter("stockNo"));
			FinancialStatementsService FSS = new FinancialStatementsService();
			FinancialStatementsVO FSVO = FSS.getStockFS(stockNo);
			JSONObject json = new ToJsonArray().getFSToJSON(FSVO);
			
			out.println(json);
			out.close();
		}
	}

}

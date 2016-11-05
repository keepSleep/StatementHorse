package com.financialstatements.controller;

import java.io.IOException;

import java.util.Date;
/*豪哥版
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
*/
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.financialstatements.model.FinancialStatementsService;
import com.financialstatements.model.FinancialStatementsVO;


//@WebServlet("/FinancialStatementsServlet")
public class FinancialStatementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FinancialStatementsServlet() {
        super();

    }


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		Long dayLong=null;
		
		if("financialstatements".equals(action)){
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/

			//接收日期(格式yyyy-mm-dd)
			String day = req.getParameter("date");
			
			//改變格式為yyyymmdd的字串
			day=day.replace("-", "");
			//轉變為Long
			dayLong = dayLong.valueOf(day);
			
			System.out.println(dayLong);

			/***************************2.開始查詢資料*****************************************/
			FinancialStatementsService financialStatementsSvc = new FinancialStatementsService();

			List<FinancialStatementsVO> financialStatementsVO = financialStatementsSvc.getByPostDate(new Date(dayLong));
			
/*豪哥版
			//-------------------注意!!------------------
			List<FinancialStatementsVO> list;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				
				long date = new Date().parse(day);
				java.sql.Date dateParse = (java.sql.Date) sdf.parse(day);
				list = financialStatementsSvc.getByPostDate(dateParse);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			//-------------------到這!!------------------
			 * 
			 */

			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("financialStatementsVO", financialStatementsVO);
			String url = "listFinancialStatements.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
	}

}

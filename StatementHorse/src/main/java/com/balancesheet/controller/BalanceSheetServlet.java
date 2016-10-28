package com.balancesheet.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.balancesheet.model.BalanceSheetService;
import com.balancesheet.model.BalanceSheetVO;

//@WebServlet("/FinancialStatements.do")
public class BalanceSheetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BalanceSheetServlet() {
        super();

    }


	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req,res);
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		String action =req.getParameter("action");      //預存字串，取action
		
		if("select".equals(action)){            //from  inputStockNo.jsp的請求
			List<BalanceSheetVO> list=new LinkedList<>();
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String statementDate = req.getParameter("statementdate");
			/***************************2.開始查詢資料*****************************************/
			for(int i=1;i<3;i++){
			try{
			BalanceSheetService balanceSheetSvc=new BalanceSheetService();
			BalanceSheetVO balanceSheetVO = balanceSheetSvc.getOneBalanceSheet(new Integer(req.getParameter("stockno"+i)), statementDate);
			list.add(balanceSheetVO);
			}catch(NumberFormatException e){
				continue;
			}
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("balanceSheetVO", list); // 資料庫取出的balanceSheetVO物件,存入req
			String url = "listBalanceSheet.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("accountings".equals(action)){
			String BSaccounting=req.getParameter("BSaccounting1");
			
			for(int i=1;i<3;i++){
			System.out.println(BSaccounting+i);
			}
		}
	}

}

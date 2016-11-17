package com.selectstockmessage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.dividend.model.DividendVO;
import com.tojsonarray.model.ToJsonArray;
import com.tojsonarray.model.ToTableJson;

/**
 * Servlet implementation class GetStockTableServlet
 */
@WebServlet("/GetStockTableServlet")
public class GetStockTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("content-type", "text/html;charset=UTF-8");
		
		String json = req.getParameter("json");
		String need = req.getParameter("need");
		String stock = req.getParameter("stock_no");
		Integer stockNo=Integer.parseInt(stock);
		if ("mgrjson".equals(json)) {
			PrintWriter out = resp.getWriter();
			ToTableJson tojson = new ToTableJson();
			JSONArray list = tojson.mgrToJson(stockNo);
			out.print(list);
			out.close();
		}
		if ("pricejson".equals(json)) {
			PrintWriter out = resp.getWriter();
			ToTableJson tojson = new ToTableJson();
			JSONArray list = tojson.priceToJson(stockNo);
			out.print(list);
			out.close();
		}
		if ("incomestatementjson".equals(json)) {
			PrintWriter out = resp.getWriter();
			ToTableJson tojson = new ToTableJson();
			JSONArray list = tojson.incomestatementToJson(stockNo,need);
			out.print(list);
			out.close();
		}
		if ("dividendjson".equals(json)) {
			PrintWriter out = resp.getWriter();
			ToTableJson tojson = new ToTableJson();
			JSONArray list = tojson.dividendToJson(stockNo);
			out.print(list);
			out.close();
		}
		if ("assetsjson".equals(json)) {
			PrintWriter out = resp.getWriter();
			ToTableJson tojson = new ToTableJson();
			JSONArray list = tojson.AssetsToJson(stockNo, need);
			out.print(list);
			out.close();
		}
		if ("incomejson".equals(json)) {
			PrintWriter out = resp.getWriter();
			ToTableJson tojson = new ToTableJson();
			JSONArray list = tojson.IncomeToJson(stockNo, need);
			out.print(list);
			out.close();
		}
	}

}

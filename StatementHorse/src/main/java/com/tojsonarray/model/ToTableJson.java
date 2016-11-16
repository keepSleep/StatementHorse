package com.tojsonarray.model;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.dividend.model.DividendService;
import com.dividend.model.DividendVO;
import com.incomestatement.model.IncomeStatementService;
import com.incomestatement.model.IncomeStatementVO;
import com.mgr.model.MGRService;
import com.mgr.model.MGRVO;
import com.price.model.PriceService;
import com.price.model.PriceVO;

public class ToTableJson {

	public JSONArray mgrToJson(Integer stock_no) {
		MGRService mgrSvc = new MGRService();
		List<MGRVO> MGRSet = mgrSvc.getByStockNo(stock_no);
		List<Map<String,String>> listAll = new LinkedList<Map<String,String>>();
		for(MGRVO element:MGRSet){
			Map map = new Hashtable();
			String revenueDate = element.getRevenueDate();
			Integer revenue_str = element.getRevenue();
			
			String revenue=Integer.toString(revenue_str);
//			System.out.println(revenueDate+","+revenue);
			map.put("RevenueDate",revenueDate );
			map.put("Revenue",revenue);
			listAll.add(map);
		}
		JSONArray list = new JSONArray(listAll);
		return list;
	}
	public JSONArray priceToJson(Integer stock_no) {
		PriceService priceSvc = new PriceService();
		List<PriceVO> PriceSet = priceSvc.getByStockNo(stock_no);
		List<Map<String,String>> listAll = new LinkedList<Map<String,String>>();
		for(PriceVO element:PriceSet){
			Map map = new Hashtable();
			String date = element.getPriceDate();
			Double price_str = element.getClosePrice();
			String price=Double.toString(price_str);
			map.put("Date",date );
			map.put("Price",price);
			listAll.add(map);
		}
		JSONArray list = new JSONArray(listAll);
		return list;
	}
	public JSONArray incomestatementToJson(Integer stock_no,String str) {
		IncomeStatementService Svc = new IncomeStatementService();
		List<IncomeStatementVO> set = Svc.getByStockNo(stock_no);
		List<Map<String,String>> listAll = new LinkedList<Map<String,String>>();
		for(IncomeStatementVO element:set){
			Map map = new Hashtable();
			if("EarningPerShare".equals(str)){
			String date = element.getStatementDate();
			Double earning_str = element.getEarningPerShare();
			String earning=Double.toString(earning_str);
			map.put("Date",date );
			map.put("EarningPerShare",earning);
			listAll.add(map);
			}
		}
		JSONArray list = new JSONArray(listAll);
		return list;
	}
	public JSONArray dividendToJson(Integer stock_no) {
		DividendService Svc = new DividendService();
		List<DividendVO> set = Svc.getByStockNo(stock_no);
		List<Map<String,String>> listAll = new LinkedList<Map<String,String>>();
		for(DividendVO element:set){
			Map map = new Hashtable();
			String date = element.getDividendYear();
			Double dividend_str = element.getDividend();
			String dividend=Double.toString(dividend_str);
			map.put("Date",date );
			map.put("Dividend",dividend);
			listAll.add(map);
			}
		
		JSONArray list = new JSONArray(listAll);
		return list;
	}
	
	public static void main(String[] args) {
		ToTableJson dao=new ToTableJson();
		dao.mgrToJson(2330);

	}

}

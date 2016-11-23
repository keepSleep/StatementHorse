package com.tojsonarray.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.balancesheet.model.BalanceSheetService;
import com.balancesheet.model.BalanceSheetVO;
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
		List<Map<String, String>> listAll = new LinkedList<Map<String, String>>();
		for (MGRVO element : MGRSet) {
			Map map = new Hashtable();
			String revenueDate = element.getRevenueDate();
			Integer revenue_str = element.getRevenue();

			String revenue = Integer.toString(revenue_str);
			// System.out.println(revenueDate+","+revenue);
			map.put("RevenueDate", revenueDate);
			map.put("Revenue", revenue);
			listAll.add(map);
		}
		JSONArray list = new JSONArray(listAll);
		return list;
	}

	public JSONArray priceToJson(Integer stock_no) {
		PriceService priceSvc = new PriceService();
		List<PriceVO> PriceSet = priceSvc.getByStockNo(stock_no);
		List<Map<String, String>> listAll = new LinkedList<Map<String, String>>();
		for (PriceVO element : PriceSet) {
			Map map = new Hashtable();
			String date = element.getPriceDate();
			Double price_str = element.getClosePrice();
			String price = Double.toString(price_str);
			map.put("Date", date);
			map.put("Price", price);
			listAll.add(map);
		}
		JSONArray list = new JSONArray(listAll);
		return list;
	}

	public JSONArray incomestatementToJson(Integer stock_no, String str) {
		IncomeStatementService Svc = new IncomeStatementService();
		List<IncomeStatementVO> set = Svc.getByStockNo(stock_no);
		List<Map<String, String>> listAll = new LinkedList<Map<String, String>>();
		for (IncomeStatementVO element : set) {
			Map map = new Hashtable();
			if ("EarningPerShare".equals(str)) {
				String date = element.getStatementDate();
				Double earning_str = element.getEarningPerShare();
				String earning = Double.toString(earning_str);
				map.put("Date", date);
				map.put("EarningPerShare", earning);
				listAll.add(map);
			}
			if ("profit".equals(str)) {
				String date = element.getStatementDate();
				Double revenue = element.getOperatingRevenue().doubleValue();
				Double margain = element.getOperatingMargain().doubleValue();
				Double income = element.getOperatingIncome().doubleValue();
				Double netIncome = element.getNetIncome().doubleValue();
				Double margain_ratio = Math.rint(margain / revenue * 100);
				Double income_ratio = Math.rint(income / revenue * 100);
				Double netIncome_ratio = Math.rint(netIncome / revenue * 100);
				String margainRatio = Double.toString(margain_ratio);
				String incomeRatio = Double.toString(income_ratio);
				String netIncomeRatio = Double.toString(netIncome_ratio);

				map.put("Date", date);
				map.put("MargainRatio", margainRatio);
				map.put("IncomeRatio", incomeRatio);
				map.put("NetIncomeRatio", netIncomeRatio);
				listAll.add(map);
			}
			if ("income".equals(str)) {
				String date = element.getStatementDate();
				Long revenue = element.getOperatingRevenue() / 1000;
				Long margain = element.getOperatingMargain() / 1000;
				Long income = element.getOperatingIncome() / 1000;
				Long oibt = element.getOibt() / 1000;
				Long netIncome = element.getNetIncome() / 1000;
				map.put("Date", date);
				map.put("Revenue", revenue.toString());
				map.put("Margain", margain.toString());
				map.put("Income", income.toString());
				map.put("Oibt", oibt.toString());
				map.put("NetIncome", netIncome.toString());
				listAll.add(map);
			}
		}
		JSONArray list = new JSONArray(listAll);
		return list;
	}

	public JSONArray dividendToJson(Integer stock_no) {
		DividendService Svc = new DividendService();
		List<DividendVO> set = Svc.getByStockNo(stock_no);
		List<Map<String, String>> listAll = new LinkedList<Map<String, String>>();
		for (DividendVO element : set) {
			Map map = new Hashtable();
			String date = element.getDividendYear();
			Double dividend_str = element.getDividend();
			String dividend = Double.toString(dividend_str);
			map.put("Date", date);
			map.put("Dividend", dividend);
			listAll.add(map);
		}

		JSONArray list = new JSONArray(listAll);
		return list;
	}

	public JSONArray AssetsToJson(Integer stock_no, String str) {
		BalanceSheetService Svc = new BalanceSheetService();
		List<BalanceSheetVO> set = Svc.getByStockNo(stock_no);
		List<Map<String, String>> listAll = new LinkedList<Map<String, String>>();
		for (BalanceSheetVO element : set) {
			Map map = new Hashtable();
			if ("flow".equals(str)) {
				String date = element.getStatementDate();
				Double currentAssets = element.getCurrentAssets().doubleValue();
				Double clurrentLiabilities = element.getCurrentLiabilities().doubleValue();
				Double balance_str = Math.rint(currentAssets / clurrentLiabilities * 100);
				String balance = Double.toString(balance_str);
				map.put("Date", date);
				map.put("Balance", balance);
				listAll.add(map);

			}
			if ("balance".equals(str)) {

				String date = element.getStatementDate();
				Double assets = element.getAssets().doubleValue();
				Double liabilities = element.getLiabilities().doubleValue();
				Double balance_str = Math.rint(liabilities / assets * 100);
				String balance = Double.toString(balance_str);
				map.put("Date", date);
				map.put("Balance", balance);
				listAll.add(map);

			}if("assetsall".equals(str)){
				String date = element.getStatementDate();
				Long currentassets = element.getCurrentAssets()/1000;
				Long fixedassets = element.getFixedAssets()/1000;
				Long assets = element.getAssets()/1000;
				map.put("Date", date);
				map.put("CurrentAssets", currentassets);
				map.put("FixedAssets", fixedassets);
				map.put("Assets", assets);
				listAll.add(map);
			}if("debt".equals(str)){
				String date = element.getStatementDate();
				Long currentLiabilities=element.getCurrentLiabilities()/1000;
				Long longTermLiabilities=element.getLongTermLiabilities()/1000;
				Long assets=element.getAssets()/1000;
				Long liabilities=element.getLiabilities()/1000;
				Long net=assets-liabilities;
				
				
				map.put("Date", date);
				map.put("CurrentLiabilities", currentLiabilities);
				map.put("LongTermLiabilities", longTermLiabilities);
				map.put("Net", net);
				map.put("Assets", assets);
				listAll.add(map);
			}

		}
		JSONArray list = new JSONArray(listAll);
		return list;
	}

	public JSONArray IncomeToJson(Integer stock_no, String str) {
		BalanceSheetService Svc = new BalanceSheetService();
		List<BalanceSheetVO> set = Svc.getByStockNo(stock_no);
		List<Map<String, String>> listAll = new LinkedList<Map<String, String>>();

		JSONArray list = new JSONArray(listAll);
		return list;
	}


	public JSONArray PERToJson(Integer stock_no) {
		List<Map<String, String>> listAll = new LinkedList<Map<String, String>>();
		
		PriceService svc = new PriceService();
		List<Object[]> list1 = svc.getPER(stock_no);
		for (Object[] aArray : list1) {
			Map map = new Hashtable();
			String price = aArray[0].toString();
			
			String date = aArray[1].toString();
			map.put("Date",date);
			map.put("Price", price);
		
			listAll.add(map);
		}
		JSONArray list = new JSONArray(listAll);
		return list;

	}

//	public static void main(String[] args) {
//		ToTableJson dao = new ToTableJson();
//		dao.mgrToJson(2330);
//		
//	}
}

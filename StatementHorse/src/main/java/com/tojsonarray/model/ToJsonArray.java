package com.tojsonarray.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.balancesheet.model.BalanceSheetService;
import com.balancesheet.model.BalanceSheetVO;
import com.dividend.model.DividendService;
import com.dividend.model.DividendVO;
import com.financialstatements.model.FinancialStatementsService;
import com.financialstatements.model.FinancialStatementsVO;
import com.incomestatement.model.IncomeStatementService;
import com.incomestatement.model.IncomeStatementVO;
import com.mgr.model.MGRService;
import com.mgr.model.MGRVO;
import com.price.model.PriceService;
import com.price.model.PriceVO;
import com.stock.model.StockService;

public class ToJsonArray {
	public JSONArray mgrToJson(Integer stock_no) {
		MGRService mgrSvc = new MGRService();
		List<MGRVO> MGRSet = mgrSvc.getByStockNo(stock_no);
		List listAll = new LinkedList();
		for (MGRVO element : MGRSet) {
			List list = new LinkedList();
			String str1 = element.getRevenueDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			long millionSeconds = 0;
			try {
				millionSeconds = sdf.parse(str1).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			list.add(0, millionSeconds);
			list.add(1, element.getRevenue());
			listAll.add(list);

		}

		JSONArray list = new JSONArray(listAll);
		return list;
	}

	public JSONArray priceToJson(Integer stock_no) {
		PriceService priceSvc = new PriceService();
		List<PriceVO> priceList = priceSvc.getByStockNo(stock_no);
		List listAll = new LinkedList();
		for (PriceVO element : priceList) {
			List list = new LinkedList();
			String str1 = element.getPriceDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long millionSeconds = 0;
			try {
				millionSeconds = sdf.parse(str1).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}

			list.add(0, millionSeconds);
			list.add(1, element.getOpenPrice());
			list.add(2, element.getHighestPrice());
			list.add(3, element.getLowestPrice());
			list.add(4, element.getClosePrice());
			listAll.add(list);
		}
		JSONArray list = new JSONArray(listAll);
		return list;
	}

	public JSONArray dividendToJson(Integer stock_no) {
		DividendService dividendSvc = new DividendService();
		List<DividendVO> dividendList = dividendSvc.getByStockNo(stock_no);
		List listAll = new LinkedList();
		for (DividendVO element : dividendList) {
			List list = new LinkedList();
			String str1 = element.getDividendYear();
			Integer str2=Integer.parseInt(str1)+1911;
			str1=Integer.toString(str2);
			str1=str1+"0101";
//			System.out.println(str1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			long millionSeconds = 0;
			try {
				millionSeconds = sdf.parse(str1).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
//			System.out.println(millionSeconds);
			list.add(0, millionSeconds);
			list.add(1, element.getDividend());
			listAll.add(list);
		}
		JSONArray list = new JSONArray(listAll);
		return list;
	}public JSONArray balanceSheetToJson(Integer stock_no) {
		BalanceSheetService balanceSheetSer = new BalanceSheetService();
		List<BalanceSheetVO> balanceSheetList = balanceSheetSer.getByStockNo(stock_no);
		List listAll = new LinkedList();
		for (BalanceSheetVO element : balanceSheetList) {
			List list = new LinkedList();
			String str1 = element.getStatementDate();
			Integer str2=Integer.parseInt(str1)+191100;
			str1=Integer.toString(str2);
//			System.out.println(str1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			long millionSeconds = 0;
			try {
				millionSeconds = sdf.parse(str1).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
//			System.out.println(millionSeconds);
			list.add(0, millionSeconds);
			list.add(1,element.getAssets());		
			
			listAll.add(list);

		}

		JSONArray list = new JSONArray(listAll);
		return list;
	}public JSONArray incomeStatementToJson(Integer stock_no,String str) {
		IncomeStatementService incomeStatementSer = new IncomeStatementService();
		List<IncomeStatementVO> incomeStatementList = incomeStatementSer.getByStockNo(stock_no);
		List listAll = new LinkedList();
		for (IncomeStatementVO element : incomeStatementList) {
			List list = new LinkedList();
			String str1 = element.getStatementDate();
			Integer str2=Integer.parseInt(str1)+191100;
			str1=Integer.toString(str2);
//			System.out.println(str1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			long millionSeconds = 0;
			try {
				millionSeconds = sdf.parse(str1).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//System.out.println(millionSeconds);
			list.add(0, millionSeconds);
			if("earningPerShare".equals(str)){
			list.add(1,element.getEarningPerShare());	
			//System.out.println(element.getEarningPerShare());
			}
			listAll.add(list);

		}

		JSONArray list = new JSONArray(listAll);
		return list;
	}
	
	
	
	//-------------財報比較查詢結果轉JSON--開始--by葉哲-----------------
	public JSONObject getFSToJSON(FinancialStatementsVO FSVO){
		Map map = new Hashtable();
		map.put("stockNo", FSVO.getStockNo());
		for(BalanceSheetVO BSVO :FSVO.getBalanceSheets()){
			map.put("currentAssets", BSVO.getCurrentAssets());
			map.put("fixedAssets", BSVO.getFixedAssets());
			map.put("assets", BSVO.getAssets());
			map.put("currentLiabilities", BSVO.getCurrentLiabilities());
			map.put("longTermLiabilities", BSVO.getLongTermLiabilities());
			map.put("liabilities", BSVO.getLiabilities());
			map.put("capitalStock", BSVO.getCapitalStock());
			map.put("additionalPaidInCapital", BSVO.getAdditionalPaidInCapital());
			map.put("retainedEarnings", BSVO.getRetainedEarnings());
			map.put("otherEquity", BSVO.getOtherEquity());
			map.put("consolidatedNetIncomeAttributedToStockholdersOfTheCompany", BSVO.getConsolidatedNetIncomeAttributedToStockholdersOfTheCompany());
			map.put("totalEquity", BSVO.getTotalEquity());
		};
		for(IncomeStatementVO ISVO:FSVO.getIncomeStatements()){
			map.put("operatingRevenue", ISVO.getOperatingRevenue());
			map.put("operatingCost", ISVO.getOperatingCost());
			map.put("operatingMargain", ISVO.getOperatingMargain());
			map.put("netOperatingMargain", ISVO.getNetOperatingMargain());
			map.put("operatingExpenses", ISVO.getOperatingExpenses());
			map.put("operatingIncome", ISVO.getOperatingIncome());
			map.put("nonOperatingRevenue", ISVO.getNonOperatingRevenue());
			map.put("oibt", ISVO.getOibt());
			map.put("incomeTaxExpense", ISVO.getIncomeTaxExpense());
			map.put("coiat", ISVO.getCoiat());
			map.put("netIncome", ISVO.getNetIncome());
			map.put("oci", ISVO.getOci());
			map.put("currentProfitAndLoss", ISVO.getCurrentProfitAndLoss());
			map.put("earningPerShare", ISVO.getEarningPerShare());
		}
		JSONObject json = new JSONObject(map);
		return json;
	}
	//-------------財報比較查詢結果轉JSON--結束--by葉哲-----------------
}

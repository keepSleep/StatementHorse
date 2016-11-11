package com.tojsonarray.model;

import java.sql.Array;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			Integer str2 = Integer.parseInt(str1) + 1911;
			str1 = Integer.toString(str2);
			str1 = str1 + "0101";
			// System.out.println(str1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			long millionSeconds = 0;
			try {
				millionSeconds = sdf.parse(str1).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// System.out.println(millionSeconds);
			list.add(0, millionSeconds);
			list.add(1, element.getDividend());
			listAll.add(list);
		}
		JSONArray list = new JSONArray(listAll);
		return list;
	}

	public JSONArray incomeStatementToJson(Integer stock_no, String str) {
		List listAll = new LinkedList();
			IncomeStatementService incomeStatementSvc=new IncomeStatementService();
			List<IncomeStatementVO> incomeStatementList = incomeStatementSvc.getByStockNo(stock_no);
			
			for(IncomeStatementVO element:incomeStatementList){
				List elementlist=new LinkedList();
				String str1=element.getStatementDate();
				Integer int1=Integer.parseInt(str1);
				Integer int2=int1+191100;
				String date=Integer.toString(int2);
				elementlist.add(0,date);
				if ("earningPerShare".equals(str)) {
					elementlist.add(1, element.getEarningPerShare());
					// System.out.println(element.getEarningPerShare());
				}if("operatingRevenue".equals(str)){
					elementlist.add(1, element.getOperatingRevenue()/1000000);
				}if("operatingMargain".equals(str)){
					elementlist.add(1, element.getOperatingMargain()/1000000);
				}if("operatingIncome".equals(str)){
					elementlist.add(1, element.getOperatingIncome()/1000000);
				}if("oibt".equals(str)){
					elementlist.add(1, element.getOibt());
				}if("netIncome".equals(str)){
					elementlist.add(1,element.getNetIncome());
				}
				listAll.add(elementlist);
			}
		JSONArray list = new JSONArray(listAll);
		return list;
	}


	public JSONArray PERToJson(Integer stock_no) {
		PriceService priceSvc = new PriceService();
		IncomeStatementService incomeStatementSvc=new IncomeStatementService();
		List<PriceVO> priceList = priceSvc.getByStockNo(stock_no);
		List listAll = new LinkedList();
		for(PriceVO element:priceList){
			List list = new LinkedList();
			Double price=element.getClosePrice();
			String priceDate = element.getPriceDate();
			Integer year=Integer.parseInt(priceDate.substring(0,4));
			Double month=Double.parseDouble(priceDate.substring(5,7));
			Integer season=(int) Math.ceil(month/3.0);
			String yearStr=Integer.toString(year-1911);
			String seasonStr=Integer.toString(season);
			IncomeStatementVO incomeStatementVO=null;
			Double earningPerShare =null;
			try{
			incomeStatementVO=incomeStatementSvc.getOneStockIncomeStatement(stock_no, yearStr+"0"+seasonStr);
			System.out.println(yearStr+"0"+seasonStr);
			earningPerShare = incomeStatementVO.getEarningPerShare();
//			System.out.println(earningPerShare);
			}catch(NullPointerException e){
				seasonStr=Integer.toString(season-1);
				incomeStatementVO = incomeStatementSvc.getOneStockIncomeStatement(stock_no, yearStr+"0"+seasonStr);
//				System.out.println(yearStr+"0"+seasonStr);
				earningPerShare = incomeStatementVO.getEarningPerShare();
			}
			double PER = price/earningPerShare;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long millionSeconds = 0;
			try {
				millionSeconds = sdf.parse(priceDate).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			list.add(0, millionSeconds);
			list.add(1, PER);
			System.out.println(millionSeconds);
			System.out.println(PER);

			listAll.add(list);
		}

		JSONArray list = new JSONArray(listAll);
		return list;
	
	}

	public JSONArray balanceSheetToJson(Integer stock_no,String str) {
		List listAll = new LinkedList();
		FinancialStatementsService financialStatementsSvc = new FinancialStatementsService();
		List<FinancialStatementsVO> financialStatementList=financialStatementsSvc.getOneStock(stock_no);
		List list2=new LinkedList();
		for(FinancialStatementsVO element:financialStatementList){
			List list = new LinkedList();
			Date str1 = element.getPostDate();
			long millionSeconds=0;
			millionSeconds=str1.getTime();
			list.add(0, millionSeconds);
			Set<BalanceSheetVO> balanceSheetVO=element.getBalanceSheets();
			for(BalanceSheetVO set:balanceSheetVO){
				if ("balance".equals(str)) {
					Double assets=set.getAssets().doubleValue();
					Double liabilities=set.getLiabilities().doubleValue();
					Double balance=liabilities/assets;
					list.add(1,balance);
				}
			}
			listAll.add(list);
		}
	
		JSONArray list = new JSONArray(listAll);
		return list;
	}

	public JSONArray ProfitToJson(Integer stock_no, String str){
		//http://localhost:8081/StatementHorse/ShowStockServlet?json=profitjson&need=grossMargin&stock_no=2330
		List listAll = new LinkedList();
		FinancialStatementsService financialStatementsSvc = new FinancialStatementsService();
		List<FinancialStatementsVO> financialStatementList=financialStatementsSvc.getOneStock(stock_no);
//		List list2=new LinkedList();
//		list2.add(0, "季度");
//		list2.add(1, "毛利率");
//		list2.add(2,"稅後淨利率");
////		list2.add(3, "operatingProfitMargin");
//		listAll.add(list2);
		for(FinancialStatementsVO element:financialStatementList){
			List list=new LinkedList();
		
			Date str1 = element.getPostDate();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str2=sdf.format(str1);
			String str3=str2.substring(0,4)+str2.substring(5,7);
			
			long millionSeconds=0;
			millionSeconds=str1.getTime();
			list.add(0, str3);
			
			Set<IncomeStatementVO> incomestatementVO=element.getIncomeStatements();
			for(IncomeStatementVO set:incomestatementVO){
				Long revenueLong =set.getOperatingRevenue();
				Double revenue=revenueLong.doubleValue();
				Long cost=set.getOperatingCost();
				Long expenses = set.getOperatingExpenses();
				Long margain=set.getOperatingMargain();
				if("grossMargin".equals(str)){
					Double grossMargin=(revenue-cost)/revenue;
					list.add(1, grossMargin);
//					Double operatingProfitMargin=(revenue-cost-expenses)/revenue;
//					list.add(3, operatingProfitMargin);
					Double netProfitMargin= set.getNetIncome()/revenue;
					list.add(2,netProfitMargin);
				}

			}
			listAll.add(list);
		}
		JSONArray list = new JSONArray(listAll);
		return list;
	}
	public JSONArray SafetyToJson(Integer stock_no,String str){
		List listAll = new LinkedList();
		FinancialStatementsService financialStatementsSvc = new FinancialStatementsService();
		List<FinancialStatementsVO> financialStatementList=financialStatementsSvc.getOneStock(stock_no);
		List list2=new LinkedList();
		list2.add(0, "季度");
		list2.add(1, "流動比");
		list2.add(2, "速動比");
//		list2.add(3, "operatingProfitMargin");
		listAll.add(list2);
		for(FinancialStatementsVO element:financialStatementList){
			List list=new LinkedList();
		
			Date str1 = element.getPostDate();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str2=sdf.format(str1);
			String str3=str2.substring(0,4)+str2.substring(5,7);
			
			long millionSeconds=0;
			millionSeconds=str1.getTime();
			list.add(0, str3);
			
			Set<BalanceSheetVO> balanceSheetVO=element.getBalanceSheets();
			for(BalanceSheetVO set:balanceSheetVO){
				
				
				
			}
		}
		
		JSONArray list = new JSONArray(listAll);
		return list;
	}
	public JSONArray IncomeToJson(Integer stock_no){
		List listAll = new LinkedList();
		IncomeStatementService Svc=new IncomeStatementService();
		List<IncomeStatementVO> incomeStatementList=Svc.getByStockNo(stock_no);
		Model model1=new Model();
		Model model2=new Model();
		Model model3=new Model();
		Model model4=new Model();
		Model model5=new Model();
		Model model6=new Model();
		ArrayList<String> categories=new ArrayList<String>();
		ArrayList<Long> data2=new ArrayList<Long>();
		ArrayList<Long> data3=new ArrayList<Long>();
		ArrayList<Long> data4=new ArrayList<Long>();
		ArrayList<Long> data5=new ArrayList<Long>();
		ArrayList<Long> data6=new ArrayList<Long>();
		for(IncomeStatementVO element:incomeStatementList){
			String date = element.getStatementDate();
			categories.add(date);
			model1.setCategories(categories);
			
		}
		
		listAll.add(model1);
		for(IncomeStatementVO element:incomeStatementList){
			Long operatingRevenue=element.getOperatingRevenue();
			data2.add(operatingRevenue);
			model2.setName("營收");
			model2.setData(data2);
		}
		listAll.add(model2);
		for(IncomeStatementVO element:incomeStatementList){
			Long operatingMargain=element.getOperatingMargain();
			data3.add(operatingMargain);
			model3.setName("毛利");
			model3.setData(data3);
		}
		listAll.add(model3);
		for(IncomeStatementVO element:incomeStatementList){
			Long operatingIncome=element.getOperatingIncome();
			data4.add(operatingIncome);
			model4.setName("營業利益");
			model4.setData(data4);
		}
		listAll.add(model4);
		for(IncomeStatementVO element:incomeStatementList){
			Long oibt=element.getOibt();
			data5.add(oibt);
			model5.setName("稅前淨利");
			model5.setData(data5);
		}
		listAll.add(model5);
		for(IncomeStatementVO element:incomeStatementList){
			Long netIncome=element.getNetIncome();
			data6.add(netIncome);
			model6.setName("稅後淨利");
			model6.setData(data6);
		}
		listAll.add(model6);
		JSONArray list = new JSONArray(listAll);
		return list;
	}
	public JSONArray AssetsToJson(Integer stock_no){
		List listAll = new LinkedList();
		BalanceSheetService balanceSheetSvc=new BalanceSheetService();
		List<BalanceSheetVO> balanceSheetList=balanceSheetSvc.getByStockNo(stock_no);
		Model model1=new Model();
		Model model2=new Model();
		Model model3=new Model();
		Model model4=new Model();
		Model model5=new Model();
		ArrayList<String> categories=new ArrayList<String>();
		ArrayList<Long> data2=new ArrayList<Long>();
		ArrayList<Long> data3=new ArrayList<Long>();
		ArrayList<Long> data4=new ArrayList<Long>();
		ArrayList<Long> data5=new ArrayList<Long>();
		
		for(BalanceSheetVO element:balanceSheetList){
			String date = element.getStatementDate();
			categories.add(date);
			model1.setCategories(categories);
			
		}
		
		listAll.add(model1);
		for(BalanceSheetVO element:balanceSheetList){
			Long currentAssets=element.getCurrentAssets();
			data2.add(currentAssets);
			model2.setName("流動資產");
			model2.setData(data2);
		}
		listAll.add(model2);
		for(BalanceSheetVO element:balanceSheetList){
			Long fixedAssets=element.getFixedAssets();
			data3.add(fixedAssets);
			model3.setName("固定資產");
			model3.setData(data3);
		}
		listAll.add(model3);
		for(BalanceSheetVO element:balanceSheetList){
			Long assets=element.getAssets();
			data4.add(assets);
			model4.setName("總資產");
			model4.setData(data4);
		}
		listAll.add(model4);
		JSONArray list = new JSONArray(listAll);
		return list;
	}
	
	
	
	public static void main(String[] args) {
	
		
	}


	
	
	
	//-------------財報比較查詢結果轉JSON--開始--by葉哲-----------------
	public JSONObject getFSToJSON(FinancialStatementsVO FSVO){
		Map map = new Hashtable();
		map.put("stockNo", FSVO.getStockVO().getStockNo());
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

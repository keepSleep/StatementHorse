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
	public JSONArray getVO(Integer stock_no,String str){
		List<Map<String,String>> listAll = new LinkedList<Map<String,String>>();
		if(str.equals("getMGRVO")){
			MGRService mgrSvc=new MGRService();
			List<MGRVO> MGRList =mgrSvc.getByStockNo(stock_no);
			for(MGRVO element:MGRList){
				Map map = new Hashtable();
				String revenueDate=element.getRevenueDate();
				Integer revenue_int=element.getRevenue();
				String revenue=Integer.toString(revenue_int);
				map.put("RevenueDate",revenueDate );
				map.put("Revenue",revenue);
				listAll.add(map);
				
			}

			
		}
		
		JSONArray list = new JSONArray(listAll);
		return list;
	}
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
					elementlist.add(1, element.getOperatingRevenue()/1000);
				}if("operatingMargain".equals(str)){
					elementlist.add(1, element.getOperatingMargain()/1000);
				}if("operatingIncome".equals(str)){
					elementlist.add(1, element.getOperatingIncome()/1000);
				}if("oibt".equals(str)){
					elementlist.add(1, element.getOibt()/1000);
				}if("netIncome".equals(str)){
					elementlist.add(1,element.getNetIncome()/1000);
				}
				listAll.add(elementlist);
			}
		JSONArray list = new JSONArray(listAll);
		return list;
	}
	public JSONArray PERToJson(Integer stock_no) {

		List listAll = new LinkedList();
		PriceService svc=new PriceService();
		List<Object[]> list1=svc.getPER(stock_no);
		for(Object[] aArray:list1){
			List elementlist=new LinkedList();
			String price_str=aArray[0].toString();
			Double price=Double.parseDouble(price_str);
			String date=aArray[1].toString();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long millionSeconds = 0;
			try {
				millionSeconds = sdf.parse(date).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			elementlist.add(millionSeconds);
			elementlist.add(price);
			listAll.add(elementlist);
		}
		
		JSONArray list = new JSONArray(listAll);
		return list;

	}
	public JSONArray balanceSheetToJson(Integer stock_no,String str) {
		List listAll = new LinkedList();
		BalanceSheetService balanceSheetSvc=new BalanceSheetService();
		List<BalanceSheetVO> balanceSheetList = balanceSheetSvc.getByStockNo(stock_no);
		
		for(BalanceSheetVO element:balanceSheetList){
			List elementlist=new LinkedList();
			String str1=element.getStatementDate();
			Integer int1=Integer.parseInt(str1);
			Integer int2=int1+191100;
			String date=Integer.toString(int2);
			elementlist.add(0,date);
			if ("currentAssets".equals(str)) {
				elementlist.add(1, element.getCurrentAssets()/1000);
			}if ("fixedAssets".equals(str)) {
				elementlist.add(1, element.getFixedAssets()/1000);
			}if ("assets".equals(str)) {
				elementlist.add(1, element.getAssets()/1000);
			}if("balance".equals(str)){
				Double assets=element.getAssets().doubleValue();
				Double liabilities=element.getLiabilities().doubleValue();
				Double balance=liabilities/assets;
				elementlist.add(1,balance);
			}if("currentLiabilities".equals(str)){
				elementlist.add(1, element.getCurrentLiabilities()/1000);
			}if("longTermLiabilities".equals(str)){
				elementlist.add(1, element.getLongTermLiabilities()/1000);
			}if("net".equals(str)){
				Long net=element.getAssets()-element.getLiabilities();
				elementlist.add(1,net/1000);
			}
			
			
			
			
			listAll.add(elementlist);
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
	public JSONArray IncomeToJson(Integer stock_no,String str){
		List listAll = new LinkedList();
		IncomeStatementService Svc=new IncomeStatementService();
		List<IncomeStatementVO> incomeStatementList=Svc.getByStockNo(stock_no);
		Model model1=new Model();
		Model model2=new Model();
		Model model3=new Model();
		Model model4=new Model();
		Model model5=new Model();
		Model model6=new Model();
		Model model7=new Model();
		Model model8=new Model();
		Model model9=new Model();
		ArrayList<String> categories=new ArrayList<String>();
		ArrayList<Long> data2=new ArrayList<Long>();
		ArrayList<Long> data3=new ArrayList<Long>();
		ArrayList<Long> data4=new ArrayList<Long>();
		ArrayList<Long> data5=new ArrayList<Long>();
		ArrayList<Long> data6=new ArrayList<Long>();
		ArrayList<Double> data7=new ArrayList<Double>();
		ArrayList<Double> data8=new ArrayList<Double>();
		ArrayList<Double> data9=new ArrayList<Double>();
		for(IncomeStatementVO element:incomeStatementList){
			String str1 = element.getStatementDate();
			Integer int1=Integer.parseInt(str1);
			Integer int2=int1+191100;
			String date=Integer.toString(int2);
			categories.add(date);
			Long operatingRevenue=element.getOperatingRevenue();
			Long operatingMargain=element.getOperatingMargain();
			Long operatingIncome=element.getOperatingIncome();
			Long oibt=element.getOibt();
			Long netIncome=element.getNetIncome();
			if("income".equals(str)){
				model1.setCategories(categories);
				data2.add(operatingRevenue);
				model2.setName("營收");
				model2.setData(data2);
				data3.add(operatingMargain);
				model3.setName("毛利");
				model3.setData(data3);
				data4.add(operatingIncome);
				model4.setName("營業利益");
				model4.setData(data4);
				data5.add(oibt);
				model5.setName("稅前淨利");
				model5.setData(data5);
				data6.add(netIncome);
				model6.setName("稅後淨利");
				model6.setData(data6);
			}if("profit".equals(str)){
				model1.setCategories(categories);
//				data2.add(operatingRevenue);
//				model2.setName("營收");
//				model2.setData(data2);
				Double operatingMargain_double=operatingMargain.doubleValue();
				Double operatingRevenue_double=operatingRevenue.doubleValue();
				Double operatingIncome_double=operatingIncome.doubleValue();
				Double netIncome_double=netIncome.doubleValue();
				data7.add(Math.rint(operatingMargain_double/operatingRevenue*100));
				model7.setName("毛利率");
				model7.setData(data7);
				data8.add(Math.rint(operatingIncome_double/operatingRevenue*100));
//				data8.add(operatingIncome_double/operatingRevenue);
				model8.setName("營業利益率");
				model8.setData(data8);
//				data5.add(oibt);
//				model5.setName("稅前淨利");
//				model5.setData(data5);
				data9.add(Math.rint(netIncome_double/operatingRevenue*100));
				model9.setName("淨利率");
				model9.setData(data9);
			}
		}
		if("income".equals(str)){
		listAll.add(model1);
		listAll.add(model2);
		listAll.add(model3);
		listAll.add(model4);
		listAll.add(model5);
		listAll.add(model6);
		}
		if("profit".equals(str)){
		listAll.add(model1);
		listAll.add(model7);
		listAll.add(model8);
		listAll.add(model9);
		}
		JSONArray list = new JSONArray(listAll);
		return list;
	}
	public JSONArray AssetsToJson(Integer stock_no,String str){
		List listAll = new LinkedList();
		BalanceSheetService balanceSheetSvc=new BalanceSheetService();
		List<BalanceSheetVO> balanceSheetList=balanceSheetSvc.getByStockNo(stock_no);
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
		ArrayList<Double> data6=new ArrayList<Double>();
		
			for(BalanceSheetVO element:balanceSheetList){
				String str1 = element.getStatementDate();
				Integer int1=Integer.parseInt(str1);
				Integer int2=int1+191100;
				String date=Integer.toString(int2);
				categories.add(date);
				model1.setCategories(categories);
			}
			listAll.add(model1);
		if("assetsAll".equals(str)){
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
		}if("balance".equals(str)){
			for(BalanceSheetVO element:balanceSheetList){
				Double assets=element.getAssets().doubleValue();
				Double liabilities=element.getLiabilities().doubleValue();
				Double balance=Math.rint(liabilities/assets*100);
				
				data6.add(balance);
				model6.setName("負債比率");
				model6.setData(data6);
			}
			listAll.add(model6);
		}if("flow".equals(str)){
			for(BalanceSheetVO element:balanceSheetList){
				Double currentAssets=element.getCurrentAssets().doubleValue();
				Double clurrentLiabilities=element.getCurrentLiabilities().doubleValue();
				Double balance=Math.rint(currentAssets/clurrentLiabilities*100);
				
				data6.add(balance);
				model6.setName("流動比率");
				model6.setData(data6);
			}
			listAll.add(model6);
		}if("debt".equals(str)){
			for(BalanceSheetVO element:balanceSheetList){
				Long currentLiabilities=element.getCurrentLiabilities();
					data2.add(currentLiabilities);
					model2.setName("流動負債");
					model2.setData(data2);
				}
				listAll.add(model2);
				for(BalanceSheetVO element:balanceSheetList){
					Long longTermLiabilities=element.getLongTermLiabilities();
					data3.add(longTermLiabilities);
					model3.setName("長期負債");
					model3.setData(data3);
				}
				listAll.add(model3);
				for(BalanceSheetVO element:balanceSheetList){
					Long assets=element.getAssets();
					Long liabilities=element.getLiabilities();
					Long net=assets-liabilities;
					data4.add(net);
					model4.setName("淨值");
					model4.setData(data4);
				}
				listAll.add(model4);
				for(BalanceSheetVO element:balanceSheetList){
					Long assets=element.getAssets();
					data5.add(assets);
					model5.setName("總資產");
					model5.setData(data5);
				}
				listAll.add(model5);
				
		}
		
		
		
		JSONArray list = new JSONArray(listAll);
		return list;
	}
	public static void main(String[] args) {
	
		
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

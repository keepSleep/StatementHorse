package com.jsoup;

import java.net.URL;
import java.sql.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.financialstatements.model.FinancialStatementsService;
import com.stock.model.StockService;

public class GetFinancialStatements {

	
	
	//利用股號和年份來查詢今天有沒有新的財報或營收
	//營收走GetMrg.java財報走GetIcomeBalanceSheet.java
	public static void insertnewfinancialstatements(Integer stockno,String year,String month) throws NumberFormatException, Exception{
		FinancialStatementsService financialstatementservice=new FinancialStatementsService();
		
		URL url = new URL("http://mops.twse.com.tw/mops/web/t51sb10?Stp=R1"); 
	     Document xmlDoc =  Jsoup.parse(url, 3000);
	     Elements lists = xmlDoc.select("u");
//	     System.out.println(lists.get(10).text());
	     for(Element list:lists){
	    	 if(list.text().contains(stockno.toString())&&list.text().contains("資產負債表")){
//	    		 System.out.println(new java.sql.Date(System.currentTimeMillis()));
//	    		 System.out.println(list.text().substring(0,5));
//	    		 System.out.println(list.text().substring(list.text().indexOf("-")+1,list.text().indexOf("-")+6));
	    		
	         financialstatementservice.addFinancialStatements(stockno, list.text().substring(list.text().indexOf("-")+1,list.text().indexOf("-")+6), new java.sql.Date(System.currentTimeMillis()), list.text().substring(0,5));
	         
	         GetIcomeBalanceSheet.Parsing(new Integer(year), new Integer(list.text().substring(list.text().indexOf("-")+5,list.text().indexOf("-")+6)), stockno); 
	    	 }
	    	 if(list.text().contains(stockno.toString())&&list.text().contains("營業收入資訊")){
	    		    		  
	    		 GetMgr.insertMgr(new Integer(year),new Integer(month), stockno);
	    	 }
	    	 
	     }
	}
	
	public static void findtodayfinancialstatements() throws NumberFormatException, Exception{
		FinancialStatementsService financialstatementservice=new FinancialStatementsService();
		StockService stockservice=new StockService();
		
		
		URL url = new URL("http://mops.twse.com.tw/mops/web/t51sb10?Stp=R1"); 
	     Document xmlDoc =  Jsoup.parse(url, 3000);
	     Elements lists = xmlDoc.select("u");
	     
	     for(Element list:lists){
//	    	 System.out.println(list.text().contains("資產負債表"));
	    	 if(list.text().contains("資產負債表")){
//	    		System.out.println(list.text().indexOf("(", 6));
//	    		 System.out.println(list.text().substring(list.text().indexOf("(", 6)+1,list.text().indexOf("(", 6)+5));
	    		 
	    		 String stockno = list.text().substring(list.text().indexOf("(", 6)+1,list.text().indexOf("(", 6)+5);
	    		 String statementdate=list.text().substring(list.text().indexOf("-")+1,list.text().indexOf("-")+6);
	    		 Date postdate = new java.sql.Date(System.currentTimeMillis());
	    		 String posttime=list.text().substring(0,5);
	    		 stockservice.insert(new Integer(stockno));
	    		 financialstatementservice.addFinancialStatements(new Integer(stockno), statementdate, postdate, posttime);
	    	 }
	    	 
	     }
	
	}
	
	
	public static void main(String[] args) throws NumberFormatException, Exception {
		
		findtodayfinancialstatements();
	}

}

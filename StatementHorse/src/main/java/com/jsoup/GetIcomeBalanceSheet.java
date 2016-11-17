package com.jsoup;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.balancesheet.model.BalanceSheetDAO;
import com.balancesheet.model.BalanceSheetVO;
import com.incomestatement.model.IncomeStatementDAO;
import com.incomestatement.model.IncomeStatementVO;
import com.stock.model.StockVO;

public class GetIcomeBalanceSheet {

	
	//有財報才來這邊 新增資產負債表和損益表的動作
	 public static void Parsing(Integer year,Integer season,Integer stockno) throws Exception {
		 	BalanceSheetVO balancesheetvo = new BalanceSheetVO();
		 	IncomeStatementVO incomestatementvo = new IncomeStatementVO();
		 	StockVO stockvo=new StockVO();
		 	BalanceSheetDAO balancesheetdao = new BalanceSheetDAO();
		 	IncomeStatementDAO incomestatementdao = new IncomeStatementDAO();
		 	
	        URL url = new URL("http://mops.twse.com.tw/server-java/t164sb01?step=1&CO_ID="+stockno+"&SYEAR="+year+"&SSEASON="+season+"&REPORT_ID=C"); 
	        Document xmlDoc =  Jsoup.parse(url, 5000); //使用Jsoup jar 去解析網頁
	        //(要解析的文件,timeout)
//	        Elements title = xmlDoc.select("title"); //要解析的tag元素為title
	        Elements tdtext = xmlDoc.select("td");  //要解析的tag元素為td
//	        for(int i=0;i<100;i++)
//	        System.out.println(happy.get(i));
//	        System.out.println(happy.get(50).text());
	        //資產負債表
	        long current_assets=0;
	        long fixed_assets=0;
	        long assets=0;
	        long current_liabilities=0;
	        long long_term_liabilities=0;
	        long liabilities=0;
	        long capital_stock=0;
	        long additional_paid_in_capital=0;
	        long retained_earnings=0;
	        long other_equity=0;
	        long Consolidated_Net_Income_Attributed_to_Stockholders_of_the_Company=0;
	        long total_equity=0;
	        
	        //損益表
	        long operating_revenue=0;
	        long operating_cost=0;
	        long operating_margain=0;
	        long net_operating_margain=0;
	        long operating_expenses=0;
	        long operating_income=0;
	        long non_operating_revenue=0;
	        long oibt=0;
	        long income_tax_expense=0;
	        long coiat=0;
	        long net_income=0;
	        long oci=0;
	        long current_profit_and_loss=0;
	        double earning_per_share=0.0;
	        
	        int i=0;
	        for(Element s:tdtext){	
	        	
	        	//資產負債表
	        	if(s.text().trim().contains(" 流動資產合計")){
	        		current_assets=new Long(tdtext.get(i+1).text().replace(",",""));
	        	System.out.println("流動資產合計="+current_assets);
	        	}
	        	
	        	if(s.text().trim().contains(" 非流動資產合計")){
	        		fixed_assets=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("非流動資產合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	
	        	if(s.text().trim().contains(" 資產總計")){
	        		assets=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("資產總計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	
	        	if(s.text().trim().contains(" 資產總額")){
	        		assets=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("資產總額="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	
	        	if(s.text().trim().contains(" 流動負債合計")){
	        		current_liabilities=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("流動負債合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 非流動負債合計")){
	        		long_term_liabilities=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("非流動負債合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 負債總計")){
	        		liabilities=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("負債總計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 負債總額")){
	        		liabilities=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("負債總額="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 股本合計")){
	        		capital_stock=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("股本合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 資本公積合計")){
	        		additional_paid_in_capital=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("資本公積合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 保留盈餘合計")){
	        		retained_earnings=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("保留盈餘合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 其他權益合計")){
	        		other_equity=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("其他權益合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 歸屬於母公司業主之權益合計")){
	        		Consolidated_Net_Income_Attributed_to_Stockholders_of_the_Company=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("歸屬於母公司業主之權益合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 權益總額")){
	        		total_equity=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("權益總額="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	
	        	//損益表
	        	
	        	if(s.text().trim().contains(" 營業收入合計")){
	        		operating_revenue=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("營業收入合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 營業成本合計")){
	        		operating_cost=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("營業成本合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 營業毛利（毛損）")){
	        		operating_margain=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("營業毛利（毛損）="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 營業毛利（毛損）淨額")){
	        		net_operating_margain=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("營業毛利（毛損）淨額="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 營業費用合計")){
	        		operating_expenses=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("營業費用合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 營業利益（損失）")){
	        		operating_income=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("營業利益（損失）="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 營業外收入及支出合計")){
	        		non_operating_revenue=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("營業外收入及支出合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 繼續營業單位稅前淨利（淨損）")){
	        		oibt=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("繼續營業單位稅前淨利（淨損）="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 所得稅費用（利益）合計")){
	        		income_tax_expense=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("所得稅費用（利益）合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 繼續營業單位本期淨利（淨損）")){
	        		coiat=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("繼續營業單位本期淨利（淨損）="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 本期淨利（淨損）")){
	        		net_income=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("本期淨利（淨損）="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 其他綜合損益（淨額）")){
	        		oci=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("其他綜合損益（淨額）="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 本期綜合損益總額")){
	        		current_profit_and_loss=new Long(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("本期綜合損益總額="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	if(s.text().trim().contains(" 稀釋每股盈餘合計")){
	        		earning_per_share=new Double(tdtext.get(i+1).text().replace(",",""));
		        	System.out.println("稀釋每股盈餘合計="+tdtext.get(i+1).text().replace(",",""));
		       	}
	        	
	        	
	        	i++;
	        }
	        stockvo.setStockNo(stockno);
	        
	        balancesheetvo.setStockVO(stockvo);
	        balancesheetvo.setStatementDate(""+(year-1911)+"0"+season);
	        balancesheetvo.setCurrentAssets(current_assets);
	        balancesheetvo.setFixedAssets(fixed_assets);
	        balancesheetvo.setAssets(assets);
	        balancesheetvo.setCurrentLiabilities(current_liabilities);
	        balancesheetvo.setLongTermLiabilities(long_term_liabilities);
	        balancesheetvo.setLiabilities(liabilities);
	        balancesheetvo.setCapitalStock(capital_stock);
	        balancesheetvo.setAdditionalPaidInCapital(additional_paid_in_capital);
	        balancesheetvo.setRetainedEarnings(retained_earnings);
	        balancesheetvo.setOtherEquity(other_equity);
	        balancesheetvo.setConsolidatedNetIncomeAttributedToStockholdersOfTheCompany(Consolidated_Net_Income_Attributed_to_Stockholders_of_the_Company);
	        balancesheetvo.setTotalEquity(total_equity);
	        
//	        balancesheetdao.insert(balancesheetvo);
	        
	        incomestatementvo.setStockVO(stockvo);
	        incomestatementvo.setStatementDate(""+(year-1911)+"0"+season);
	        incomestatementvo.setOperatingRevenue(operating_revenue);
	        incomestatementvo.setOperatingCost(operating_cost);
	        incomestatementvo.setOperatingMargain(operating_margain);
	        incomestatementvo.setNetOperatingMargain(net_operating_margain);
	        incomestatementvo.setOperatingExpenses(operating_expenses);
	        incomestatementvo.setOperatingIncome(operating_income);
	        incomestatementvo.setNonOperatingRevenue(non_operating_revenue);
	        incomestatementvo.setOibt(oibt);
	        incomestatementvo.setIncomeTaxExpense(income_tax_expense);
	        incomestatementvo.setCoiat(coiat);
	        incomestatementvo.setNetIncome(net_income);
	        incomestatementvo.setOci(oci);
	        incomestatementvo.setCurrentProfitAndLoss(current_profit_and_loss);
	        incomestatementvo.setEarningPerShare(earning_per_share);;
	        
	        incomestatementdao.insert(incomestatementvo);
//	        System.out.println("Title is "+title.get(0).text()); //得到title tag的內容
//	        System.out.print(happy.get(20).text()+",");
//	        System.out.print(happy.get(21).text()+",");
//	        System.out.print(happy.get(22).text()+",");
//	        System.out.print(happy.get(23).text()+",");
//	        System.out.print(happy.get(24).text()+",");
//	        System.out.println();
	        //得到td tag的內容
	        //注意: 因為有好多個td 我想要取得的是&amp;amp;amp;amp;amp;amp;amp;lt;td&amp;amp;amp;amp;amp;amp;amp;gt;樂&amp;amp;amp;amp;amp;amp;amp;lt;/td&amp;amp;amp;amp;amp;amp;amp;gt; 是第2個td 所以填get(1)
	 
	    }
	 public static void main(String[] args) throws Exception {

			for (int y = 2013; y <= 2016; y++) {
				for (int s = 1; s <= 4; s++) {
					Parsing(y, s, 1101);
				}
			}
		}
}

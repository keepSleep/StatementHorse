package com.jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mgr.model.MGRService;

public class GetMgr {
	
	//有營收才會來這邊 作新增營收的動作
	public static void insertMgr(Integer year,Integer month,Integer stockno) throws IOException{
		MGRService mgrservice = new MGRService();
		
		
		
		URL url = new URL("http://mops.twse.com.tw/mops/web/t05st10_ifrs?encodeURIComponent=1&step=1&firstin=1&off=1&keyword4=&code1=&TYPEK2=&checkbtn=&queryName=co_id&inpuType=co_id&TYPEK=all&isnew=false&co_id="+stockno+"&year="+(year-1911)+"&month="+String.format("%02d", month)); 
			   
		Document xmlDoc =  Jsoup.parse(url, 3000);
	     
	     Elements trs = xmlDoc.select("tr");
	     for(Element tr:trs){
	    	 System.out.println(tr.text());
//	    	 System.out.println(tr.children().get(0).text());
	    	 if(tr.children().get(0).text().equals("本月")){
	    		 System.out.println(tr.text().substring(5).replace(",",""));
	    		 mgrservice.insert(stockno, year+""+String.format("%02d", month), new Integer(tr.text().substring(5).replace(",","")), new java.sql.Date(System.currentTimeMillis()).toString());
	    	 }
	     }
	}
	
	public static void main(String[] args) throws IOException {
		insertMgr(2016,10,1476);

	}

}

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

	// 有營收才會來這邊 作新增營收的動作
	public static void insertMgr(Integer year, Integer month, Integer stockno) throws IOException {
	//	Thread thread = new Thread();
		
		MGRService mgrservice = new MGRService();

		URL url = new URL(
				"http://mops.twse.com.tw/mops/web/t05st10_ifrs?encodeURIComponent=1&step=1&firstin=1&off=1&keyword4=&code1=&TYPEK2=&checkbtn=&queryName=co_id&inpuType=co_id&TYPEK=all&isnew=false&co_id="
						+ stockno + "&year=" + (year - 1911) + "&month=" + String.format("%02d", month));

		Document xmlDoc = Jsoup.parse(url, 20000);

		Elements trs = xmlDoc.select("tr");
		for (Element tr : trs) {
			System.out.println(tr.text());
			// System.out.println(tr.children().get(0).text());
			if (tr.children().get(0).text().equals("本月")) {
				System.out.println(tr.text().substring(5).replace(",", ""));
				mgrservice.insert(stockno, year + "" + String.format("%02d", month),
						new Integer(tr.text().substring(5).replace(",", "")),
						new java.sql.Date(System.currentTimeMillis()).toString());
			}
		}
	}


	public static void main(String[] args) throws IOException, InterruptedException {
		int[] stock = new int[] { 1101, 1102, 1216, 1301, 1303, 1326, 1402, 1476, 2002, 2105, 2207, 2227, 2301, 2303,
				2308, 2311, 2317, 2325, 2330, 2354, 2357, 2382, 2395, 2408, 2409, 2412, 2454, 2474, 2801, 2880, 2881,
				2882, 2883, 2884, 2885, 2886, 2887, 2890, 2891, 2892, 2912, 3008, 3045, 3474, 3481, 4904, 4938, 5880,
				6505, 9904 };

		for (int i = 2; i < stock.length; i++) {
			for (int y = 2013; y <= 2016; y++) {
				for (int m = 1; m <= 12; m++) {
					try{
					insertMgr(y, m, stock[i]);
					Thread.sleep(3000);
					}catch(java.net.ConnectException e){
						continue;
					}
				}

			}
		}

	}

}

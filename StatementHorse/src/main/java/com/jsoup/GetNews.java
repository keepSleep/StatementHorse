package com.jsoup;

import java.io.IOException;
import java.sql.Date;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.news.model.NewsService;
import com.stocknews.model.StockNewsService;
import com.sun.jmx.snmp.Timestamp;

import sun.security.timestamp.Timestamper;

public class GetNews {

	public static void getnewsbystock(Integer stockno, Integer page) throws IOException {
		NewsService newsservice = new NewsService();
		StockNewsService stocknewsservice = new StockNewsService();
		Connection connect = Jsoup.connect("https://tw.stock.yahoo.com/q/h?s=" + stockno + "&pg=" + page)
				.userAgent("UA").timeout(5000);
		Response resp = connect.execute();
		if (resp.statusCode() == 200) {
			Document doc = connect.get();
			Elements lists = doc.select("td");
			int i = 0;
			a: for (Element list : lists) {
				if (list.text().equals("個　股　新　聞　與　研　究　報　告")) {
					int j = 5;
					Date newsDate = null;
					String newsTitle = null;
					String newsWebaddress = null;
					while (true) {

						if (j % 6 == 0) {
							System.out.println(lists.get(i + j).text());
							newsTitle = lists.get(i + j).text();
							System.out.println("https://tw.stock.yahoo.com" + lists.get(i + j).children().attr("href"));
							newsWebaddress = "https://tw.stock.yahoo.com" + lists.get(i + j).children().attr("href");
						}
						if (j % 6 == 1) {
							System.out.println(lists.get(i + j).text().substring(1, 11).replace("/", "-"));
							newsDate = java.sql.Date
									.valueOf(lists.get(i + j).text().substring(1, 11).replace("/", "-"));
						}
						if (newsDate != null && newsTitle != null && newsWebaddress != null) {
							
							if (newsservice.checktitleexist(newsTitle)) {
								newsservice.insert(newsDate, newsTitle, newsWebaddress);
							}
							stocknewsservice.insert(newsTitle, stockno);
							newsDate = null;
							newsTitle = null;
							newsWebaddress = null;
						}
						j++;
						if (lists.get(i + j).text().contains("共")) {
							break a;
						}
					}

				}
				i++;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		int[] stock = new int[] { 1101, 1102, 1216, 1301, 1303, 1326, 1402, 1476, 2002, 2105, 2207, 2227, 2301, 2303,
				2308, 2311, 2317, 2325, 2330, 2354, 2357, 2382, 2395, 2408, 2409, 2412, 2454, 2474, 2801, 2880, 2881,
				2882, 2883, 2884, 2885, 2886, 2887, 2890, 2891, 2892, 2912, 3008, 3045, 3474, 3481, 4904, 4938, 5880,
				6505, 9904 };
		for(int i =0;i<stock.length;i++){
		for (int page = 1; page <= 45; page++) {
			getnewsbystock(stock[i], page);
		}

		}
	}

}

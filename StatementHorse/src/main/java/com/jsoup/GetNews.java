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
//		for (int i = 1; i <= 45; i++) {
			getnewsbystock(2330, 2);

//		}
	}

}

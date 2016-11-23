package com.jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dividend.model.DividendService;
import com.news.model.NewsService;
import com.stocknews.model.StockNewsService;

public class GetDividend {
	public static void GetDividend(Integer stockNo) throws IOException {

		DividendService dividendSvc = new DividendService();
		//https時用
		Connection connect = Jsoup.connect("https://tw.stock.yahoo.com/d/s/dividend_" + stockNo + ".html")
				.userAgent("UA").timeout(5000);
		Response resp = connect.execute();
		if (resp.statusCode() == 200) {
			Document doc = connect.get();
			Elements lists = doc.select("td");
			int i = 0;

			for (Element list : lists) {
				if (list.text().equals("104")) {

					Double value = Double.parseDouble(lists.get(i + 5).text());
					dividendSvc.insert(stockNo, "104", value);
					System.out.println(lists.get(i + 5).text());
				}
				if (list.text().equals("103")) {

					Double value = Double.parseDouble(lists.get(i + 5).text());
					dividendSvc.insert(stockNo, "103", value);
					System.out.println(lists.get(i + 5).text());
				}
				if (list.text().equals("102")) {

					Double value = Double.parseDouble(lists.get(i + 5).text());
					dividendSvc.insert(stockNo, "102", value);
					System.out.println(lists.get(i + 5).text());
				}
				if (list.text().equals("101")) {

					Double value = Double.parseDouble(lists.get(i + 5).text());
					dividendSvc.insert(stockNo, "101", value);
					System.out.println(lists.get(i + 5).text());
				}
				if (list.text().equals("101")) {

					Double value = Double.parseDouble(lists.get(i + 5).text());
					dividendSvc.insert(stockNo, "101", value);
					System.out.println(lists.get(i + 5).text());
				}
				if (list.text().equals("100")) {

					Double value = Double.parseDouble(lists.get(i + 5).text());
					dividendSvc.insert(stockNo, "100", value);
					System.out.println(lists.get(i + 5).text());
				}
				if (list.text().equals("99")) {

					Double value = Double.parseDouble(lists.get(i + 5).text());
					dividendSvc.insert(stockNo, "99", value);
					System.out.println(lists.get(i + 5).text());
				}
				if (list.text().equals("98")) {

					Double value = Double.parseDouble(lists.get(i + 5).text());
					dividendSvc.insert(stockNo, "98", value);
					System.out.println(lists.get(i + 5).text());
				}
				if (list.text().equals("97")) {

					Double value = Double.parseDouble(lists.get(i + 5).text());
					dividendSvc.insert(stockNo, "97", value);
					System.out.println(lists.get(i + 5).text());
				}
				if (list.text().equals("96")) {

					Double value = Double.parseDouble(lists.get(i + 5).text());
					dividendSvc.insert(stockNo, "96", value);
					System.out.println(lists.get(i + 5).text());
				}
				if (list.text().equals("95")) {

					Double value = Double.parseDouble(lists.get(i + 5).text());
					dividendSvc.insert(stockNo, "95", value);
					System.out.println(lists.get(i + 5).text());
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
		for (int i = 0; i < stock.length; i++) {
			GetDividend(stock[i]);
		}
	}

}

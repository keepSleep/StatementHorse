package com.jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.price.model.PriceService;

public class GetPrice {
	public static void insertPrice(Integer stockNo, Integer year, Integer month, Integer day) throws IOException {
		PriceService priceService = new PriceService();

		URL url = new URL("http://www.tse.com.tw/ch/trading/exchange/MI_INDEX/MI_INDEX.php?download=&qdate="
				+ (year - 1911) + "%2F" + String.format("%02d", month) + "%2F" + String.format("%02d", day)
				+ "&selectType=ALLBUT0999");
		Document xmlDoc = Jsoup.parse(url, 10000); // 使用Jsoup jar 去解析網頁
		Elements tdText = xmlDoc.select("td"); // 要解析的tag元素為td

		Double open_price = null;
		Double close_price = null;
		Double highest_price = null;
		Double lowest_price = null;
		Integer quantity = null;
		int i = 0;
		String priceDate = "" + year + "-" + month + "-" + day;

		for (Element tdContext : tdText) {
			// System.out.println(tdContext.text());
			// 設定的股號
			if (tdContext.text().equals("" + stockNo)) {
				try {
					// 開盤價
					open_price = new Double(tdText.get(i + 5).text().replace(",", ""));
					System.out.println(open_price);
				} catch (java.lang.NumberFormatException e) {
					open_price = new Double(0.0);
				}
				try {
					// 最高價
					highest_price = new Double(tdText.get(i + 6).text().replace(",", ""));
					System.out.println(highest_price);
				} catch (java.lang.NumberFormatException e) {
					highest_price = new Double(0.0);
				}
				try {
					// 最低價
					lowest_price = new Double(tdText.get(i + 7).text().replace(",", ""));
					System.out.println(lowest_price);
				} catch (java.lang.NumberFormatException e) {
					lowest_price = new Double(0.0);
				}
				try {
					// 收盤價
					close_price = new Double(tdText.get(i + 8).text().replace(",", ""));
					System.out.println(close_price);
				} catch (java.lang.NumberFormatException e) {
					close_price = new Double(0.0);
				}
				// 成交量
				quantity = new Integer(tdText.get(i + 2).text().replace(",", ""));
				System.out.println(tdText.get(i + 2).text().replace(",", ""));
			}
			i++;
		}

		priceService.insert(stockNo, priceDate, open_price, close_price, highest_price, lowest_price, quantity);
		System.out.println(priceDate);
	}

	public static void main(String[] args) throws IOException {
		int[] stock = new int[] { 1101, 1102, 1216, 1301, 1303, 1326, 1402, 1476, 2002, 2105, 2207, 2227, 2301, 2303,
				2308, 2311, 2317, 2325, 2330, 2354, 2357, 2382, 2395, 2408, 2409, 2412, 2454, 2474, 2801, 2880, 2881,
				2882, 2883, 2884, 2885, 2886, 2887, 2890, 2891, 2892, 2912, 3008, 3045, 3474, 3481, 4904, 4938, 5880,
				6505, 9904 };

		for (int y = 2016; y <= 2016; y++) {
			for (int m = 11; m <= 12; m++) {
				for (int d = 20; d <= 31; d++) {
					for (int is = 0; is < stock.length; is++) {
						try {
							insertPrice(stock[is], y, m, d);
						} catch (org.hibernate.exception.ConstraintViolationException e) {
							continue;
						} catch (org.hibernate.exception.SQLGrammarException se) {
							continue;
						}
					}
				}
			}
		}
	}

}

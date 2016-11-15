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

		for (Element tdContext : tdText) {
			// System.out.println(tdContext.text());
			// 設定的股號
			if (tdContext.text().equals("" + stockNo)) {
				// 開盤價
				open_price = new Double(tdText.get(i + 5).text().replace(",", ""));
				System.out.println(open_price);
				// 最高價
				highest_price = new Double(tdText.get(i + 6).text().replace(",", ""));
				System.out.println(highest_price);
				// 最低價
				lowest_price = new Double(tdText.get(i + 7).text().replace(",", ""));
				System.out.println(lowest_price);
				// 收盤價
				close_price = new Double(tdText.get(i + 8).text().replace(",", ""));
				System.out.println(close_price);
				// 成交量
				quantity = new Integer(tdText.get(i + 2).text().replace(",", ""));
				System.out.println(tdText.get(i + 2).text().replace(",", ""));
			}
			i++;
		}

		String priceDate = "" + year + "-" + month + "-" + day;
		priceService.insert(stockNo, priceDate, open_price, close_price, highest_price, lowest_price, quantity);
		System.out.println(priceDate);
	}

	public static void main(String[] args) throws IOException {

		for (int x = 2013; x <= 2016; x++) {
			for (int i = 1; i <= 12; i++) {
				for (int j = 1; j <= 31; j++) {
					try {
						insertPrice(3008, x, i, j);
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

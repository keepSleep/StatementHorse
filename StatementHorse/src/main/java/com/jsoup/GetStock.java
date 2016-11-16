package com.jsoup;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetStock {

	public static void main(String[] args) {
		String url = "http://stock.nlog.cc/b/2330";
		try {
			Document doc = Jsoup.connect(url).ignoreHttpErrors(true).get();
			System.out.println(doc.title());
			Elements h1s = doc.select("td");

			Element thisOne = null;
			for (Iterator it = h1s.iterator(); it.hasNext();) {
				thisOne = (Element) it.next();
				System.out.println(thisOne.html());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.jsoup;

import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.price.model.PriceService;

public class GetPrice {
	public static void insertPrice(Integer year,Integer month,Integer day) throws MalformedURLException{
		PriceService priceService = new PriceService();
		
		URL url =new URL("http://www.tse.com.tw/ch/trading/exchange/MI_INDEX/MI_INDEX.php?download=&qdate=  105   %2F  11  %2F  11  &selectType=ALLBUT0999");
	}
	public static void main(String[] args){
		System.out.println("%2F");
		
	}
}

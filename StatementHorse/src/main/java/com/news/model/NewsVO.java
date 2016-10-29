package com.news.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


import com.stocknews.model.StockNewsVO;

/*
 * 註1: classpath必須有hibernate-jpa-2.0-api-1.0.0.Final.jar 
 * 註2: Annotation可以添加在屬性上，也可以添加在getXxx()方法之上
 */

public class NewsVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer newsId;//新聞編號
	private Date newsDate; //日期
	private String newsTitle;//標題
	private String newsWebaddress;//超連結網址
	private Set<StockNewsVO> stocknews=new LinkedHashSet<StockNewsVO>();
																	
	public Integer getNewsId() {
		return newsId;	
	}

	public void setNewsId(Integer newsID) {
		this.newsId = newsID;
	}

	public Date getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	
	public String getNewsWebaddress() {
		return newsWebaddress;
	}

	public void setNewsWebaddress(String newsWebaddress) {
		this.newsWebaddress = newsWebaddress;
	}

	public Set<StockNewsVO> getStocknews() {
		return stocknews;
	}

	public void setStocknews(Set<StockNewsVO> stocknews) {
		this.stocknews = stocknews;
	}

}

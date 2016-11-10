package com.stocknews.model;

import com.news.model.NewsVO;
import com.stock.model.StockVO;

public class StockNewsVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private StockVO StockVO;
	private NewsVO NewsVO	;
	
	public StockVO getStockVO() {
		return StockVO;
	}
	public void setStockVO(StockVO stockVO) {
		StockVO = stockVO;
	}
	public NewsVO getNewsVO() {
		return NewsVO;
	}
	public void setNewsVO(NewsVO newsVO) {
		NewsVO = newsVO;
	}

	

}

package com.dividend.model;

import java.io.Serializable;

import com.stock.model.StockVO;

public class DividendVO implements Serializable {
	private StockVO StockVO;
	private String dividendYear;
	private Double dividend;
	
	
	public String getDividendYear() {
		return dividendYear;
	}
	public void setDividendYear(String dividendYear) {
		this.dividendYear = dividendYear;
	}
	public Double getDividend() {
		return dividend;
	}
	public void setDividend(Double dividend) {
		this.dividend = dividend;
	}
	public StockVO getStockVO() {
		return StockVO;
	}
	public void setStockVO(StockVO stockVO) {
		StockVO = stockVO;
	}
	
}

package com.price.model;

import java.io.Serializable;

import com.stock.model.StockVO;

public class PriceVO implements Serializable {
	private StockVO stockVO;
	private String priceDate;
	private Double openPrice;
	private Double closePrice;
	private Double highestPrice;
	private Double lowestPrice;
	private Integer quantity;
	public StockVO getStockVO() {
		return stockVO;
	}
	public void setStockVO(StockVO stockVO) {
		this.stockVO = stockVO;
	}
	public String getPriceDate() {
		return priceDate;
	}
	public void setPriceDate(String priceDate) {
		this.priceDate = priceDate;
	}
	public Double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}
	public Double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}
	public Double getHighestPrice() {
		return highestPrice;
	}
	public void setHighestPrice(Double highestPrice) {
		this.highestPrice = highestPrice;
	}
	public Double getLowestPrice() {
		return lowestPrice;
	}
	public void setLowestPrice(Double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}	
}

package com.mgr.model;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.stock.model.StockVO;

public class MGRVO implements Serializable {

	private StockVO stockVO;
	private String revenueDate;
	private Integer revenue;
	private Date postDate;
	
	public StockVO getStockVO() {
		return stockVO;
	}
	public void setStockVO(StockVO stockVO) {
		this.stockVO = stockVO;
	}
	public String getRevenueDate() {
		return revenueDate;
	}
	public void setRevenueDate(String revenueDate) {
		this.revenueDate = revenueDate;
	}
	public Integer getRevenue() {
		return revenue;
	}
	public void setRevenue(Integer revenue) {
		this.revenue = revenue;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	
}

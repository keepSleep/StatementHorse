package com.listingdetails.model;

import com.stock.model.StockVO;
import com.tracklisting.model.TrackListingVO;

public class ListingDetailsVO implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private Integer listingNo;
//	private Integer stockNo;
	private TrackListingVO trackListingVO;
	private StockVO stockVO;
	
//	public Integer getListingNo() {
//		return listingNo;
//	}
//	public void setListingNo(Integer listingNo) {
//		this.listingNo = listingNo;
//	}
//	public Integer getStockNo() {
//		return stockNo;
//	}
//	public void setStockNo(Integer stockNo) {
//		this.stockNo = stockNo;
//	}
	
	public TrackListingVO getTrackListingVO() {
		return trackListingVO;
	}
	public void setTrackListingVO(TrackListingVO trackListingVO) {
		this.trackListingVO = trackListingVO;
	}

	public StockVO getStockVO() {
		return stockVO;
	}
	public void setStockVO(StockVO stockVO) {
		this.stockVO = stockVO;
	}
	

}

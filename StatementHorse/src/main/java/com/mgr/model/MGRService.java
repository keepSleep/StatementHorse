package com.mgr.model;



import java.util.List;

import com.stock.model.StockService;
import com.stock.model.StockVO;


public class MGRService {
	private MGRDAO_interface dao;
	public MGRService(){
		dao=new MGRDAO();
	}
	public MGRVO getOneStockMonthlyMGR(Integer stockNo,String revenueDate){
		StockService stockSer=new StockService();
		StockVO stockVO=stockSer.getOneStock(stockNo);
		MGRVO mgrVO=new MGRVO();
		mgrVO.setStockVO(stockVO);
		mgrVO.setRevenueDate(revenueDate);
		mgrVO = dao.FindByPrimaryKey(mgrVO);
		return mgrVO;
	}
	public List<MGRVO> getByStockNo(Integer stockNo){
		List<MGRVO> list=dao.getByStockNo(stockNo);
		return list;
	}
	public void insert(Integer stockNo,String revenueDate,Integer revenue,String postDate) {
		StockVO stockVO = new StockVO(); 
		stockVO.setStockNo(stockNo);
		MGRVO mgrVO =new MGRVO();
		mgrVO.setStockVO(stockVO);
		mgrVO.setRevenueDate(revenueDate);
		mgrVO.setRevenue(revenue);
		mgrVO.setPostDate(java.sql.Date.valueOf(postDate));
		dao.insert(mgrVO);
	}
	public void update(Integer stockNo,String revenueDate,Integer revenue,String postDate) {
		StockVO stockVO = new StockVO(); 
		stockVO.setStockNo(stockNo);
		MGRVO mgrVO =new MGRVO();
		mgrVO.setStockVO(stockVO);
		mgrVO.setRevenueDate(revenueDate);
		mgrVO.setRevenue(revenue);
		mgrVO.setPostDate(java.sql.Date.valueOf(postDate));
		dao.update(mgrVO);
	}
	public void delete(Integer stockNo,String revenueDate) {
		StockVO stockVO = new StockVO(); 
		MGRVO mgrVO=new MGRVO();
		stockVO.setStockNo(stockNo);
		mgrVO.setStockVO(stockVO);
		mgrVO.setRevenueDate(revenueDate);
		dao.delete(mgrVO);
		
	}
	public void deleteByStockNO(Integer stockNo){
	dao.deleteByStockNo(stockNo);
	}
}

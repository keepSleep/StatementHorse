package com.dividend.model;

import java.util.List;

import com.price.model.PriceVO;
import com.stock.model.StockVO;

public class DividendService {
	private Dividend_interface dao;
	
	public DividendService(){
		dao = new DividendDAO();
	}
	public DividendVO insert(Integer stockNo, String dividendYear, Double dividend){
		DividendVO dividendVO=new DividendVO();
		StockVO stockVO=new StockVO();
		stockVO.setStockNo(stockNo);
		dividendVO.setStockVO(stockVO);
		dividendVO.setDividendYear(dividendYear);
		dividendVO.setDividend(dividend);
		dao.insert(dividendVO);
		
		return dividendVO;
	}
	
	public DividendVO update(Integer stockNo, String dividendYear, Double dividend){
		DividendVO dividendVO = new DividendVO();
		StockVO stockVO=new StockVO();
		stockVO.setStockNo(stockNo);
		dividendVO.setStockVO(stockVO);
		dividendVO.setDividendYear(dividendYear);
		dividendVO.setDividend(dividend);
		dao.update(dividendVO);
		
		return dao.findByPrimaryKey(dividendVO);
				
	}
	
	public void delete(Integer stockNo, String dividendYear){
		DividendVO dividendVO = new DividendVO();
		StockVO stockVO=new StockVO();
		stockVO.setStockNo(stockNo);
		dividendVO.setStockVO(stockVO);
		dividendVO.setDividendYear(dividendYear);
		dao.delete(dividendVO);
	}
	
	public DividendVO getOneDividend(Integer stockNo, String dividendYear){
		DividendVO dividendVO = new DividendVO();
		StockVO stockVO=new StockVO();
		stockVO.setStockNo(stockNo);
		dividendVO.setStockVO(stockVO);
		dividendVO.setDividendYear(dividendYear);
		return dao.findByPrimaryKey(dividendVO);
	}
	
	public List<DividendVO> getAll(){
		return dao.getAll();
	}
	public void deleteByStockNO(Integer stockNo){
		dao.deleteByStockNo(stockNo);
	}
	public List<DividendVO> getByStockNo(Integer stockNo){
		List<DividendVO> list=dao.getByStockNo(stockNo);
		return list;
	}
}

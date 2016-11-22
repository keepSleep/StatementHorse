package com.price.model;

import java.util.List;

import com.mgr.model.MGRDAO;
import com.mgr.model.MGRDAO_interface;
import com.mgr.model.MGRVO;
import com.stock.model.StockService;
import com.stock.model.StockVO;

public class PriceService {
	private PriceDAO_interface dao;
	public PriceService(){
		dao=new PriceDAO();
	}
	public PriceVO getOneStockMonthlyPrice(Integer stockNo,String priceDate){
		StockService stockSer=new StockService();
		StockVO stockVO=stockSer.getOneStock(stockNo);
		PriceVO priceVO=new PriceVO();
		priceVO.setStockVO(stockVO);
		priceVO.setPriceDate(priceDate);
		priceVO = dao.findByPrimaryKey(priceVO);
		return priceVO;
	}
	public List<PriceVO> getByStockNo(Integer stockNo){
		List<PriceVO> list=dao.getByStockNo(stockNo);
		return list;
	}
	
	public void insert(Integer stockNo,String priceDate,
			Double openPrice,Double closePrice, Double highestPrice,
			Double lowestPrice,Integer quantity) {
		StockVO stockVO = new StockVO(); 
		stockVO.setStockNo(stockNo);
		PriceVO priceVO =new PriceVO();
		priceVO.setStockVO(stockVO);
		priceVO.setPriceDate(priceDate);
		priceVO.setOpenPrice(openPrice);
		priceVO.setClosePrice(closePrice);
		priceVO.setHighestPrice(highestPrice);
		priceVO.setLowestPrice(lowestPrice);
		priceVO.setQuantity(quantity);
		dao.insert(priceVO);
	}
	public void update(Integer stockNo,String priceDate,
			Double openPrice,Double closePrice, Double highestPrice,
			Double lowestPrice,Integer quantity) {
		StockVO stockVO = new StockVO(); 
		stockVO.setStockNo(stockNo);
		PriceVO priceVO =new PriceVO();
		priceVO.setStockVO(stockVO);
		priceVO.setPriceDate(priceDate);
		priceVO.setOpenPrice(openPrice);
		priceVO.setClosePrice(closePrice);
		priceVO.setHighestPrice(highestPrice);
		priceVO.setLowestPrice(lowestPrice);
		priceVO.setQuantity(quantity);
		dao.insert(priceVO);
	}
	public void delete(Integer stockNo,String priceDate) {
		StockVO stockVO = new StockVO(); 
		stockVO.setStockNo(stockNo);
		PriceVO priceVO =new PriceVO();
		priceVO.setStockVO(stockVO);
		priceVO.setPriceDate(priceDate);
		dao.delete(priceVO);
		
	}
	
	public List<Object[]> getPER(Integer stockNo){
		List list=dao.getStoredProcedure(stockNo);
		return list;
	}
	
	public void deleteByStockNO(Integer stockNo){
	dao.deleteByStockNo(stockNo);
	}
//	public static  void main(String args[]){
//		PriceService priceser=new PriceService();
//		List<Object[]> list=priceser.getPER(3008);
//		
//		for(Object[] aArray:list){
////			System.out.println(aArray.getClass());
//				
//				System.out.println(aArray[0]+","+aArray[1]);
//			
//		}
//	}
}

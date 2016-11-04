package com.mgr.model;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
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
	
	public List<MGRVO> insertmgrvo(MGRVO mgrVO){
		List<MGRVO> list = dao.findByStockNo(mgrVO.getStockVO().getStockNo());
		MGRVO lastVO = list.get(0);
		Date lastdate = lastVO.getPostDate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date lastdateandtime=null;
		try {
			lastdateandtime = dateFormat.parse(lastdate.toString());
			System.out.println(lastdateandtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dao.insert(mgrVO);
		return checkinsert(mgrVO.getStockVO().getStockNo(),lastdateandtime);	
	}
	
	public List<MGRVO> checkinsert(Integer stockno,Date lastdateandtime){
		List<MGRVO> returnlist=new LinkedList<>();
		List<MGRVO> list = dao.findByStockNo(stockno);
		for(MGRVO mgrvo:list){
			Date lastdate = mgrvo.getPostDate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date lastdateandtime1=null;
			try {
				lastdateandtime1= dateFormat.parse(lastdate.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if(lastdateandtime1.before(lastdateandtime)){
				break;				
			}else{
				returnlist.add(mgrvo);
			}
		}
		return returnlist;
	}
	
	public void deleteByStockNO(Integer stockNo){
	dao.deleteByStockNo(stockNo);
	}
	
	
	
}

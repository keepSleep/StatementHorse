package com.financialstatements.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.balancesheet.model.BalanceSheetDAO;
import com.balancesheet.model.BalanceSheetVO;
import com.stock.model.StockService;
import com.stock.model.StockVO;

public class FinancialStatementsService {
	private FinancialStatements_interface dao;
	
	public FinancialStatementsService(){
		dao = new FinancialStatementsDAO();
		
	}
	public FinancialStatementsVO addFinancialStatements(Integer stockNo,String statementDate, Date postDate, String postTime
			){
		FinancialStatementsVO financialStatementsVO = new FinancialStatementsVO();
		financialStatementsVO.setStockNo(stockNo);
		financialStatementsVO.setStatementDate(statementDate);
		financialStatementsVO.setPostDate(postDate);
		financialStatementsVO.setPostTime(postTime);
		
		
		
		
		return financialStatementsVO;
	}
	public FinancialStatementsVO updateFinancialStatements(Integer stockNo,String statementDate, Date postDate, String postTime
			){
		
		FinancialStatementsVO financialStatementsVO = new FinancialStatementsVO();
		financialStatementsVO.setStockNo(stockNo);
		financialStatementsVO.setStatementDate(statementDate);
		financialStatementsVO.setPostDate(postDate);
		financialStatementsVO.setPostTime(postTime);
		dao.update(financialStatementsVO);
		
		return dao.findByPrimaryKey(financialStatementsVO);
	}
	public void deleteFinancialStatements(Integer stockNo, String statementDate){
		FinancialStatementsVO financialStatementsVO = new FinancialStatementsVO();
		financialStatementsVO.setStockNo(stockNo);
		financialStatementsVO.setStatementDate(statementDate);
		dao.delete(financialStatementsVO);
	}
	public FinancialStatementsVO getOneFinancialStatements(Integer stockNo, String statementDate){
		FinancialStatementsVO financialStatementsVO = new FinancialStatementsVO();
		financialStatementsVO.setStockNo(stockNo);
		financialStatementsVO.setStatementDate(statementDate);
		return dao.findByPrimaryKey(financialStatementsVO);
		
	}
	public List<FinancialStatementsVO> getAll(){
		return dao.getAll();
	}
	
	public List<FinancialStatementsVO> getByPostDate(Date postDate ){
		FinancialStatementsVO financialStatementsVO = new FinancialStatementsVO();
		financialStatementsVO.setPostDate(postDate);
		return dao.getByPostDate(financialStatementsVO);
	}
	public List<FinancialStatementsVO> checkinsert(Integer stockno,Date lastdateandtime){
		List<FinancialStatementsVO> returnlist = new LinkedList<>();
		List<FinancialStatementsVO> list = dao.findByStockNo(stockno);
		for(FinancialStatementsVO financialStatementsVO:list){
			Date lastdate = financialStatementsVO.getPostDate();
			String lasttime = financialStatementsVO.getPostTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			java.util.Date lastdateandtime1 = null;
			try {
				lastdateandtime1 = dateFormat.parse(lastdate.toString()+lasttime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(lastdateandtime1.before(lastdateandtime)){
				break;
			}else{
				returnlist.add(financialStatementsVO);
			}
		}
		return returnlist;
		
	}

	public List<FinancialStatementsVO> insert(FinancialStatementsVO financialStatementsVO){
		List<FinancialStatementsVO> list = dao.findByStockNo(financialStatementsVO.getStockNo());
		FinancialStatementsVO lastvo = list.get(0);
		Date lastdate = lastvo.getPostDate();
		String lasttime = lastvo.getPostTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		java.util.Date lastdateandtime = null;
		try {
			lastdateandtime = dateFormat.parse(lastdate.toString()+lasttime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dao.insert(financialStatementsVO);
		return checkinsert(financialStatementsVO.getStockNo(),(java.sql.Date)lastdateandtime);
	}
	
	

}

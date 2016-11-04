package com.financialstatements.model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.balancesheet.model.BalanceSheetDAO;
import com.balancesheet.model.BalanceSheetVO;

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

	
	public static final void main(String args[]) throws ParseException{
		FinancialStatementsService a=new FinancialStatementsService();
		FinancialStatementsVO FinancialStatementsVO=new FinancialStatementsVO();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");		
		FinancialStatementsVO.setPostDate(dateformat.parse("2014-08-01"));
		
		List<com.financialstatements.model.FinancialStatementsVO> list = a.getByPostDate(FinancialStatementsVO.getPostDate());
		for(FinancialStatementsVO aa:list){
			System.out.println(aa.getPostDate());
			System.out.println(aa.getPostTime());
			System.out.println(aa.getStockNo());
			System.out.println(aa.getStatementDate());
		}
	
	}
}

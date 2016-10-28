package com.financialstatements.model;

import java.sql.Date;
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

}

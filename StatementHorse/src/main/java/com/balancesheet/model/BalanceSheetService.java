package com.balancesheet.model;

import java.util.List;

public class BalanceSheetService {
	private BalanceSheet_interface dao;
	
	public BalanceSheetService(){
		dao = new BalanceSheetDAO();
	}
	public BalanceSheetVO addBalanceSheet(Integer stockNo,String statementDate, Long currentAssets, Long fixedAssets, Long assets, Long currentLiabilities, Long longTermLiabilities, 
			Long liabilities, Long capitalStock, Long additionalPaidInCapital, Long retainedEarnings, Long otherEquity, 
			Long consolidatedNetIncomeAttributedToStockholdersOfTheCompany, Long totalEquity){
		BalanceSheetVO balanceSheetVO = new BalanceSheetVO();
		balanceSheetVO.setStockNo(stockNo);
		balanceSheetVO.setStatementDate(statementDate);
		balanceSheetVO.setCurrentAssets(currentAssets);
		balanceSheetVO.setFixedAssets(fixedAssets);
		balanceSheetVO.setAssets(assets);
		balanceSheetVO.setCurrentLiabilities(currentLiabilities);
		balanceSheetVO.setLongTermLiabilities(longTermLiabilities);
		balanceSheetVO.setLiabilities(liabilities);
		balanceSheetVO.setCapitalStock(capitalStock);
		balanceSheetVO.setAdditionalPaidInCapital(additionalPaidInCapital);
		balanceSheetVO.setRetainedEarnings(retainedEarnings);
		balanceSheetVO.setOtherEquity(otherEquity);
		balanceSheetVO.setConsolidatedNetIncomeAttributedToStockholdersOfTheCompany(consolidatedNetIncomeAttributedToStockholdersOfTheCompany);
		balanceSheetVO.setTotalEquity(totalEquity);
		
		
		
		return balanceSheetVO;
	}
	public BalanceSheetVO updateBalanceSheet(Integer stockNo,String statementDate, Long currentAssets, Long fixedAssets, Long assets, Long currentLiabilities, Long longTermLiabilities, 
			Long liabilities, Long capitalStock, Long additionalPaidInCapital, Long retainedEarnings, Long otherEquity, 
			Long consolidatedNetIncomeAttributedToStockholdersOfTheCompany, Long totalEquity){
		
		BalanceSheetVO balanceSheetVO = new BalanceSheetVO();
		balanceSheetVO.setStockNo(stockNo);
		balanceSheetVO.setStatementDate(statementDate);
		balanceSheetVO.setCurrentAssets(currentAssets);
		balanceSheetVO.setFixedAssets(fixedAssets);
		balanceSheetVO.setAssets(assets);
		balanceSheetVO.setCurrentLiabilities(currentLiabilities);
		balanceSheetVO.setLongTermLiabilities(longTermLiabilities);
		balanceSheetVO.setLiabilities(liabilities);
		balanceSheetVO.setCapitalStock(capitalStock);
		balanceSheetVO.setAdditionalPaidInCapital(additionalPaidInCapital);
		balanceSheetVO.setRetainedEarnings(retainedEarnings);
		balanceSheetVO.setOtherEquity(otherEquity);
		balanceSheetVO.setConsolidatedNetIncomeAttributedToStockholdersOfTheCompany(consolidatedNetIncomeAttributedToStockholdersOfTheCompany);
		balanceSheetVO.setTotalEquity(totalEquity);
		dao.update(balanceSheetVO);
		
		return dao.findByPrimaryKey(balanceSheetVO);
	}
	public void deleteBalanceSheet(Integer stockNo, String statementDate){
		BalanceSheetVO balanceSheetVO = new BalanceSheetVO();
		balanceSheetVO.setStockNo(stockNo);
		balanceSheetVO.setStatementDate(statementDate);
		dao.delete(balanceSheetVO);
	}
	public BalanceSheetVO getOneBalanceSheet(Integer stockNo, String statementDate){
		BalanceSheetVO balanceSheetVO = new BalanceSheetVO();
		balanceSheetVO.setStockNo(stockNo);
		balanceSheetVO.setStatementDate(statementDate);
		return dao.findByPrimaryKey(balanceSheetVO);
		
	}
	public List<BalanceSheetVO> getAll(){
		return dao.getAll();
	}
}

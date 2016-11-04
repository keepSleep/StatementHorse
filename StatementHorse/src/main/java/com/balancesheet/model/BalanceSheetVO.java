package com.balancesheet.model;

import java.io.Serializable;

import com.financialstatements.model.FinancialStatementsVO;
import com.stock.model.StockVO;

public class BalanceSheetVO implements Serializable {
	private StockVO stockVO;
	private String statementDate;
	private Long currentAssets;
	private Long fixedAssets;
	private Long assets;
	private Long currentLiabilities;
	private Long longTermLiabilities;
	private Long liabilities;
	private Long capitalStock;
	private Long additionalPaidInCapital;
	private Long retainedEarnings;
	private Long otherEquity;
	private Long consolidatedNetIncomeAttributedToStockholdersOfTheCompany;
	private Long totalEquity;
	private FinancialStatementsVO financialStatementsVO;
	public StockVO getStockVO() {
		return stockVO;
	}
	public void setStockVO(StockVO stockVO) {
		this.stockVO = stockVO;
	}public FinancialStatementsVO getFinancialStatementsVO() {
		return financialStatementsVO;
	}
	public void setFinancialStatementsVO(FinancialStatementsVO financialStatementsVO) {
		this.financialStatementsVO = financialStatementsVO;
	}
	
	public String getStatementDate() {
		return statementDate;
	}
	public void setStatementDate(String statementDate) {
		this.statementDate = statementDate;
	}
	public Long getCurrentAssets() {
		return currentAssets;
	}
	public void setCurrentAssets(Long currentAssets) {
		this.currentAssets = currentAssets;
	}
	public Long getFixedAssets() {
		return fixedAssets;
	}
	public void setFixedAssets(Long fixedAssets) {
		this.fixedAssets = fixedAssets;
	}
	public Long getAssets() {
		return assets;
	}
	public void setAssets(Long assets) {
		this.assets = assets;
	}
	public Long getCurrentLiabilities() {
		return currentLiabilities;
	}
	public void setCurrentLiabilities(Long currentLiabilities) {
		this.currentLiabilities = currentLiabilities;
	}
	public Long getLongTermLiabilities() {
		return longTermLiabilities;
	}
	public void setLongTermLiabilities(Long longTermLiabilities) {
		this.longTermLiabilities = longTermLiabilities;
	}
	public Long getLiabilities() {
		return liabilities;
	}
	public void setLiabilities(Long liabilities) {
		this.liabilities = liabilities;
	}
	public Long getCapitalStock() {
		return capitalStock;
	}
	public void setCapitalStock(Long capitalStock) {
		this.capitalStock = capitalStock;
	}
	public Long getAdditionalPaidInCapital() {
		return additionalPaidInCapital;
	}
	public void setAdditionalPaidInCapital(Long additionalPaidInCapital) {
		this.additionalPaidInCapital = additionalPaidInCapital;
	}
	public Long getRetainedEarnings() {
		return retainedEarnings;
	}
	public void setRetainedEarnings(Long retainedEarnings) {
		this.retainedEarnings = retainedEarnings;
	}
	public Long getOtherEquity() {
		return otherEquity;
	}
	public void setOtherEquity(Long otherEquity) {
		this.otherEquity = otherEquity;
	}
	public Long getConsolidatedNetIncomeAttributedToStockholdersOfTheCompany() {
		return consolidatedNetIncomeAttributedToStockholdersOfTheCompany;
	}
	public void setConsolidatedNetIncomeAttributedToStockholdersOfTheCompany(
			Long consolidatedNetIncomeAttributedToStockholdersOfTheCompany) {
		this.consolidatedNetIncomeAttributedToStockholdersOfTheCompany = consolidatedNetIncomeAttributedToStockholdersOfTheCompany;
	}
	public Long getTotalEquity() {
		return totalEquity;
	}
	public void setTotalEquity(Long totalEquity) {
		this.totalEquity = totalEquity;
	}

	
	
}

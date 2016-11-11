package com.financialstatements.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.balancesheet.model.BalanceSheetVO;
import com.incomestatement.model.IncomeStatementVO;
import com.stock.model.StockVO;

public class FinancialStatementsVO implements Serializable {
	private Integer stockNo;
	private String statementDate;
	private Date postDate;
	private String postTime;
	private Set<BalanceSheetVO> balanceSheets = new HashSet<BalanceSheetVO>();
	private Set<IncomeStatementVO> incomeStatements = new HashSet<IncomeStatementVO>();
//	private StockVO stockVO;
	
	public Set<BalanceSheetVO> getBalanceSheets() {
		return balanceSheets;
	}

	public void setBalanceSheets(Set<BalanceSheetVO> balanceSheets) {
		this.balanceSheets = balanceSheets;
	}

	public Set<IncomeStatementVO> getIncomeStatements() {
		return incomeStatements;
	}

	public void setIncomeStatements(Set<IncomeStatementVO> incomeStatements) {
		this.incomeStatements = incomeStatements;
	}

	
	public Integer getStockNo() {
		return stockNo;
	}

	public void setStockNo(Integer stockNo) {
		this.stockNo = stockNo;
	}

	public String getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(String statementDate) {
		this.statementDate = statementDate;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

//	public StockVO getStockVO() {
//		return stockVO;
//	}
//
//	public void setStockVO(StockVO stockVO) {
//		this.stockVO = stockVO;
//	}

}

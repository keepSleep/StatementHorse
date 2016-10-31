package com.incomestatement.model;

import java.io.Serializable;

import com.financialstatements.model.FinancialStatementsVO;
import com.stock.model.StockVO;

public class IncomeStatementVO implements Serializable {
	private StockVO StockVO;
	private String statementDate;
	private Long operatingRevenue;
	private Long operatingCost;
	private Long operatingMargain;
	private Long netOperatingMargain;
	private Long operatingExpenses;
	private Long operatingIncome;
	private Long nonOperatingRevenue;
	private Long oibt;
	private Long incomeTaxExpense;
	private Long coiat;
	private Long netIncome;
	private Long oci;
	private Long currentProfitAndLoss;
	private Double earningPerShare;
	private FinancialStatementsVO financialStatementsVO;

	public FinancialStatementsVO getFinancialStatementsVO() {
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

	public Long getOperatingRevenue() {
		return operatingRevenue;
	}

	public void setOperatingRevenue(Long operatingRevenue) {
		this.operatingRevenue = operatingRevenue;
	}

	public Long getOperatingCost() {
		return operatingCost;
	}

	public void setOperatingCost(Long operatingCost) {
		this.operatingCost = operatingCost;
	}

	public Long getOperatingMargain() {
		return operatingMargain;
	}

	public void setOperatingMargain(Long operatingMargain) {
		this.operatingMargain = operatingMargain;
	}

	public Long getNetOperatingMargain() {
		return netOperatingMargain;
	}

	public void setNetOperatingMargain(Long netOperatingMargain) {
		this.netOperatingMargain = netOperatingMargain;
	}

	public Long getOperatingExpenses() {
		return operatingExpenses;
	}

	public void setOperatingExpenses(Long operatingExpenses) {
		this.operatingExpenses = operatingExpenses;
	}

	public Long getOperatingIncome() {
		return operatingIncome;
	}

	public void setOperatingIncome(Long operatingIncome) {
		this.operatingIncome = operatingIncome;
	}

	public Long getNonOperatingRevenue() {
		return nonOperatingRevenue;
	}

	public void setNonOperatingRevenue(Long nonOperatingRevenue) {
		this.nonOperatingRevenue = nonOperatingRevenue;
	}

	public Long getOibt() {
		return oibt;
	}

	public void setOibt(Long oibt) {
		this.oibt = oibt;
	}

	public Long getIncomeTaxExpense() {
		return incomeTaxExpense;
	}

	public void setIncomeTaxExpense(Long incomeTaxExpense) {
		this.incomeTaxExpense = incomeTaxExpense;
	}

	public Long getCoiat() {
		return coiat;
	}

	public void setCoiat(Long coiat) {
		this.coiat = coiat;
	}

	public Long getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(Long netIncome) {
		this.netIncome = netIncome;
	}

	public Long getOci() {
		return oci;
	}

	public void setOci(Long oci) {
		this.oci = oci;
	}

	public Long getCurrentProfitAndLoss() {
		return currentProfitAndLoss;
	}

	public void setCurrentProfitAndLoss(Long currentProfitAndLoss) {
		this.currentProfitAndLoss = currentProfitAndLoss;
	}

	public Double getEarningPerShare() {
		return earningPerShare;
	}

	public void setEarningPerShare(Double earningPerShare) {
		this.earningPerShare = earningPerShare;
	}

	public StockVO getStockVO() {
		return StockVO;
	}

	public void setStockVO(StockVO stockVO) {
		StockVO = stockVO;
	}

}

package com.incomestatement.model;

import java.io.Serializable;
import com.stock.model.StockVO;

public class IncomeStatementVO implements Serializable {
	private StockVO stockVO;
	private String statementDate;//季度
	private Long operatingRevenue;//營業收入
	private Long operatingCost;//營業成本
	private Long operatingMargain;//營業毛利
	private Long netOperatingMargain;//營業毛利淨額
	private Long operatingExpenses;//營業費用
	private Long operatingIncome;//營業利益
	private Long nonOperatingRevenue;//業外收入及支出
	private Long oibt;//稅前淨利
	private Long incomeTaxExpense;//所得稅費用
	private Long coiat;//繼續營業單位本期淨利
	private Long netIncome;//本期淨利
	private Long oci;//其他綜合損益
	private Long currentProfitAndLoss;//本期綜合損益
	private Double earningPerShare;//基本每股盈餘
	public StockVO getStockVO() {
		return stockVO;
	}
	public void setStockVO(StockVO stockVO) {
		this.stockVO = stockVO;
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
}

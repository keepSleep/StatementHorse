package com.stock.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.balancesheet.model.BalanceSheetVO;
import com.dividend.model.DividendVO;
import com.incomestatement.model.IncomeStatementVO;
import com.listingdetails.model.ListingDetailsVO;
import com.message.model.MsgVO;
import com.mgr.model.MGRVO;
import com.price.model.PriceVO;
import com.stocknews.model.StockNewsVO;

public class StockVO implements Serializable {
	private Integer stockNo;//股號
	private String stockName;//股名
	private String industry;//產業類別
	private String chairman;//董事長
	private Date companyEstablishDate;//公司成立日期
	private String companyTaxId;//統一編號
	private Date listingDate;//上市日期
	private Date stockholdersMeetingDate;//股東會日期
	private String stockWebaddress;//公司網址
	private Integer capital;//資本額
	private String accountingFirm;//簽證會機師
	private Set<MGRVO> mgrs=new LinkedHashSet<MGRVO>();
	private Set<PriceVO> prices=new LinkedHashSet<PriceVO>();
	private Set<IncomeStatementVO> incomeStatements=new LinkedHashSet<IncomeStatementVO>();
	private Set<BalanceSheetVO> balanceSheets=new LinkedHashSet<BalanceSheetVO>();
	private Set<MsgVO> message=new LinkedHashSet<MsgVO>();
	private Set<ListingDetailsVO> listingdetail=new LinkedHashSet<ListingDetailsVO>();
	private Set<StockNewsVO> stocknews=new LinkedHashSet<StockNewsVO>();
	private Set<DividendVO> dividends=new LinkedHashSet<DividendVO>();
	
	
	public Set<BalanceSheetVO> getBalanceSheets() {
		return balanceSheets;
	}
	public void setBalanceSheets(Set<BalanceSheetVO> balanceSheets) {
		this.balanceSheets = balanceSheets;
	}
	public Integer getStockNo() {
		return stockNo;
	}
	public void setStockNo(Integer stockNo) {
		this.stockNo = stockNo;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getChairman() {
		return chairman;
	}
	public void setChairman(String chairman) {
		this.chairman = chairman;
	}
	public Date getCompanyEstablishDate() {
		return companyEstablishDate;
	}
	public void setCompanyEstablishDate(Date companyEstablishDate) {
		this.companyEstablishDate = companyEstablishDate;
	}
	public String getCompanyTaxId() {
		return companyTaxId;
	}
	public void setCompanyTaxId(String companyTaxId) {
		this.companyTaxId = companyTaxId;
	}
	public Date getListingDate() {
		return listingDate;
	}
	public void setListingDate(Date listingDate) {
		this.listingDate = listingDate;
	}
	public Date getStockholdersMeetingDate() {
		return stockholdersMeetingDate;
	}
	public void setStockholdersMeetingDate(Date stockholdersMeetingDate) {
		this.stockholdersMeetingDate = stockholdersMeetingDate;
	}
	public String getStockWebaddress() {
		return stockWebaddress;
	}
	public void setStockWebaddress(String stockWebaddress) {
		this.stockWebaddress = stockWebaddress;
	}
	public Integer getCapital() {
		return capital;
	}
	public void setCapital(Integer capital) {
		this.capital = capital;
	}
	public String getAccountingFirm() {
		return accountingFirm;
	}
	public void setAccountingFirm(String accountingFirm) {
		this.accountingFirm = accountingFirm;
	}
	public Set<MGRVO> getMgrs() {
		return mgrs;
	}
	public void setMgrs(Set<MGRVO> mgrs) {
		this.mgrs = mgrs;
	}
	public Set<PriceVO> getPrices() {
		return prices;
	}
	public void setPrices(Set<PriceVO> prices) {
		this.prices = prices;
	}
	public Set<IncomeStatementVO> getIncomeStatements() {
		return incomeStatements;
	}
	public void setIncomeStatements(Set<IncomeStatementVO> incomeStatements) {
		this.incomeStatements = incomeStatements;
	}
	public Set<MsgVO> getMessage() {
		return message;
	}
	public void setMessage(Set<MsgVO> message) {
		this.message = message;
	}
	public Set<ListingDetailsVO> getListingdetail() {
		return listingdetail;
	}
	public void setListingdetail(Set<ListingDetailsVO> listingdetail) {
		this.listingdetail = listingdetail;
	}
	public Set<StockNewsVO> getStocknews() {
		return stocknews;
	}
	public void setStocknews(Set<StockNewsVO> stocknews) {
		this.stocknews = stocknews;
	}
	public Set<DividendVO> getDividends() {
		return dividends;
	}
	public void setDividends(Set<DividendVO> dividens) {
		this.dividends = dividens;
	}
	

}

package com.stock.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;


import com.balancesheet.model.BalanceSheetVO;
import com.incomestatement.model.IncomeStatementVO;
import com.mgr.model.MGRVO;
import com.price.model.PriceVO;


public class StockService {
	private StockDAO_interface dao;
	public StockService(){
		dao=new StockDAO();
	}
//	private Integer stockNo;//股號
//	private String stockName;//股名
//	private String industry;//產業類別
//	private String chairman;//董事長
//	private Date companyEstablishDate;//公司成立日期
//	private String companyTaxId;//統一編號
//	private Date listingDate;//上市日期
//	private Date stockholdersMeetingDate;//股東會日期
//	private String stockWebaddress;//公司網址
//	private Integer capital;//資本額
//	private String accountingFirm;//簽證會機師
	
	public void insert(Integer stockNo){
		StockVO stockVO=new StockVO();
		stockVO.setStockNo(stockNo);
		dao.insert(stockVO);
	}
	
	public void insertstock(Integer stockNo,String stockName,String industry,
		String chairman,Date companyEstablishDate,String companyTaxId,Date listingDate,
		Date stockholdersMeetingDate,String stockWebaddress,Long capital,String accountingFirm){
		StockVO stockVO=new StockVO();
		stockVO.setStockNo(stockNo);
		stockVO.setStockName(stockName);
		stockVO.setIndustry(industry);
		stockVO.setChairman(chairman);
		stockVO.setCompanyEstablishDate(companyEstablishDate);
		stockVO.setCompanyTaxId(companyTaxId);
		stockVO.setListingDate(listingDate);
		stockVO.setStockholdersMeetingDate(stockholdersMeetingDate);
		stockVO.setStockWebaddress(stockWebaddress);
		stockVO.setCapital(capital);
		stockVO.setAccountingFirm(accountingFirm);
		dao.insert(stockVO);
		
	}
	public void updatestock(Integer stockNo,String stockName,String industry,
			String chairman,Date companyEstablishDate,String companyTaxId,Date listingDate,
			Date stockholdersMeetingDate,String stockWebaddress,Long capital,String accountingFirm){
			StockVO stockVO=new StockVO();
			stockVO.setStockNo(stockNo);
			stockVO.setStockName(stockName);
			stockVO.setIndustry(industry);
			stockVO.setChairman(chairman);
			stockVO.setCompanyEstablishDate(companyEstablishDate);
			stockVO.setCompanyTaxId(companyTaxId);
			stockVO.setListingDate(listingDate);
			stockVO.setStockholdersMeetingDate(stockholdersMeetingDate);
			stockVO.setStockWebaddress(stockWebaddress);
			stockVO.setCapital(capital);
			stockVO.setAccountingFirm(accountingFirm);
			dao.update(stockVO);
			
		}
//	public void delete(Integer stockNo) {
//		IncomeStatementService incomeStatementstr=new IncomeStatementService();
//		incomeStatementstr.deleteByStockNO(stockNo);
//		BalanceSheetService balanceSheetstr=new BalanceSheetService();
//		balanceSheetstr.deleteByStockNO(stockNo);
//		PriceService priceser=new PriceService();
//		priceser.deleteByStockNO(stockNo);
//		MGRService mgrser=new MGRService();
//		mgrser.deleteByStockNO(stockNo);
//		dao.delete(stockNo);
//		
//	}
	public Integer getStockNoByStockName(String stockName){
		Integer stockno=null;
		List<StockVO> stockVO=dao.findStockNoByStockName(stockName);
		for(StockVO element:stockVO){
			stockno=element.getStockNo();
		}
		return stockno;
	}
	
	
	public Set<PriceVO> getOneStockPrice(Integer stockNo){
		StockVO stockVO=new StockVO();
		stockVO.setStockNo(stockNo);
		StockVO stockVO3 = dao.findByPrimaryKey(stockVO);
		Set<PriceVO> set3 = stockVO3.getPrices();
		return set3;
	}
	public Set<IncomeStatementVO> getOneStockIncomeStatement(Integer stockNo){
		StockVO stockVO=new StockVO();
		stockVO.setStockNo(stockNo);
		StockVO stockVO3 = dao.findByPrimaryKey(stockVO);
		Set<IncomeStatementVO> set3 = stockVO3.getIncomeStatements();
		return set3;
	}
	public Set<BalanceSheetVO> getOneStockBalanceSheet(Integer stockNo){
		StockVO stockVO=new StockVO();
		stockVO.setStockNo(stockNo);
		StockVO stockVO3 = dao.findByPrimaryKey(stockVO);
		Set<BalanceSheetVO> set3 = stockVO3.getBalanceSheets();
		return set3;
	}

	public Set<MGRVO> getOneStockMGR(Integer stockNo){
		StockVO stockVO=new StockVO();
		stockVO.setStockNo(stockNo);
		StockVO stockVO3 = dao.findByPrimaryKey(stockVO);
		Set<MGRVO> set3 = stockVO3.getMgrs();
		return set3;
	}
	public StockVO getOneStock(Integer stockNo){
		StockVO stockVO=new StockVO();
		stockVO.setStockNo(stockNo);
		stockVO=dao.findByPrimaryKey(stockVO);
		return stockVO;
	}
	
}

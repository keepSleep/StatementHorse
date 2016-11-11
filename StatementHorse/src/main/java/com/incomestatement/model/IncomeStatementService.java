package com.incomestatement.model;

import java.util.List;

import com.price.model.PriceVO;
import com.stock.model.StockVO;

public class IncomeStatementService {
	private IncomeStatementDAO_interface dao;
	public IncomeStatementService(){
		dao=new IncomeStatementDAO();
	}
	public void insert(
		Integer stockNo,String statementDate,Long operatingRevenue,
		Long operatingCost,Long operatingMargain,Long netOperatingMargain,
		Long operatingExpenses,Long operatingIncome,Long nonOperatingRevenue,
		Long oibt,Long incomeTaxExpense,Long coiat,Long netIncome,
		Long oci,Long currentProfitAndLoss,Double earningPerShare){
		IncomeStatementVO incomeStatementVO=new IncomeStatementVO();
		StockVO stockVO=new StockVO();
		stockVO.setStockNo(stockNo);
		incomeStatementVO.setStockVO(stockVO);
		incomeStatementVO.setStatementDate(statementDate);
		incomeStatementVO.setOperatingRevenue(nonOperatingRevenue);
		incomeStatementVO.setOperatingCost(operatingCost);
		incomeStatementVO.setOperatingMargain(operatingMargain);
		incomeStatementVO.setNetOperatingMargain(netOperatingMargain);
		incomeStatementVO.setOperatingExpenses(operatingExpenses);
		incomeStatementVO.setOperatingIncome(operatingIncome);
		incomeStatementVO.setNonOperatingRevenue(nonOperatingRevenue);
		incomeStatementVO.setOibt(oibt);
		incomeStatementVO.setIncomeTaxExpense(incomeTaxExpense);
		incomeStatementVO.setCoiat(coiat);
		incomeStatementVO.setNetIncome(netIncome);
		incomeStatementVO.setOci(oci);
		incomeStatementVO.setCurrentProfitAndLoss(currentProfitAndLoss);
		incomeStatementVO.setEarningPerShare(earningPerShare);
		dao.insert(incomeStatementVO);
				
	}
	public void update(
			Integer stockNo,String statementDate,Long operatingRevenue,
			Long operatingCost,Long operatingMargain,Long netOperatingMargain,
			Long operatingExpenses,Long operatingIncome,Long nonOperatingRevenue,
			Long oibt,Long incomeTaxExpense,Long coiat,Long netIncome,
			Long oci,Long currentProfitAndLoss,Double earningPerShare){
			IncomeStatementVO incomeStatementVO=new IncomeStatementVO();
			StockVO stockVO=new StockVO();
			stockVO.setStockNo(stockNo);
			incomeStatementVO.setStockVO(stockVO);
			incomeStatementVO.setStatementDate(statementDate);
			incomeStatementVO.setOperatingRevenue(nonOperatingRevenue);
			incomeStatementVO.setOperatingCost(operatingCost);
			incomeStatementVO.setOperatingMargain(operatingMargain);
			incomeStatementVO.setNetOperatingMargain(netOperatingMargain);
			incomeStatementVO.setOperatingExpenses(operatingExpenses);
			incomeStatementVO.setOperatingIncome(operatingIncome);
			incomeStatementVO.setNonOperatingRevenue(nonOperatingRevenue);
			incomeStatementVO.setOibt(oibt);
			incomeStatementVO.setIncomeTaxExpense(incomeTaxExpense);
			incomeStatementVO.setCoiat(coiat);
			incomeStatementVO.setNetIncome(netIncome);
			incomeStatementVO.setOci(oci);
			incomeStatementVO.setCurrentProfitAndLoss(currentProfitAndLoss);
			incomeStatementVO.setEarningPerShare(earningPerShare);
			dao.update(incomeStatementVO);
		}
		public void delete(Integer stockNo,String statementDate){
			StockVO stockVO=new StockVO();
			stockVO.setStockNo(stockNo);
			IncomeStatementVO incomeStatementVO=new IncomeStatementVO();
			incomeStatementVO.setStockVO(stockVO);
			incomeStatementVO.setStatementDate(statementDate);
			dao.delete(incomeStatementVO);
			
		}
		public IncomeStatementVO getOneStockIncomeStatement(Integer stockNo,String statementDate){
			StockVO stockVO=new StockVO();
			stockVO.setStockNo(stockNo);
			IncomeStatementVO incomeStatementVO=new IncomeStatementVO();
			incomeStatementVO.setStockVO(stockVO);
			incomeStatementVO.setStatementDate(statementDate);
			incomeStatementVO=dao.findByPrimaryKey(incomeStatementVO);
			return incomeStatementVO;
		}
		public List<IncomeStatementVO> getByStockNo(Integer stockNo){
			List<IncomeStatementVO> list=dao.getByStockNo(stockNo);
			return list;
			
		}
		public void deleteByStockNO(Integer stockNo){
			dao.deleteByStockNo(stockNo);
		}
		
}

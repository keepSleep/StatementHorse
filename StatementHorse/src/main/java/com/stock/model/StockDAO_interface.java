package com.stock.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.incomestatement.model.IncomeStatementVO;
import com.mgr.model.MGRVO;
import com.price.model.PriceVO;

public interface StockDAO_interface {
		public void insert(StockVO stockVO);
		public void update(StockVO stockVO);
		public void delete(StockVO stockNo);
		public StockVO findByPrimaryKey(StockVO stockVO);
		public List<StockVO> getAll();
		public Set<MGRVO> getRevenueByStockNo(StockVO stockVO);
		public Set<PriceVO> getPriceByStockNo(StockVO stockVO);
		public Set<IncomeStatementVO> getIncomeStatementByStockNo(StockVO stockVO);
		
}

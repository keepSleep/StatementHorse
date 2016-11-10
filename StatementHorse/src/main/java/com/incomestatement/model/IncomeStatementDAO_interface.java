package com.incomestatement.model;

import java.util.*;

import com.stock.model.StockVO;

public interface IncomeStatementDAO_interface {
	public void insert(IncomeStatementVO incomeStatementVO);
	public void update(IncomeStatementVO incomeStatementVO);
	public void delete(IncomeStatementVO incomeStatementVO);
	public void deleteByStockNo(Integer stockNo);
	public IncomeStatementVO findByPrimaryKey(IncomeStatementVO incomeStatementVO);
	public List<IncomeStatementVO> getAll();
	public List<IncomeStatementVO> getByStockNo(Integer stockNo);
	
	
}

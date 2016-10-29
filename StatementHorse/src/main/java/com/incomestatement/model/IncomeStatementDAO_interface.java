package com.incomestatement.model;

import java.util.*;

public interface IncomeStatementDAO_interface {
	public void insert(IncomeStatementVO incomeStatementVO);
	public void update(IncomeStatementVO incomeStatementVO);
	public void delete(IncomeStatementVO incomeStatementVO);
	public IncomeStatementVO findByPrimaryKey(IncomeStatementVO incomeStatementVO);
	public List<IncomeStatementVO> getAll();
}

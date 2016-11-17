package com.financialstatements.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.incomestatement.model.IncomeStatementVO;


public interface FinancialStatements_interface {
    public void insert(FinancialStatementsVO financialStatementsVO);
    public void update(FinancialStatementsVO financialStatementsVO);
    public void delete(FinancialStatementsVO financialStatementsVO);
    public FinancialStatementsVO findByPrimaryKey(FinancialStatementsVO financialStatementsVO);
    public List<FinancialStatementsVO> getAll();
    public List<FinancialStatementsVO> findByStockNo(Integer stockno);
    public List<FinancialStatementsVO> getByPostDate(FinancialStatementsVO financialStatementsVO);
    public List<FinancialStatementsVO> getByStockNo(Integer stockno);
    //------------財報比較查詢全部statementDates---By葉哲-----------------
    public List<FinancialStatementsVO> getAllSDs();
    public List<Object[]> getByStockNoByShao(ArrayList stockNoArrayList);

    //�d�߬Y���������u(�@��h)(�^�� Set)
//    public Set<BalanceSheetVO> getBalanceSheetsByStockNoAndStatementDate(Integer stock_no,String statement_date);
//    public Set<IncomeStatementVO> getIncomeStatementsByStockNo(Integer stock_no);
    
    
    
}

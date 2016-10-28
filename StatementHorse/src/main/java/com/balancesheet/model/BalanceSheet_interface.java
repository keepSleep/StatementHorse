package com.balancesheet.model;

import java.util.List;

public interface BalanceSheet_interface {
    public void insert(BalanceSheetVO balanceSheetVO);
    public void update(BalanceSheetVO balanceSheetVO);
    public void delete(BalanceSheetVO balanceSheetVO);
    public BalanceSheetVO findByPrimaryKey(BalanceSheetVO balanceSheetVO);
    public List<BalanceSheetVO> getAll();
}

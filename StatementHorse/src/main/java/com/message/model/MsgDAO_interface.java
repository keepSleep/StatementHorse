package com.message.model;

import java.util.HashSet;
import java.util.List;

import com.balancesheet.model.BalanceSheetVO;
import com.incomestatement.model.IncomeStatementVO;
import com.mgr.model.MGRVO;
import com.price.model.PriceVO;

public interface MsgDAO_interface {
		public void insert(MsgVO MsgVO);
		//public void update(MsgVO MsgVO);
		public void delete(MsgVO MsgVO);
		public List<MsgVO> findByKey(String member_id,Integer stock_no);
		public List<MsgVO> findStockNo(String member_id);
		public List<MsgVO> getAll();
		public List<BalanceSheetVO> findbalancesheetbystockno(String Stockno) ;
		public List<IncomeStatementVO> findincomestatementbystockno(String Stockno);
		public List<MGRVO> findmgrbystockno(String Stockno);
		public List<MGRVO> findmgrbystocknobyrevencedate(String Stockno);
		public List<PriceVO> findpricebystocknobypricedate(String Stockno);
}

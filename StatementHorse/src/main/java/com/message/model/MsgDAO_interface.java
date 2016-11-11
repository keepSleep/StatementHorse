package com.message.model;

import java.util.HashSet;
import java.util.List;

public interface MsgDAO_interface {
		public void insert(MsgVO MsgVO);
		//public void update(MsgVO MsgVO);
		public void delete(MsgVO MsgVO);
		public List<MsgVO> findByKey(String member_id,Integer stock_no);
		public List<MsgVO> findStockNo(String member_id);
		public List<MsgVO> getAll();

}

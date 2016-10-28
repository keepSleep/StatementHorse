package com.messagelists.model;

import java.util.List;
import java.util.Set;

import com.message.model.MsgVO;

public interface MsgListDAO_interface {
		public void insert(MsgListVO MsgListVO);
		public void update(MsgListVO MsgListVO);
		public void delete(MsgListVO MsgListVO);
		public MsgListVO findByKey(MsgListVO MsgListVO);
		public List<MsgListVO> getAll();
		
}

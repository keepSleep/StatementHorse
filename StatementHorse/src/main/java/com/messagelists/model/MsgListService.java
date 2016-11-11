package com.messagelists.model;

import java.util.List;

public class MsgListService {
	private MsgListHibernateDAO dao;
	public MsgListService(){
		dao=new MsgListHibernateDAO();
	}
	
	public void insert(MsgListVO MsgListVO){
		dao.insert(MsgListVO);
	}
	
	public void update(MsgListVO MsgListVO){
		dao.update(MsgListVO);
	}
	
	public void delete(MsgListVO MsgListVO){
		dao.delete(MsgListVO);
	}
	
	public MsgListVO findByKey(MsgListVO MsgListVO){
		MsgListVO MsgListVo=dao.findByKey(MsgListVO);
		return MsgListVo;
	}
	
	public List<MsgListVO> getAll() {
		List<MsgListVO> list=dao.getAll();
		return list;
	}
}

package com.messagelists.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.message.model.MsgVO;

import hibernate.util.HibernateUtil;

public class MsgListHibernateDAO implements MsgListDAO_interface {

	private static final String GET_ALL = "from MsgListVO order by list_no";

	@Override
	public void insert(MsgListVO MsgListVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(MsgListVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public void update(MsgListVO MsgListVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(MsgListVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(MsgListVO MsgListVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			MsgListVO msglistVO = (MsgListVO) session.get(MsgListVO.class, MsgListVO.getListNo());
			session.delete(msglistVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public MsgListVO findByKey(MsgListVO MsgListVO) {
		MsgListVO msglistvo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			msglistvo = (MsgListVO) session.get(MsgListVO.class, MsgListVO.getListNo());
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return msglistvo;
	}

	@Override
	public List<MsgListVO> getAll() {
		List<MsgListVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
		}
		return list;
	}



	public static void main(String[] args) {

		MsgListHibernateDAO msglistdao = new MsgListHibernateDAO();
		MsgListVO msglistvo = new MsgListVO();
		
//		Set<MsgVO> msgs = new HashSet<>();
		//-------------新增 insert---------------------
//		msglistvo.setListName("我是一個通知設定");
//		msglistdao.insert(msglistvo);
		
		
		//-------------更新 update---------------------
//		msglistvo.setListNo(4);
//		msglistvo.setListName("我是我");
//		msglistdao.update(msglistvo);
		
		
		//------------刪除delete------------------------
//		msglistvo.setListNo(4);
//		msglistdao.delete(msglistvo);

		// ------------單個查詢findByKey----------------

//		 Set<MsgVO> msgs = new HashSet<>();
//		 msglistvo.setListNo(2);
//		 MsgListVO msglist = msglistdao.findByKey(msglistvo);
//		 System.out.print(msglist.getListNo() + ",");
//		 System.out.print(msglist.getListName());
		 
//		 Set<MsgVO> msgs = msglist.getMessages();
//		 for (MsgVO msgvo : msgs) {
//		 System.out.print(msgvo.getMemberId() + ",");
//		 System.out.print(msgvo.getStockNo());
//		 System.out.println();
//		 }

		// ------查詢全部getAll--------
		List<MsgListVO> list2 = msglistdao.getAll();
		for (MsgListVO msglistvo2 : list2) {
			System.out.print(msglistvo2.getListNo() + ",");
			System.out.print(msglistvo2.getListName());
			System.out.println();
			Set<MsgVO> msg2 = msglistvo2.getMessages();
			for (MsgVO msgvo2 : msg2) {
				System.out.print(msgvo2.getMemberVO().getMemberId() + ",");
//				System.out.print(msgvo2.getStockNo());
				System.out.println();
			}
		}
	}

}

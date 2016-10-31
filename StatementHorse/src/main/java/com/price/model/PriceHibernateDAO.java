package com.price.model;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;


import hibernate.util.HibernateUtil;

public class PriceHibernateDAO implements PriceDAO_interface {
	
	private static final String GET_ALL_STMT = "FROM PriceVO order by stockNo, priceDate";

	@Override
	public void insert(PriceVO priceVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(priceVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}				
	}

	@Override
	public void update(PriceVO priceVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(priceVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(PriceVO priceVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			priceVO = (PriceVO)session.get(PriceVO.class, priceVO);
			session.delete(priceVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public PriceVO findByPrimaryKey(PriceVO priceVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			priceVO = (PriceVO) session.get(PriceVO.class, priceVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return priceVO;
	}

	@Override
	public List<PriceVO> getAll() {
		List<PriceVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
}

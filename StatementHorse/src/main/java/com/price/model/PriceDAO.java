package com.price.model;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;









import com.mgr.model.MGRVO;
import com.stock.model.StockVO;

import hibernate.util.HibernateUtil;

public class PriceDAO implements PriceDAO_interface {
	
	private static final String GET_ALL_STMT = 
			"FROM PriceVO order by stock_no, price_date";
	private static final String GET_By_StockNo_STMT=
			"from PriceVO where stock_no=:stockVO order by Price_Date ";
	private static final String DELETE_BY_STOCKNO=
			"delete from PriceVO where stock_no=:stockVO";
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
			session.delete(priceVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	

	@Override
	public void deleteByStockNo(Integer stockNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query=session.createQuery(DELETE_BY_STOCKNO);
			StockVO stockVO=new StockVO();
			PriceVO priceVO=new PriceVO();
			stockVO.setStockNo(stockNo);
			priceVO.setStockVO(stockVO);
			query.setProperties(priceVO);
			int updateCount=query.executeUpdate();
			System.out.println("成功更新"+updateCount+"筆");
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

	@Override
	public List<PriceVO> getByStockNo(Integer stockNo) {
		List<PriceVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_By_StockNo_STMT);
			PriceVO priceVO=new PriceVO();
			StockVO stockVO=new StockVO();
			stockVO.setStockNo(stockNo);
			priceVO.setStockVO(stockVO);
			query.setProperties(priceVO);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
		
	}
	
}

package com.incomestatement.model;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;

import com.price.model.PriceVO;
import com.stock.model.StockVO;

import hibernate.util.HibernateUtil;

public class IncomeStatementDAO implements IncomeStatementDAO_interface {
		private static final String GET_ALL_STMT = 	
				"FROM IncomeStatementVO order by stock_no, statement_date";
		private static final String GET_By_StockNo_STMT=
				"from IncomeStatementVO where stock_no=:stockVO order by statement_date ";
		private static final String DELETE_BY_STOCKNO=
				"delete from IncomeStatementVO where stock_no=:stockVO";
		
	@Override
	public void insert(IncomeStatementVO incomeStatementVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(incomeStatementVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}				
	}

	@Override
	public void update(IncomeStatementVO incomeStatementVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(incomeStatementVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(IncomeStatementVO incomeStatementVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(incomeStatementVO);
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
			IncomeStatementVO incomeStatement=new IncomeStatementVO();
			StockVO stockVO=new StockVO();
			stockVO.setStockNo(stockNo);
			incomeStatement.setStockVO(stockVO);
			query.setProperties(incomeStatement);
			int updateCount=query.executeUpdate();
			System.out.println("成功更新"+updateCount+"筆");
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public List<IncomeStatementVO> getByStockNo(Integer stockNo) {
		List<IncomeStatementVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_By_StockNo_STMT);
			IncomeStatementVO incomeStatementVO=new IncomeStatementVO();
			StockVO stockVO=new StockVO();
			stockVO.setStockNo(stockNo);
			incomeStatementVO.setStockVO(stockVO);
			query.setProperties(incomeStatementVO);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
		
	}


	@Override
	public IncomeStatementVO findByPrimaryKey(IncomeStatementVO incomeStatementVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			incomeStatementVO = (IncomeStatementVO) session.get(IncomeStatementVO.class, incomeStatementVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return incomeStatementVO;
	}

	@Override
	public List<IncomeStatementVO> getAll() {
		List<IncomeStatementVO> list = null;
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

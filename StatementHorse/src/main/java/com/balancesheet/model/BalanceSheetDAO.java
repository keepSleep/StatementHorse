package com.balancesheet.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.price.model.PriceVO;
import com.stock.model.StockVO;

import hibernate.util.HibernateUtil;

public class BalanceSheetDAO implements BalanceSheet_interface {
	
	private static final String GET_ALL_STMT = "FROM BalanceSheetVO ORDER BY stockNo , statementDate";
	private static final String GET_By_StockNo_STMT=
			"from BalanceSheetVO where stock_no=:stockVO order by statement_date ";
	@Override
	public void insert(BalanceSheetVO balanceSheetVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(balanceSheetVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}	
	}

	@Override
	public void update(BalanceSheetVO balanceSheetVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(balanceSheetVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}	
	}

	@Override
	public void delete(BalanceSheetVO balanceSheetVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			balanceSheetVO = (BalanceSheetVO) session.get(BalanceSheetVO.class, balanceSheetVO);
			session.delete(balanceSheetVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public BalanceSheetVO findByPrimaryKey(BalanceSheetVO balanceSheetVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			balanceSheetVO = (BalanceSheetVO) session.get(BalanceSheetVO.class, balanceSheetVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return balanceSheetVO;
	}

	@Override
	public List<BalanceSheetVO> getAll() {
		List<BalanceSheetVO> list = null;
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
	public List<BalanceSheetVO> getByStockNo(Integer stockNo) {
		List<BalanceSheetVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_By_StockNo_STMT);
			BalanceSheetVO balanceSheetVO=new BalanceSheetVO();
			StockVO stockVO=new StockVO();
			stockVO.setStockNo(stockNo);
			balanceSheetVO.setStockVO(stockVO);
			query.setProperties(balanceSheetVO);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
}

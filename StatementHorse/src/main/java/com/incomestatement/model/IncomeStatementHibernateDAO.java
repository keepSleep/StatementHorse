package com.incomestatement.model;

import java.sql.*;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;
import com.price.model.PriceVO;

public class IncomeStatementHibernateDAO implements IncomeStatementDAO_interface {
		private static final String GET_ALL_STMT = 	"FROM IncomeStatementVO order by stockNo, statementDate";
	
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
			incomeStatementVO = (IncomeStatementVO) session.get(IncomeStatementVO.class, incomeStatementVO);
			session.delete(incomeStatementVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
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

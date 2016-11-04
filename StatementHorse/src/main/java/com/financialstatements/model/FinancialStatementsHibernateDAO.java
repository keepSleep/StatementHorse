package com.financialstatements.model;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import com.balancesheet.model.BalanceSheetVO;
import com.incomestatement.model.IncomeStatementVO;


import hibernate.util.HibernateUtil;

public class FinancialStatementsHibernateDAO implements FinancialStatements_interface {
	private static final String GET_ALL_STMT = "FROM FinancialStatementsVO ORDER BY stockNo , statementDate";
	private static final String GET_BY_POST_DATE_STMT ="FROM FinancialStatementsVO where post_date=? order by post_time";

	@Override
	public void insert(FinancialStatementsVO financialStatementsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(financialStatementsVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(FinancialStatementsVO financialStatementsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(financialStatementsVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(FinancialStatementsVO financialStatementsVO) {
//		IncomeStatementHibernateDAO isDao = new IncomeStatementHibernateDAO();
//		IncomeStatementVO isVO = new IncomeStatementVO();
//		BalanceSheetHibernateDAO bsDao = new BalanceSheetHibernateDAO();
//		BalanceSheetVO bsVO = new BalanceSheetVO();
//		
//		int stockNo = financialStatementsVO.getStockNo();
//		String statementDate = financialStatementsVO.getStatementDate();
//		
//		isVO.setStockNo(stockNo);
//		isVO.setStatementDate(statementDate);
//		bsVO.setStockNo(stockNo);
//		bsVO.setStatementDate(statementDate);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
//			isDao.delete(isVO);
//			bsDao.delete(bsVO);
			financialStatementsVO = (FinancialStatementsVO) session.get(FinancialStatementsVO.class, financialStatementsVO);
			session.delete(financialStatementsVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public FinancialStatementsVO findByPrimaryKey(FinancialStatementsVO financialStatementsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			financialStatementsVO = (FinancialStatementsVO) session.get(FinancialStatementsVO.class, financialStatementsVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return financialStatementsVO;
	}

	@Override
	public List<FinancialStatementsVO> getAll() {
		List<FinancialStatementsVO> list = null;
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
	public List<FinancialStatementsVO> getByPostDate(FinancialStatementsVO financialStatementsVO){
		List<FinancialStatementsVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_BY_POST_DATE_STMT);
			query.setParameter(0, financialStatementsVO.getPostDate());
			list= query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
		
		return list;

	}
	
}

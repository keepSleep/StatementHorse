package com.financialstatements.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.balancesheet.model.BalanceSheetDAO;
import com.balancesheet.model.BalanceSheetVO;
import com.incomestatement.model.IncomeStatementDAO;
import com.incomestatement.model.IncomeStatementVO;
import com.stock.model.StockVO;

import hibernate.util.HibernateUtil;

public class FinancialStatementsDAO implements FinancialStatements_interface {
	private static final String GET_ALL_STMT = "FROM FinancialStatementsVO ORDER BY stockNo , statementDate";
	private static final String GET_BY_POST_DATE_STMT ="FROM FinancialStatementsVO where postDate=? order by postTime";
	private static final String GET_DATE_BY_STOCK="FROM FinancialStatementsVO Where stockNo=? ORDER BY postDate desc";
	private static final String GET_BY_STOCK="FROM FinancialStatementsVO Where stockNo=? ORDER BY postDate asc";
	
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
	public List<FinancialStatementsVO> findByStockNo(Integer stockno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<FinancialStatementsVO> list=null;
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_DATE_BY_STOCK);
			query.setParameter(0, stockno);
			list=query.list();
			session.getTransaction().commit();			
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	

	
	@Override
	public List<FinancialStatementsVO> getByStockNo(Integer stockno) {
		
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			List<FinancialStatementsVO> list=null;
			try {
				session.beginTransaction();
				Query query = session.createQuery(GET_BY_STOCK);
				query.setParameter(0, stockno);
				list=query.list();
				session.getTransaction().commit();			
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
			return list;
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

	
	//------------財報比較查詢全部statementDates---By葉哲-----------------
	private static final String GET_ALL_SDs = "select statement_date FROM FINANCIAL_STATEMENTS GROUP BY statement_date ORDER BY statement_date desc";
	@Override
	public List<FinancialStatementsVO> getAllSDs() {
		List<FinancialStatementsVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createSQLQuery(GET_ALL_SDs);
			list= query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<Object[]> getByStockNoByShao(ArrayList stockNoArrayList) {
		
		List<Object[]>list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			String s1 = "";
			
			for( int i = 0 ; i < stockNoArrayList.size() ; i++ ){	
				
				if(i>=1){
					s1 += " or s.stock_no =";};		
					
				s1 += stockNoArrayList.get(i);	
				
			};
			
			s1 += " ";

			String sql = "select f.stock_no, s.stock_name, f.statement_date, f.post_date from FINANCIAL_STATEMENTS f join STOCK s on f.stock_no = s.stock_no where s.stock_no =" + s1 + "order by post_date desc";				
			Query query = session.createSQLQuery(sql);
			
			list = query.list();
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return list;
	}
	
	
}

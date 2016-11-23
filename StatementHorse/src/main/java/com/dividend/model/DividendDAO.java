package com.dividend.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.price.model.PriceVO;
import com.stock.model.StockVO;

import hibernate.util.HibernateUtil;

public class DividendDAO implements Dividend_interface {

	private static final String GET_ALL_STMT = 
			"from DividendVO";
	private static final String GET_By_StockNo_STMT=
			"from DividendVO where stock_no=:stockVO ";
	private static final String DELETE_BY_STOCKNO=
			"delete from DividendVO where stock_no=:stockVO";
	
	@Override
	public void insert(DividendVO dividendVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(dividendVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public void update(DividendVO dividendVO) {
		Session session =HibernateUtil.getSessionFactory().getCurrentSession();
			try{
			session.beginTransaction();
			session.saveOrUpdate(dividendVO);
			session.getTransaction().commit();
			}catch(RuntimeException ex){
				session.getTransaction().rollback();
				throw ex;
			}
	}

	@Override
	public void delete(DividendVO dividendVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.delete(dividendVO);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
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
			DividendVO dividendVO=new DividendVO();
			stockVO.setStockNo(stockNo);
			dividendVO.setStockVO(stockVO);
			query.setProperties(dividendVO);
			int updateCount=query.executeUpdate();
			System.out.println("成功更新"+updateCount+"筆");
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public List<DividendVO> getByStockNo(Integer stockNo) {
		List<DividendVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_By_StockNo_STMT);
			DividendVO dividendVO=new DividendVO();
			StockVO stockVO=new StockVO();
			stockVO.setStockNo(stockNo);
			dividendVO.setStockVO(stockVO);
			query.setProperties(dividendVO);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
		
	}


	@Override
	public DividendVO findByPrimaryKey(DividendVO dividendVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			dividendVO = (DividendVO)session.get(DividendVO.class, dividendVO);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
		return dividendVO;
	}

	@Override
	public List<DividendVO> getAll() {
		List<DividendVO> list =null;
		Session session =HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
		
		return list;
	}
	

}

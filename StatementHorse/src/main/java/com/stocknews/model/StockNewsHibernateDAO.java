package com.stocknews.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.news.model.NewsVO;
import com.stock.model.StockVO;

import hibernate.util.HibernateUtil;

public class StockNewsHibernateDAO implements StockNewsDAOinterface {

private static final String GET_ALL_STMT = "from Stock_News_VO order by stockno";

	@Override
	public void insert(StockNewsVO stock_News_VO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(stock_News_VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public void update(StockNewsVO stock_News_VO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(stock_News_VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public void delete(Integer stockno,Integer newsid) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			NewsVO NewsVO=new NewsVO();
			StockVO StockVO=new StockVO();
			
			StockNewsVO stockNewsVO = new StockNewsVO();
			NewsVO.setNewsId(newsid);
			StockVO.setStockNo(stockno);
			
			stockNewsVO.setStockVO(StockVO);
			stockNewsVO.setNewsVO(NewsVO);
			
			session.delete(stockNewsVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public StockNewsVO findByPrimaryKey(Integer stockno) {
		StockNewsVO stock_News_VO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			stock_News_VO = (StockNewsVO) session.get(StockNewsVO.class, stockno);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
	
			session.getTransaction().rollback();
			throw ex;
		}
		return stock_News_VO;
	}

	@Override
	public List<StockNewsVO> getAll() {
		List<StockNewsVO> list = null;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StockNewsVO> getAllByStockNo(Integer stockno) {
		
		List<StockNewsVO> snVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			snVO = (List<StockNewsVO>) session.createQuery("from StockNewsVO where stock_no=?")
					.setParameter(0,stockno).list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return snVO;
		
	}

}

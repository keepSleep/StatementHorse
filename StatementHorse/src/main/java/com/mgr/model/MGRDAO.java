package com.mgr.model;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.mgr.model.MGRVO;
import com.stock.model.StockVO;

import hibernate.util.HibernateUtil;

public class MGRDAO implements MGRDAO_interface {

	private static final String GET_ALL_STMT=
			"from MGRVO order by stock_no ";
	private static final String GET_By_StockNo_STMT=
			"from MGRVO where stock_no=:stockVO order by Revenue_Date ";
	private static final String DELETE_BY_STOCKNO=
			"delete from MGRVO where stock_no=:stockVO";
	private static final String GET_DATE_BY_STOCK="FROM MGRVO Where stock_no=:stockVO ORDER BY postDate desc";	
	
	@Override
	public List<MGRVO> getByStockNo(Integer stockNo) {
		List<MGRVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_By_StockNo_STMT);
			MGRVO mgrVO=new MGRVO();
			StockVO stockVO=new StockVO();
			stockVO.setStockNo(stockNo);
			mgrVO.setStockVO(stockVO);
			query.setProperties(mgrVO);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
		
	}

	@Override
	public void insert(MGRVO mgrVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(mgrVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public void update(MGRVO mgrVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(mgrVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public void delete(MGRVO mgrVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(mgrVO);
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
			MGRVO mgrVO=new MGRVO();
			StockVO stockVO=new StockVO();
			stockVO.setStockNo(stockNo);
			mgrVO.setStockVO(stockVO);
			query.setProperties(mgrVO);
			int updateCount=query.executeUpdate();
			System.out.println("成功更新"+updateCount+"筆");
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public MGRVO FindByPrimaryKey(MGRVO mgrVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			mgrVO = (MGRVO) session.get(MGRVO.class, mgrVO);
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		    return mgrVO;
	
	}

	@Override
	public List<MGRVO> findByStockNo(Integer stockno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<MGRVO> list=null;
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_DATE_BY_STOCK);
			MGRVO mgrVO=new MGRVO();
			StockVO stockVO=new StockVO();
			stockVO.setStockNo(stockno);
			mgrVO.setStockVO(stockVO);
			query.setProperties(mgrVO);
			list=query.list();
			session.getTransaction().commit();			
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}	
	@Override
	public List<MGRVO> getAll() {
		List<MGRVO> list = null;
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

//	public static void main(String[] args) {
//		MGRDAO dao=new MGRDAO(); 
        //新增(舊股號新增測試ok，新股號)
//		StockVO stockVO = new StockVO(); // 部門POJO
//		stockVO.setStockNo(2412);
//		MGRVO mgrVO1 =new MGRVO();
//		mgrVO1.setStockVO(stockVO);
//		mgrVO1.setRevenueDate("201609");
//		mgrVO1.setRevenue(56679900);
//		mgrVO1.setPostDate(java.sql.Date.valueOf("2016-9-10"));
//		dao.insert(mgrVO1);
//		System.out.println("insert_ok");
		//修改(測試ok)
//		stock.model.StockVO stockVO = new stock.model.StockVO(); // 部門POJO
//		stockVO.setStockNo(3008);
//		MGRVO mgrVO2 =new MGRVO();
//		mgrVO2.setStockVO(stockVO);
//		mgrVO2.setRevenueDate("201608");
//		mgrVO2.setRevenue(5);
//		mgrVO2.setPostDate(java.sql.Date.valueOf("2016-09-22"));
//		dao.update(mgrVO2);
//		System.out.println("update_ok");
		//刪除(測試ok)
//		stock.model.StockVO stockVO = new stock.model.StockVO(); // 部門POJO
//		stockVO.setStockNo(3008);
//		dao.delete(stockVO, "201609");
//		System.out.println("delete_ok");
//		StockVO stockVO = new StockVO(); // 部門POJO
//		stockVO.setStockNo(3008);
//		MGRVO mgrVO=new MGRVO();
//		mgrVO.setStockVO(stockVO);
//		dao.deleteByStockNo(mgrVO);
//		System.out.println("delete_ok");
		//查詢  by primary key (測試ok)
//		com.stock.model.StockVO stockVO = new com.stock.model.StockVO(); // 部門POJO
//		stockVO.setStockNo(2330);
//			MGRVO mgrVO3 = dao.FindByPrimaryKey(stockVO,"201608");
//	System.out.print(mgrVO3.getRevenueDate() + ",");
//		System.out.print(mgrVO3.getRevenue() + ",");
//		
//		// 注意以下三行的寫法 (優!)
//		System.out.print(mgrVO3.getStockVO().getStockNo());
//		System.out.println("\n---------------------");
		//查詢全部(測試ok)
//		stock.model.StockVO stockVO = new stock.model.StockVO(); // 部門POJO
//		List<MGRVO> list=dao.getAll();
//		for(MGRVO mgrVO:list){
//			System.out.print("stock_no="+mgrVO.getStockVO().getStockNo()+",");
//			System.out.print("revenueDate="+mgrVO.getRevenueDate()+",");
//			System.out.print("revenue="+mgrVO.getRevenue()+",");
//			System.out.println("post_date="+mgrVO.getPostDate());
//	
//		}
//	}
}

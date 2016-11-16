package com.stock.model;

import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import com.incomestatement.model.IncomeStatementVO;
import com.mgr.model.MGRVO;
import com.price.model.PriceVO;

import hibernate.util.HibernateUtil;

public class StockDAO implements StockDAO_interface {
	
	private static final String GET_ALL_STMT=
			"from StockVO order by stockNo ";
	private static final String GET_STOCK_NO=
			"from StockVO where stock_name=:stockName";
	
	@Override
	public void insert(StockVO stockVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(stockVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public void update(StockVO stockVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(stockVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(StockVO stockVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			stockVO = (StockVO) session.get(StockVO.class, stockVO.getStockNo());
			session.delete(stockVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public StockVO findByPrimaryKey(StockVO stockVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		StockVO stockVO1=null;
		try {
			session.beginTransaction();
			stockVO1= (StockVO) session.get(StockVO.class, stockVO.getStockNo());
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return stockVO1;
	}
	@Override
	public List<StockVO> findStockNoByStockName(String stockName){
		List<StockVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		StockVO stockVO1=null;
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_STOCK_NO);
			StockVO stockVO=new StockVO();
			stockVO.setStockName(stockName);
			query.setProperties(stockVO);
			list = query.list();
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<StockVO> getAll() {
		List<StockVO> list = null;
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
	public Set<MGRVO> getRevenueByStockNo(StockVO stockVO) {
		Set<MGRVO>	set = findByPrimaryKey(stockVO).getMgrs();
		return set;
	}

	@Override
	public Set<PriceVO> getPriceByStockNo(StockVO stockVO) {
		Set<PriceVO>	set = findByPrimaryKey(stockVO).getPrices();
		return set;
	}

	@Override
	public Set<IncomeStatementVO> getIncomeStatementByStockNo(StockVO stockVO) {
		Set<IncomeStatementVO>	set = findByPrimaryKey(stockVO).getIncomeStatements();
		return set;
	}

//	public static void main(String[] args) {
//		
//		StockDAO dao=new StockDAO();
////		新增(測試ok)
//		StockVO stockVO=new StockVO();
//		stockVO.setStockNo(2412);
//		stockVO.setStockName("中華電信");
//		stockVO.setIndustry("通信網路");
//		stockVO.setChairman("蔡力行");
//		stockVO.setCompanyEstablishDate(java.sql.Date.valueOf("1996-06-15"));
//		stockVO.setCompanyTaxId("96979933");
//		stockVO.setListingDate(java.sql.Date.valueOf("2000-10-27"));
//		stockVO.setStockholdersMeetingDate(java.sql.Date.valueOf("2016-06-24"));
//		stockVO.setStockWebaddress("http://www.cht.com.tw");
//		stockVO.setCapital(77574);
//		stockVO.setAccountingFirm("勤業眾信聯合會計師事務所");
//		dao.insert(stockVO);
//		System.out.println("insert_ok");
////		修改(測試ok)
//		StockVO stockVO1=new StockVO();
//		stockVO1.setStockNo(2412);
//		stockVO1.setStockName("中華電信");
//		stockVO1.setIndustry("通信網路");
//		stockVO1.setChairman("蔡力行");
//		stockVO1.setCompanyEstablishDate(java.sql.Date.valueOf("1996-06-15"));
//		stockVO1.setCompanyTaxId("96979933");
//		stockVO1.setListingDate(java.sql.Date.valueOf("2000-10-27"));
//		stockVO1.setStockholdersMeetingDate(java.sql.Date.valueOf("2016-06-24"));
//		stockVO1.setStockWebaddress("http://www.cht.com.tw");
//		stockVO1.setCapital(77574);
//		stockVO1.setAccountingFirm("超有良心聯合會計師事務所");
//		dao.update(stockVO1);
//		System.out.println("update_ok");
////		刪除(測試ok)
//		
//		dao.delete(2412);
//		System.out.println("delete_ok");
//
//		//find By Primary Key
//		StockVO stockVO2=dao.findByPrimaryKey(2330);
//		System.out.println(stockVO2.getStockNo());
//		System.out.println(stockVO2.getStockName());
//		System.out.println(stockVO2.getIndustry());
//		System.out.println(stockVO2.getChairman());
//		System.out.println(stockVO2.getCompanyEstablishDate());
//		System.out.println(stockVO2.getCompanyTaxId());
//		System.out.println(stockVO2.getListingDate());
//		System.out.println(stockVO2.getStockholdersMeetingDate());
//		System.out.println(stockVO2.getStockWebaddress());
//		System.out.println(stockVO2.getCapital());
//		System.out.println(stockVO2.getAccountingFirm());
//		System.out.println("select_ok");
//		
////		● 查詢-findByPrimaryKey (優秀!) (一方dept2.hbm.xml必須設為lazy="false")
////		(測試ok但是沒排序)
//		StockVO stockVO3 = dao.findByPrimaryKey(2330);
//		System.out.print(stockVO3.getStockNo() + ",");
//		System.out.print(stockVO3.getStockName() + ",");
//		System.out.print(stockVO3.getIndustry() + ",");
//		System.out.print(stockVO3.getChairman() + ",");
//		System.out.print(stockVO3.getCompanyEstablishDate() + ",");
//		System.out.print(stockVO3.getCompanyTaxId() + ",");
//		System.out.print(stockVO3.getListingDate() + ",");
//		System.out.print(stockVO3.getStockholdersMeetingDate() + ",");
//		System.out.print(stockVO3.getStockWebaddress() + ",");
//		System.out.print(stockVO3.getCapital() + ",");
//		System.out.print(stockVO3.getAccountingFirm());
//		
//		System.out.println("\n-----------------");
//		Set<MGRVO> set3 = stockVO3.getMgrs();
//		for (MGRVO aEmp : set3) {
//			System.out.print(aEmp.getRevenueDate() + ",");
//			System.out.print(aEmp.getRevenue() + ",");
//			System.out.print(aEmp.getPostDate() );
//			
//			System.out.println();
//		}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		//查詢全部 select get all 
//		List<StockVO> list=dao.getAll();
//		for(StockVO stock:list){
//		System.out.print(stock.getStockNo()+",");
//		System.out.print(stock.getStockName()+",");
//		System.out.print(stock.getIndustry()+",");
//		System.out.print(stock.getChairman()+",");
//		System.out.print(stock.getCompanyEstablishDate()+",");
//		System.out.print(stock.getCompanyTaxId()+",");
//		System.out.print(stock.getListingDate()+",");
//		System.out.print(stock.getStockholdersMeetingDate()+",");
//		System.out.print(stock.getStockWebaddress()+",");
//		System.out.print(stock.getCapital()+",");
//		System.out.println(stock.getAccountingFirm());
//		}
//		System.out.println("select_all_ok");
//		
//		//查詢Revenue By stockNo
//		Set<MGRVO> set =dao.getRevenueByStockno(2330);
//		for(MGRVO mgrVO:set){
//			System.out.print(mgrVO.getStockVO().getStockNo()+",");
//			System.out.print(mgrVO.getRevenueDate()+",");
//			System.out.print(mgrVO.getRevenue()+",");
//			System.out.println(mgrVO.getPostDate());
//		}
//		System.out.println("select_Revenue_ok");
//		
//	}
//


}

package com.news.model;

/*
 Hibernate is providing a factory.getCurrentSession() method for retrieving the current session. A
 new session is opened for the first time of calling this method, and closed when the transaction is
 finished, no matter commit or rollback. But what does it mean by the “current session”? We need to
 tell Hibernate that it should be the session bound with the current thread.

 <hibernate-configuration>
 <session-factory>
 ...
 <property name="current_session_context_class">thread</property>
 ...
 </session-factory>
 </hibernate-configuration>

 */

import org.hibernate.*;

import hibernate.util.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NewsHibernateDAO implements NewsDAOinterface {

	private static final String GET_ALL_STMT = "from NewsVO order by newsId";
	private static final String GET_ONE_BY_TITLE = "from NewsVO where news_title=?";
	@Override
	public void insert(NewsVO newsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(newsVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public void update(NewsVO newsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(newsVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer newsId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			// 【此時多方(宜)可採用HQL刪除】
			// Query query = session.createQuery("delete EmpVO where empno=?");
			// query.setParameter(0, empno);
			// System.out.println("刪除的筆數=" + query.executeUpdate());

			// 【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
			NewsVO newsVO = new NewsVO();
			newsVO.setNewsId(newsId);
			session.delete(newsVO);

			// 【此時多方不可(不宜)採用cascade聯級刪除】
			// 【多方emp2.hbm.xml如果設為 cascade="all"或
			// cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除】
			// EmpVO empVO = (EmpVO) session.get(EmpVO.class, empno);
			// session.delete(empVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> list = null;
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
	public List<NewsVO> getOneByTitle(String title) {
		List<NewsVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ONE_BY_TITLE);
			query.setParameter(0, title.trim());
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	@Override
	public NewsVO findByPrimaryKey(Integer newsId) {
		NewsVO newsVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			newsVO = (NewsVO) session.get(NewsVO.class, newsId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {

			session.getTransaction().rollback();
			throw ex;
		}
		return newsVO;
	}

	public static void main(String[] args) throws ParseException {
		NewsDAOinterface dao = new NewsHibernateDAO();
		SimpleDateFormat var = new SimpleDateFormat("yyyy/MM/dd");
		//新增新聞
		NewsVO newsVO = new NewsVO();
		newsVO.setNewsDate(var.parse("2016/10/13"));
		newsVO.setNewsTitle("台積電財務長何麗梅稱，Q4狀況比先前預估佳，市場庫存溫和");
		newsVO.setNewsWebaddress("https://tw.finance.yahoo.com/news_content/url/d/a/20161013/%E5%80%8B%E8%82%A1-%E5%8F%B0%E7%A9%8D%E9%9B%BB-2330-%E8%B2%A1%E5%8B%99%E9%95%B7%E4%BD%95%E9%BA%97%E6%A2%85%E7%A8%B1-q4%E7%8B%80%E6%B3%81%E6%AF%94%E5%85%88%E5%89%8D%E9%A0%90%E4%BC%B0%E4%BD%B3-231055396.html");
		dao.insert(newsVO);
		NewsVO newsVO1 = dao.findByPrimaryKey(10);
		System.out.println("新增一則新聞"+newsVO1.getNewsId()+newsVO1.getNewsDate()+newsVO1.getNewsTitle());
		//更新新聞內容
		newsVO1.setNewsDate(var.parse("2016/10/14"));
		newsVO1.setNewsTitle("台積電(2330)財務長何麗梅稱，Q4狀況比先前預估佳，市場庫存溫和");
		dao.update(newsVO1);
		System.out.println("更新新聞內容"+newsVO1.getNewsDate()+" / "+newsVO1.getNewsTitle());
		//getAll測試，確認是否新增新聞成功
		List <NewsVO> news = dao.getAll();
		if(news.size()==11){
			for (NewsVO data1:news){
				System.out.println("新增的新聞資料"+data1.getNewsId()+"/"+data1.getNewsTitle());
			}
			System.out.println("新增新聞成功");
		}else{
			System.out.println("新增新聞失敗");
		}
		//delete測試
		dao.delete(news.get(news.size()-1).getNewsId());
		// getAll測試，確認是否刪除帳號成功
		news = dao.getAll();
		if(news.size()==10){
			for (NewsVO data1:news){
				System.out.println("新增的新聞資料"+data1.getNewsId()+"/"+data1.getNewsTitle());
			}
			System.out.println("刪除新聞成功");
		}else{
			System.out.println("刪除新聞失敗");
		}
	}
}
package com.tracklisting.model;

import org.hibernate.*;
import hibernate.util.HibernateUtil;
import java.util.*;

public class TrackListingHibernateDAO implements TrackListing_Interface{

	@Override
	public void insert(TrackListingVO trackListingVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(trackListingVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public void update(TrackListingVO trackListingVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(trackListingVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public void delete(Integer listingNo) {
	
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try {
				session.beginTransaction();
				TrackListingVO tlVO = (TrackListingVO) session.get(TrackListingVO.class, listingNo);
				session.delete(tlVO);
				session.getTransaction().commit();
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
			
	}

	@Override
	public TrackListingVO findByPrimaryKey(Integer listingNo) {
		
		TrackListingVO tlVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			tlVO = (TrackListingVO) session.get(TrackListingVO.class, listingNo);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return tlVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TrackListingVO> getAllByMember(String memberId) {
		
		List<TrackListingVO> tlVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			//tlVO = (List<TrackListingVO>) session.get(TrackListingVO.class, member_id); //只能查PK
			tlVO = (List<TrackListingVO>) session.createQuery("from TrackListingVO where member_id=?")
					.setParameter(0,memberId).list(); //.list()是B車的一個方法，可以回傳list物件
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return tlVO;
	}

}

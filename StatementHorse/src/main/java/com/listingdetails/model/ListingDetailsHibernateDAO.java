package com.listingdetails.model;

import org.hibernate.*;

import com.stock.model.StockVO;
import com.tracklisting.model.TrackListingVO;

import hibernate.util.HibernateUtil;
import java.util.*;

public class ListingDetailsHibernateDAO implements ListingDetails_Interface {

	@Override
	public void insert(ListingDetailsVO listingDetailsVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(listingDetailsVO);
			// session.createQuery("insert into LISTING_DETAILS
			// (listing_no,stock_no)")
			// .setParameter("listing_no",listingDetailsVO.getListing_no())
			// .setParameter("stock_no",listingDetailsVO.getStock_no());

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public void delete(Integer listingNo, Integer stockNo) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			ListingDetailsVO ldVO = new ListingDetailsVO();
			StockVO stockVO=new StockVO();
			TrackListingVO trackListingVO = new TrackListingVO();
			
			trackListingVO.setListingNo(listingNo);
			stockVO.setStockNo(stockNo);
			
			ldVO.setTrackListingVO(trackListingVO);
			ldVO.setStockVO(stockVO);
			session.delete(ldVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ListingDetailsVO> getAllByListing(Integer listingNo) {

		List<ListingDetailsVO> ldVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			// Query query = session.createQuery("select Listing_Details_VO
			// where listing_no=?");
			// list = query.list();
			ldVO = (List<ListingDetailsVO>) session.createQuery("from ListingDetailsVO where listing_no=?")
					.setParameter(0, listingNo).list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return ldVO;
	}

	public static void main(String[] args) {
		ListingDetailsHibernateDAO dao = new ListingDetailsHibernateDAO();
		dao.delete(1, 1476);

		List<ListingDetailsVO> list = dao.getAllByListing(1);
		for (ListingDetailsVO ld : list) {
//			System.out.print(ld.getStockNo() + ",");
			System.out.print(ld.getTrackListingVO().getListingNo());
			System.out.println();
		}
	}

}

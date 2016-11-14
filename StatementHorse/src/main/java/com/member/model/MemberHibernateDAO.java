package com.member.model;

import java.util.List;
import java.util.Set;

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
import org.hibernate.Query;
import org.hibernate.Session;

import com.listingdetails.model.ListingDetailsVO;
import com.message.model.MsgVO;
import com.tracklisting.model.TrackListingVO;

import hibernate.util.HibernateUtil;

public class MemberHibernateDAO implements MemberDAOInterface {

	private static final String GET_ALL_STMT = "from MemberVO order by memberId";

	@Override
	public void insert(MemberVO memberVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(memberVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public void update(MemberVO memberVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(memberVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(String memberId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			// 【此時多方(宜)可採用HQL刪除】
			// Query query = session.createQuery("delete EmpVO where empno=?");
			// query.setParameter(0, empno);
			// System.out.println("刪除的筆數=" + query.executeUpdate());

			// 【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
			MemberVO memberVO = new MemberVO();
			memberVO.setMemberId(memberId);
			session.delete(memberVO);

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
	public MemberVO findByPrimaryKey(String memberId) {
		MemberVO memberVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			memberVO = (MemberVO) session.get(MemberVO.class, memberId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {

			session.getTransaction().rollback();
			throw ex;
		}
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = null;
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
	public MemberVO findByMemberEmail(String memberEmail) {
		MemberVO memberVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from MemberVO where memberEmail=?");
			query.setParameter(0, memberEmail);
			memberVO = (MemberVO) query.uniqueResult();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {

			session.getTransaction().rollback();
			throw ex;
		}
		return memberVO;
	}

	public static void main(String[] args) {
		
		MemberDAOInterface dao=new MemberHibernateDAO();
		dao.delete("Blastoise");
		/*
		 * MemberDAOInterface dao = new MemberHibernateDAO(); //
		 * 新增(新增會員ok，新會員帳號) MemberVO memberVO = new MemberVO();
		 * memberVO.setMemberId("Alice");
		 * memberVO.setMemberEmail("Alice@gmail.com");
		 * memberVO.setMemberPassword("123456"); dao.insert(memberVO); MemberVO
		 * memberVO1 = dao.findByPrimaryKey("Alice"); System.out.println("新增帳號"
		 * + memberVO1.getMemberId()); System.out.println("新增信箱" +
		 * memberVO1.getMemberEmail()); System.out.println("新增密碼" +
		 * memberVO1.getMemberPassword()); // 更改密碼
		 * memberVO1.setMemberPassword("234567"); dao.update(memberVO1);
		 * memberVO1 = dao.findByPrimaryKey("Alice"); System.out.println("更改密碼"
		 * + memberVO1.getMemberPassword()); // getAll測試，確認是否新增帳號成功
		 * List<MemberVO> members = dao.getAll(); if (members.size() == 3) { for
		 * (MemberVO data1:members){
		 * System.out.println("所有帳號資料"+data1.getMemberId()+" / "+data1.
		 * getMemberEmail()+" / "+data1.getMemberPassword()); }
		 * System.out.println("帳號筆數無誤"); } else { System.out.println("帳號筆數有誤");
		 * } // delete測試 dao.delete("Alice"); // getAll測試，確認是否刪除帳號成功 members =
		 * dao.getAll(); if (members.size() == 2) { for(MemberVO data2:members){
		 * System.out.println("所有帳號資料"+data2.getMemberId()+" / "+data2.
		 * getMemberEmail()+" / "+data2.getMemberPassword()); //測設連結MsgVO的值
		 * System.out.println("通知明細"); for(MsgVO msgVO:data2.getMsgVOs()){
		 * System.out.println(msgVO.getMemberVO()+"/"+msgVO.getStockNo()+" / "
		 * +msgVO.getMsgListVO().getListNo()); } System.out.println("追蹤清單");
		 * for(TrackListingVO trackListingVO:data2.getTrackListingVOs()){
		 * System.out.println(trackListingVO.getListingName()+trackListingVO.
		 * getMemberVO().getMemberId()+trackListingVO.getListingName()); } }
		 * System.out.println("帳號筆數無誤"); } else { System.out.println("帳號筆數有誤");
		 * }
		 */
//		MemberDAOInterface dao = new MemberHibernateDAO();
//		MemberVO membervo = dao.findByPrimaryKey("Charizard");
//		Set<TrackListingVO> list1 = membervo.getTrackListingVOs();
//		membervo.getMemberId();
//		for (TrackListingVO tl : list1) {
//			Set<ListingDetailsVO> ldvo = tl.getLds();
//			for (ListingDetailsVO ld : ldvo) {
//				System.out.println(ld.getStockVO());
//			}
//		}
	}
}

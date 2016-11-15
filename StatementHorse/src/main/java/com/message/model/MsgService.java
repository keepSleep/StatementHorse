package com.message.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.listingdetails.model.ListingDetailsVO;
import com.member.model.MemberDAOInterface;
import com.member.model.MemberHibernateDAO;
import com.member.model.MemberVO;
import com.messagelists.model.MsgListVO;
import com.stock.model.StockVO;
import com.tracklisting.model.TrackListingHibernateDAO;
import com.tracklisting.model.TrackListingVO;

public class MsgService {
	
	private MsgDAO_interface msgdao=null;
	private MemberDAOInterface memberdao=null;
	
	
	public MsgService(){
		msgdao=new MsgHibernateDAO();
		memberdao=new MemberHibernateDAO();
	}

	public void insert(String memberid,Integer stockno,Integer listno) {
		MsgVO msgvo = new MsgVO();
		MemberVO member=new MemberVO();
		member.setMemberId(memberid);
		MsgListVO msglist=new MsgListVO(); 
		msglist.setListNo(listno);
		StockVO stock=new StockVO();
		stock.setStockNo(stockno);
		
		msgvo.setMemberVO(member);
		msgvo.setStockVO(stock);
		msgvo.setMsgListVO(msglist);
		
		msgdao.insert(msgvo);
	}
	
	public void delete(String memberid,Integer stockno,Integer listno) {
		MsgVO msgvo = new MsgVO();
		MemberVO member=new MemberVO();
		member.setMemberId(memberid);
		MsgListVO msglist=new MsgListVO(); 
		msglist.setListNo(listno);
		StockVO stock=new StockVO();
		stock.setStockNo(stockno);
		
		msgvo.setMemberVO(member);
		msgvo.setStockVO(stock);
		msgvo.setMsgListVO(msglist);
			
		msgdao.delete(msgvo);
	}

	public List<Integer> findByKey(String MemberId,Integer StockNo) {
		List<MsgVO> list=msgdao.findByKey(MemberId, StockNo);
		List<Integer> list1=new LinkedList<>();
		for(MsgVO msgvo:list){
			list1.add(msgvo.getMsgListVO().getListNo());
		}
		return list1;
	}
	
	public List<Integer> findStockNo(String MemberId) {
		Set<Integer> set= new HashSet<>();
		List<Integer> list=new LinkedList<>();
		MemberVO membervo = memberdao.findByPrimaryKey(MemberId);
		TrackListingHibernateDAO tracklisting = new TrackListingHibernateDAO();
		List<TrackListingVO> tracklistvos = tracklisting.getAllByMember(MemberId);
//		Set<TrackListingVO> tracklistvos = membervo.getTrackListingVOs();
		
		
		for(TrackListingVO tlvo:tracklistvos){
			Set<ListingDetailsVO> ldvo = tlvo.getLds();
			for(ListingDetailsVO LDVO:ldvo){
				set.add(LDVO.getStockVO().getStockNo());
			}
		}		
		for(Integer stockno:set){
			list.add(stockno);
		}
		
		return list;
	}
}



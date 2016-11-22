package com.message.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.balancesheet.model.BalanceSheetVO;
import com.incomestatement.model.IncomeStatementVO;
import com.listingdetails.model.ListingDetailsVO;
import com.member.model.MemberDAOInterface;
import com.member.model.MemberHibernateDAO;
import com.member.model.MemberVO;
import com.messagelists.model.MsgListVO;
import com.mgr.model.MGRVO;
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
	
	public List findbalancesheetbystockno(String Stockno) {
		LinkedList list1 = new LinkedList();
		List<BalanceSheetVO> balancesheetvolist = msgdao.findbalancesheetbystockno(Stockno);
		for(BalanceSheetVO bb:balancesheetvolist){
			LinkedList list = new LinkedList();
			list.add(bb.getStockVO().getStockNo());
			list.add(bb.getStatementDate());
			list.add(bb.getCurrentAssets());
			list.add(bb.getFixedAssets());
			list.add(bb.getAssets());
			list.add(bb.getCurrentLiabilities());
			list.add(bb.getLongTermLiabilities());
			list.add(bb.getLiabilities());
			list.add(bb.getCapitalStock());
			list.add(bb.getAdditionalPaidInCapital());
			list.add(bb.getRetainedEarnings());
			list.add(bb.getOtherEquity());
			list.add(bb.getConsolidatedNetIncomeAttributedToStockholdersOfTheCompany());
			list.add(bb.getTotalEquity());		
			list1.add(list);
		}
		
		return list1;
	}
	
	public List findincomestatementbystockno(String Stockno) {
		LinkedList list1 = new LinkedList();
		List<IncomeStatementVO> incomestatementvolist = msgdao.findincomestatementbystockno(Stockno);
		for(IncomeStatementVO aa:incomestatementvolist){
			LinkedList list = new LinkedList();
			list.add(aa.getStockVO().getStockNo());
			list.add(aa.getStatementDate());
			list.add(aa.getOperatingRevenue());
			list.add(aa.getOperatingCost());
			list.add(aa.getOperatingMargain());
			list.add(aa.getNetOperatingMargain());
			list.add(aa.getOperatingExpenses());
			list.add(aa.getOperatingIncome());
			list.add(aa.getNonOperatingRevenue());
			list.add(aa.getOibt());
			list.add(aa.getIncomeTaxExpense());
			list.add(aa.getCoiat());
			list.add(aa.getNetIncome());
			list.add(aa.getOci());
			list.add(aa.getCurrentProfitAndLoss());
			list.add(aa.getEarningPerShare());
			list1.add(list);
		}
		
		return list1;
	}
	
	public List findmgrbystockno(String Stockno) {
		LinkedList list1 = new LinkedList();
		List<MGRVO> mgrvolist = msgdao.findmgrbystockno(Stockno);
		for(MGRVO aa:mgrvolist){
			LinkedList list = new LinkedList();
			list.add(aa.getStockVO().getStockNo());
			list.add(aa.getRevenueDate());
			list.add(aa.getRevenue());
			list.add(aa.getPostDate());
			list1.add(list);
		}
		
		return list1;
	}
	
	public List findmgrbystocknobyrevencedate(String Stockno) {
		
		LinkedList list1 = new LinkedList();
		List<MGRVO> mgrvolist = msgdao.findmgrbystocknobyrevencedate(Stockno);
		
		for(MGRVO aa:mgrvolist){
			LinkedList list = new LinkedList();
			list.add(aa.getStockVO().getStockNo());
			list.add(aa.getRevenueDate());
			list.add(aa.getRevenue());
			list.add(aa.getPostDate());

			list1.add(list);
		}
		
		return list1;
	}
	
}



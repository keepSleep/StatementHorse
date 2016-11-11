package com.tracklisting.model;

import java.util.HashSet;
import java.util.Set;

import com.listingdetails.model.ListingDetailsVO;
import com.member.model.MemberVO;


public class TrackListingVO implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Integer listingNo;
	private String listingName;
	private MemberVO memberVO;
	private Set<ListingDetailsVO> lds = new HashSet<ListingDetailsVO>();
	
	
	public Integer getListingNo() {
		return listingNo;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public void setListingNo(Integer listingNo) {
		this.listingNo = listingNo;
	}
	public String getListingName() {
		return listingName;
	}
	public void setListingName(String listingName) {
		this.listingName = listingName;
	}
	public Set<ListingDetailsVO> getLds() {
		return lds;
	}
	public void setLds(Set<ListingDetailsVO> lds) {
		this.lds = lds;
	}
	

	
}

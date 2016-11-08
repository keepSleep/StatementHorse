package com.member.model;

public interface MemberServiceInterface {
		//
		public MemberVO findMember(String memberEmail,String passsword);
		
		public void insertMember (MemberVO insmember);
		public void updateMember(MemberVO membervo,String password);
		public MemberVO findByMemberid(String memberid, String password) ;
}

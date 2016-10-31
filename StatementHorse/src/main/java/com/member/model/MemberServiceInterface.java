package com.member.model;

public interface MemberServiceInterface {
		//
		public MemberVO findMember(String memberEmail,String passsword);
		
		public void insertMember (MemberVO insmember);
}

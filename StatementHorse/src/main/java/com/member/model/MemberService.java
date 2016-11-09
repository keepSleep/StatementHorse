package com.member.model;

public class MemberService implements MemberServiceInterface {
	private MemberDAOInterface memberDAOInterface;

	public MemberService() {
		memberDAOInterface = new MemberHibernateDAO();
	}

	// 比對現有會員帳號
	@Override
	public MemberVO findMember(String memberEmail, String password) {
		MemberVO memberVO = memberDAOInterface.findByMemberEmail(memberEmail);
		if (memberVO != null && memberVO.getMemberPassword().equals(PasswordEncorder.encrypt(password)))
			return memberVO;
		else
			return null;
	}

	// 新增會員帳號
	@Override
	public void insertMember(MemberVO insmember) {
		insmember.setMemberPassword(PasswordEncorder.encrypt(insmember.getMemberPassword()));
		memberDAOInterface.insert(insmember);
	}
	
	@Override
	public void updateMember(MemberVO membervo,String password){
		membervo.setMemberPassword(password);
		memberDAOInterface.update(membervo);
		
	}
	
	@Override
	public MemberVO findByMemberid(String memberid, String password) {
		MemberVO memberVO = memberDAOInterface.findByPrimaryKey(memberid);
		if (memberVO != null && memberVO.getMemberPassword().equals(password))
			return memberVO;
		else
			return null;
		}

	@Override
	public MemberVO findMember(String memberEmail) {
		return memberDAOInterface.findByMemberEmail(memberEmail);
	}
}

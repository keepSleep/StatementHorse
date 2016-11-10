package com.member.model;

import java.util.List;

public interface MemberDAOInterface {
          public void insert(MemberVO memberVO);
          public void update(MemberVO memberVO);
          public void delete(String memberId);
          public MemberVO findByPrimaryKey(String memberId);
          public List<MemberVO> getAll();
          public MemberVO findByMemberEmail(String memberEmail);
          
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map);
}

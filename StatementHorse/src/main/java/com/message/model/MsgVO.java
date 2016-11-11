package com.message.model;

import java.io.Serializable;

import com.messagelists.model.MsgListVO;
import com.member.model.MemberVO;
import com.stock.model.StockVO;

public class MsgVO implements Serializable {
private MemberVO MemberVO;
private StockVO StockVO;
private MsgListVO MsgListVO;


public MemberVO getMemberVO() {
	return MemberVO;
}
public void setMemberVO(MemberVO memberVO) {
	MemberVO = memberVO;
}
public MsgListVO getMsgListVO() {
	return MsgListVO;
}
public void setMsgListVO(MsgListVO msgListVO) {
	MsgListVO = msgListVO;
}
public StockVO getStockVO() {
	return StockVO;
}
public void setStockVO(StockVO stockVO) {
	StockVO = stockVO;
}



}

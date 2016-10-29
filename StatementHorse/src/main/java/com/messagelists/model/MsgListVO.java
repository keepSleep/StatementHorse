package com.messagelists.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.message.model.MsgVO;

public class MsgListVO implements Serializable {
private int ListNo;
private String ListName;

private Set<MsgVO> messages=new HashSet<MsgVO>();		



public int getListNo() {
	return ListNo;
}
public void setListNo(int listNo) {
	ListNo = listNo;
}
public String getListName() {
	return ListName;
}
public void setListName(String listName) {
	ListName = listName;
}
public Set<MsgVO> getMessages() {
	return messages;
}
public void setMessages(Set<MsgVO> messages) {
	this.messages = messages;
}



}

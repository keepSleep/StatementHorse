package com.member.model;

import java.util.List;
import java.util.Set;

import com.message.model.MsgVO;
import com.tracklisting.model.TrackListingVO;


/**
 * @author whiteyushi
 *
 */
public class MemberVO implements java.io.Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String memberId;//會員編號
	private String memberEmail;//會員E-mail(帳號)
	private String memberPassword;//會員密碼
	private Integer memberCheck;//驗證狀態，0為未驗證，1為已驗證
	private Set<MsgVO> msgVOs; //連結通知明細VO
	private Set<TrackListingVO> trackListingVOs;//連結追蹤清單VO


	public String getMemberId() {
		return memberId;

	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public Set<MsgVO> getMsgVOs() {
		return msgVOs;
	}

	public void setMsgVOs(Set<MsgVO> msgVOs) {
		this.msgVOs = msgVOs;
	}

	public Set<TrackListingVO> getTrackListingVOs() {
		return trackListingVOs;
	}

	public void setTrackListingVOs(Set<TrackListingVO> trackListingVOs) {
		this.trackListingVOs = trackListingVOs;
	}

	public Integer getMemberCheck() {
		return memberCheck;
	}

	public void setMemberCheck(Integer memberCheck) {
		this.memberCheck = memberCheck;
	}

}

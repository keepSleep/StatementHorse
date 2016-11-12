package com.member.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;
import org.omg.CORBA_2_3.portable.InputStream;

import com.member.model.CheckMailService;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.member.model.PasswordEncorder;

//@WebServlet("/account/register")
public class RegisterServlet extends HttpServlet {
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/login/register.jsp";
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> msgOK = new HashMap<String, String>();
		HttpSession session1 = request.getSession();
		request.setAttribute("ErrorMsgKey", errorMsg);
		session.setAttribute("MsgOK", msgOK);
		String memberEmail = request.getParameter("email");
		String memberPassword = request.getParameter("password");
		String memberPassword2 = request.getParameter("password2");

		int experience = 0;
		long sizeInBytes = 0;
		InputStream is = null;

		if (memberEmail == null || memberEmail.trim().length() == 0) {
			errorMsg.put("registerError", "帳號欄位必須輸入");
		}
		if (memberPassword == null || memberPassword.trim().length() == 0) {
			errorMsg.put("registerError", "密碼欄位必須輸入");
		}
		if (memberPassword2 == null || memberPassword2.trim().length() == 0) {
			errorMsg.put("registerError", "密碼欄位必須輸入");
		}
		if (memberPassword != null && memberPassword2 != null && memberPassword.trim().length() > 0
				&& memberPassword2.trim().length() > 0) {
			if (!memberPassword.trim().equals(memberPassword2.trim())) {
				errorMsg.put("registerError", "密碼必須一致");
			}else if(memberPassword.length()<6){
				errorMsg.put("registerError", "密碼必須大於六個字");
			}else if(!memberPassword.matches("^.*[0-9]+.*$")||!memberPassword.matches("^.*[a-zA-Z]+.*$")){
				errorMsg.put("registerError", "密碼必須包含英文與數字");
			}
		}

		if (!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			return;
		}
		try {
			MemberService rs = new MemberService();
			if (rs.findMember(memberEmail) != null) {
				errorMsg.put("registerError", "該帳號已存在，請更換新帳號");
			} else {
				MemberVO mem = new MemberVO();
				String memberId = memberEmail.split("@")[0];
				mem.setMemberId(memberId);
				mem.setMemberEmail(memberEmail);
				mem.setMemberPassword(PasswordEncorder.encrypt(memberPassword));
				mem.setMemberCheck(0);
				mem.setCreatetime(new Date());
				rs.insertMember(mem);
				msgOK.put("InsertOK", "新增成功");
				CheckMailService checkMailService = new CheckMailService();
				checkMailService.sendCheckMail(memberEmail);
				RequestDispatcher rd = request.getRequestDispatcher("/login/registermail.jsp");
				rd.forward(request, response);
				return;
			}
			if (!errorMsg.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIDDup", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
	}
}

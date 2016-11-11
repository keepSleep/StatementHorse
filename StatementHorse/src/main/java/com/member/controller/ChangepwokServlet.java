package com.member.controller;

import java.io.IOException;
import java.util.Base64;
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

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/account/changepwok")
public class ChangepwokServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("ErrorMsgKey", errorMsg);
		String memberEmail = new String(Base64.getDecoder().decode(request.getParameter("confirmation_token")));
		String memberPassword = request.getParameter("password");
		String memberPassword2 = request.getParameter("password2");
		String path = "/login/changepw.jsp?confirmation_token="+request.getParameter("confirmation_token");
		
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
		MemberService rs = new MemberService();
		MemberVO mem = rs.findMember(memberEmail);
		if ((new Date().getTime() - mem.getCreatetime().getTime()) > 6 * 60 * 60 * 1000) {
			RequestDispatcher rd = request.getRequestDispatcher("/login/registermailagain.jsp");
			rd.forward(request, response);
			return;
		}
		mem.setMemberPassword(memberPassword);
		rs.insertMember(mem);
		RequestDispatcher rd = request.getRequestDispatcher("/message/Message.jsp");//是否要改成密碼修改完成頁面
		rd.forward(request, response);
		return;
	}
}
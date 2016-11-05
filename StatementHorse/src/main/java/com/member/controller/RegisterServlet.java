package com.member.controller;

import java.io.IOException;
import java.util.Collection;
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

@WebServlet("/account/register")
public class RegisterServlet extends HttpServlet {
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> MsgOK = new HashMap<String, String>();
		HttpSession session1 = request.getSession();
		request.setAttribute("MsgMap", "errorMsg");
		session.setAttribute("MsgOK", "msgOK");
		String memberEmail = "";
		String memberPassword = "";
		String memberPassword2 = "";

		int experience = 0;
		long sizeInBytes = 0;
		InputStream is = null;
		Collection<Part> parts = request.getParts();
		if (parts != null) {
			for (Part p : parts) {
				String findMemberEmail = p.getName();
				String value = request.getParameter(findMemberEmail);
				if (p.getContentType() == null) {
					if (findMemberEmail.equals("mid")) {
						memberEmail = value;
					} else if (findMemberEmail.equals("password")) {
						memberPassword = value;
					} else if (findMemberEmail.equalsIgnoreCase("password2")) {
						memberPassword2 = value;
					}
				}
			}
			if (memberEmail == null || memberEmail.trim().length() == 0) {
				errorMsg.put("errorIDEmpty", "帳號欄位必須輸入");
			}
			if (memberPassword == null || memberPassword.trim().length() == 0) {
				errorMsg.put("errorPasswordEmpty", "密碼欄位必須輸入");
			}
			if (memberPassword2 == null || memberPassword2.trim().length() == 0) {
				errorMsg.put("errorPassword2Empty", "密碼欄位必須輸入");
			}
			if (memberPassword.trim().length() > 0 && memberPassword2.trim().length() > 0){
				if(!memberPassword.trim().equals(memberPassword2.trim())){
					errorMsg.put("errorPassword2Empty", "密碼必須一致");
					errorMsg.put("errorPasswordEmpty", "*");
				}
			}if(!errorMsg.isEmpty()){
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request,response);
				return;
			}try{
				MemberService rs = new MemberService();
				if(rs.findMember(memberEmail)==null){
					errorMsg.put("errorIDDup", "該帳號已存在，請更換新帳號");
				}else{
					MemberVO mem = new MemberVO();
					mem.setMemberEmail(memberEmail);
					mem.setMemberPassword(memberPassword);
					rs.insertMember(mem);
					MsgOK.put("InsertOK","新增成功");
					response.sendRedirect(".../registermail.jsp");
					return;
				}if(!errorMsg.isEmpty()){
					RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
					rd.forward(request,response);
					return;
				}
			}catch(Exception e){
				e.printStackTrace();
				errorMsg.put("errorIDDup", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request,response);
			}
		}
	}
}

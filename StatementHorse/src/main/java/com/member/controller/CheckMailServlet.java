package com.member.controller;

import java.io.IOException;
import java.util.Base64;
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

@WebServlet("/account/registermailok")
public class CheckMailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String memberEmail = new String(Base64.getDecoder().decode(request.getParameter("confirmation_token")));
		MemberService rs = new MemberService();
		MemberVO mem = rs.findMember(memberEmail);
		mem.setMemberCheck(1);
		rs.insertMember(mem);
		RequestDispatcher rd = request.getRequestDispatcher("/login/registermailok.jsp");
		rd.forward(request, response);
		return;
	}
}
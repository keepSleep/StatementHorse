package com.member.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.CheckMailService;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/account/changepw")
public class ChangepwServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String memberEmail = request.getParameter("email");
		
		request.setCharacterEncoding("UTF-8");
		CheckMailService changepw = new CheckMailService();
		changepw.sendChangepw(memberEmail);
		RequestDispatcher rd = request.getRequestDispatcher("/login/forgetpwcheck.jsp");
		rd.forward(request, response);
		return;
	}
}
package com.message.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberHibernateDAO;
import com.member.model.MemberVO;


@WebServlet("/ChangeCreateTime")
public class ChangeCreateTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MemberHibernateDAO memberdao = new MemberHibernateDAO();
		HttpSession session = request.getSession();
		MemberVO membervo = (MemberVO)session.getAttribute("user");
		MemberVO membervo1= new MemberVO();
		membervo1.setMemberCheck(membervo.getMemberCheck());
		membervo1.setMemberId(membervo.getMemberId());
		membervo1.setMemberPassword(membervo.getMemberPassword());
		membervo1.setCreatetime(new Timestamp(System.currentTimeMillis()));
		membervo1.setMemberEmail(membervo.getMemberEmail());
		memberdao.update(membervo1);
		session.setAttribute("user", membervo1);
		
		response.sendRedirect("GetTrackListing");
		
//		RequestDispatcher failureView = request.getRequestDispatcher("/GetTrackListing");
//		failureView.forward(request, response);
	}

}

package com.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;
//@WebServlet("/changepassword/ChangePassword")

public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> correctMsg = new HashMap<String, String>();
		try {
			request.setCharacterEncoding("UTF-8");
			response.setHeader("content-type", "text/html;charset=UTF-8");
			HttpSession session = request.getSession();
			MemberService memberdao = new MemberService();
			request.setAttribute("ErrorMsg", errorMsg);
			session.setAttribute("CorrectMsg", correctMsg);

			// 取得會員id
			String member_id = (String) session.getAttribute("member_id");

			// 取得舊密碼
			String oldpassword = request.getParameter("oldpassword");

			String newpassword1 = request.getParameter("newpassword1");
			if (newpassword1.trim() == "") {
				errorMsg.put("newpassword1null", "新密碼不得為空白");
			}
			String newpassword2 = request.getParameter("newpassword2");
			if (newpassword2.trim() == "") {
				errorMsg.put("newpassword2null", "再次輸入的密碼不得為空白");
			}
			MemberVO membervo = memberdao.findByMemberid(member_id, oldpassword.trim());

			System.out.println(newpassword1);
			System.out.println(newpassword2);
			if (membervo != null) {
				if (newpassword1.trim() != "" && newpassword2.trim() != "" && newpassword1.equals(newpassword2)) {
					memberdao.updateMember(membervo, newpassword1);
					correctMsg.put("correctMsg", "更新成功");
				} else {
					if (newpassword1.trim() == "") {
						errorMsg.put("newpassword1null", "新密碼不得為空白");
					} else if (!newpassword1.equals(newpassword2)) {
						errorMsg.put("errornewMsg", "兩次密碼必須一致");
					}
					if (newpassword2.trim() == "") {
						errorMsg.put("newpassword2null", "再次輸入的密碼不得為空白");
					} else if (!newpassword1.equals(newpassword2)) {
						errorMsg.put("errornewMsg", "兩次密碼必須一致");
					}
				}
			} else {
				if (oldpassword.trim() == "") {
					errorMsg.put("oldpassword", "密碼不得為空白");
				} else {
					if (oldpassword.trim() == "") {
						errorMsg.put("oldpassword", "密碼不得為空白");
					} else {
						errorMsg.put("erroroldMsg", "您的密碼輸入錯誤");
					}
				}
			}
			if (!errorMsg.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("changepassword.jsp");
				failureView.forward(request, response);
			} else {
				response.sendRedirect("CorrectPage.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIDDup", e.getMessage());
		}
		// RequestDispatcher failureView =
		// request.getRequestDispatcher("CorrectPage.jsp");
		// failureView.forward(request, response);
		// response.sendRedirect("CorrectPage.jsp");

	}

}

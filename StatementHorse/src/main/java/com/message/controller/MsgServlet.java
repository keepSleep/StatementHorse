package com.message.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;

import com.member.model.MemberVO;
import com.message.model.MsgService;

//@WebServlet("/MsgServlet")
public class MsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		List<List<Object>> member = new LinkedList<>();
		MsgService msgservicedao = new MsgService();
		PrintWriter out = resp.getWriter();
		// 傳入追蹤者的id為何
		
		MemberVO membervo = (MemberVO) session.getAttribute("user");
		String member_id = membervo.getMemberId();


		//傳入追蹤者的登入時間
//		session.setAttribute("logintime",new Date(System.currentTimeMillis()));
		// System.out.println(member_id);
		// 取得使用者的追縱股號有哪些
		List<Integer> memberstockno = msgservicedao.findStockNo(member_id);
		session.setAttribute("memberstockno", memberstockno);
		String action = (String) req.getAttribute("action");

		// 利用member_id(會員)和memberstockno(股號)來搜尋
		for (Integer stock_no : memberstockno) {
			List<Object> memberstock_list_no = new LinkedList<>();
			List<Integer> memberlistno = msgservicedao.findByKey(member_id, stock_no);
			memberstock_list_no.add(0, stock_no);
			memberstock_list_no.add(1, "false");
			memberstock_list_no.add(2, "false");
			try {
				for (int listno : memberlistno) {

					if (listno == 1) {
						memberstock_list_no.set(1, "true");
					}
					if (listno == 2) {
						memberstock_list_no.set(2, "true");
					}

				}
				member.add(memberstock_list_no);
			} catch (NullPointerException e) {
				continue;
			}
		}
		session.setAttribute("memberlistno", member);
		// 轉移畫面
//		if ("jquery_check".equals(action)) {
 
		out.print(member);
//		} else {
//			RequestDispatcher failureView = req.getRequestDispatcher("message/Message.jsp");
//			failureView.forward(req, resp);
//		}
	}
}

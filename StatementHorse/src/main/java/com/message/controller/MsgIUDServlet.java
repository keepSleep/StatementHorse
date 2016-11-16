package com.message.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;
import com.message.model.MsgService;
import com.message.model.MsgVO;
import com.messagelists.model.MsgListVO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class MsgIUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		
		MemberVO membervo = (MemberVO) session.getAttribute("user");
		String member_id = membervo.getMemberId();
		
		
		MsgService msgservicedao = new MsgService();

		if ("jquery_check".equals(action)) {
			String stocknos = request.getParameter("stockcheck1");
//			System.out.println(stocknos);
			for(String stocklist:stocknos.split(";")){
				String[] stockparts=stocklist.split(",");
				String stock = stockparts[0];
				String check1=stockparts[1];
				String check2=stockparts[2];

				System.out.println(stock+","+check1+","+check2);
				
				List<Integer> list = msgservicedao.findByKey(member_id, new Integer(stock));
				System.out.println(list);
				// 取得使用者勾選的項目1和2

				if (list.size() == 2) {
					if ("true".equals(check1) && list.get(0) != 1) {

						msgservicedao.insert(member_id, new Integer(stock), 1);

					} else if ("false".equals(check1) && list.get(0) == 1) {

						msgservicedao.delete(member_id, new Integer(stock), 1);

					}
					if ("true".equals(check2) && list.get(1) != 2) {

						msgservicedao.insert(member_id, new Integer(stock), 2);

					} else if ("false".equals(check2) && list.get(1) == 2) {

						msgservicedao.delete(member_id, new Integer(stock), 2);

					}
				} else if (list.size() == 1) {
					if ("true".equals(check1) && list.get(0) != 1) {

						msgservicedao.insert(member_id, new Integer(stock), 1);

					} else if ("false".equals(check1) && list.get(0) == 1) {

						msgservicedao.delete(member_id, new Integer(stock), 1);

					}
					if ("true".equals(check2) && list.get(0) != 2) {

						msgservicedao.insert(member_id, new Integer(stock), 2);

					} else if ("false".equals(check2) && list.get(0) == 2) {

						msgservicedao.delete(member_id, new Integer(stock), 2);
					}

				} else {
					if ("true".equals(check1)) {

						msgservicedao.insert(member_id, new Integer(stock), 1);

					} else {

						msgservicedao.delete(member_id, new Integer(stock), 1);

					}
					if ("true".equals(check2)) {

						msgservicedao.insert(member_id, new Integer(stock), 2);

					} else {

						msgservicedao.delete(member_id, new Integer(stock), 2);
					}
				}

			}

//			out.println(forjsonlist);

		}
		if ("checkbox_update".equals(action)) {
			List<List<Object>> allstock = (List<List<Object>>) session.getAttribute("memberlistno");
			// 每筆股號的數字
			for (List<Object> stockno : allstock) {

				// 檢查資料庫是否已經有這筆資料
				List<Integer> list = msgservicedao.findByKey(member_id, (Integer) stockno.get(0));

				// 取得使用者勾選的項目1和2
				String check1 = request.getParameter(stockno.get(0) + "1");
				String check2 = request.getParameter(stockno.get(0) + "2");

				if (list.size() == 2) {
					if ("on".equals(check1) && list.get(0) != 1) {

						msgservicedao.insert(member_id, (Integer) stockno.get(0), 1);

					} else if (check1 == null && list.get(0) == 1) {

						msgservicedao.delete(member_id, (Integer) stockno.get(0), 1);

					}
					if ("on".equals(check2) && list.get(1) != 2) {

						msgservicedao.insert(member_id, (Integer) stockno.get(0), 2);

					} else if (check2 == null && list.get(1) == 2) {

						msgservicedao.delete(member_id, (Integer) stockno.get(0), 2);

					}
				} else if (list.size() == 1) {
					if ("on".equals(check1) && list.get(0) != 1) {

						msgservicedao.insert(member_id, (Integer) stockno.get(0), 1);

					} else if (check1 == null && list.get(0) == 1) {

						msgservicedao.delete(member_id, (Integer) stockno.get(0), 1);

					}
					if ("on".equals(check2) && list.get(0) != 2) {

						msgservicedao.insert(member_id, (Integer) stockno.get(0), 2);

					} else if (check2 == null && list.get(0) == 2) {

						msgservicedao.delete(member_id, (Integer) stockno.get(0), 2);
					}

				} else {
					if ("on".equals(check1)) {

						msgservicedao.insert(member_id, (Integer) stockno.get(0), 1);

					} else {

						msgservicedao.delete(member_id, (Integer) stockno.get(0), 1);

					}
					if ("on".equals(check2)) {

						msgservicedao.insert(member_id, (Integer) stockno.get(0), 2);

					} else {

						msgservicedao.delete(member_id, (Integer) stockno.get(0), 2);
					}
				}

			}

			// System.out.println(member_id+","+stock);


			RequestDispatcher failureView = request.getRequestDispatcher("/MsgServlet");
			failureView.forward(request, response);
		}
	}

}

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
		String member_id = (String) session.getAttribute("member_id");
		MsgService msgservicedao = new MsgService();

		if ("jquery_check".equals(action)) {
			String stocknos = request.getParameter("stockcheck1");
			
			 
			
			String[] stockcheckbox = stocknos.split(";");
			for (int i = 0; i < stockcheckbox.length; i++) {
				String[] eachstockno = stockcheckbox[i].split(",");
				String stockno = eachstockno[0];
				List<Integer> list = msgservicedao.findByKey(member_id, new Integer(stockno));
				String check1 = eachstockno[1];
				String check2 = eachstockno[2];
				
				if (list.size() == 2) {
					if ("true".equals(check1) && list.get(0) != 1) {

						msgservicedao.insert(member_id, new Integer(stockno), 1);

					} else if ("false".equals(check1) && list.get(0) == 1) {

						msgservicedao.delete(member_id, new Integer(stockno), 1);

					}
					if ("true".equals(check2) && list.get(1) != 2) {

						msgservicedao.insert(member_id, new Integer(stockno), 2);

					} else if ("false".equals(check2)  && list.get(1) == 2) {

						msgservicedao.delete(member_id, new Integer(stockno), 2);

					}
				} else if (list.size() == 1) {
					if ("true".equals(check1) && list.get(0) != 1) {

						msgservicedao.insert(member_id, new Integer(stockno), 1);

					} else if ("false".equals(check1)  && list.get(0) == 1) {

						msgservicedao.delete(member_id, new Integer(stockno), 1);

					}
					if ("true".equals(check2) && list.get(0) != 2) {

						msgservicedao.insert(member_id, new Integer(stockno), 2);

					} else if ("false".equals(check2)  && list.get(0) == 2) {

						msgservicedao.delete(member_id, new Integer(stockno), 2);
					}

				} else {
					if ("true".equals(check1)) {

						msgservicedao.insert(member_id, new Integer(stockno), 1);

					} else {

						msgservicedao.delete(member_id, new Integer(stockno), 1);

					}
					if ("true".equals(check2)) {

						msgservicedao.insert(member_id, new Integer(stockno), 2);

					} else {

						msgservicedao.delete(member_id, new Integer(stockno), 2);
					}
				}
				System.out.println(stocknos+"---------------------"+stockno+"-----------------"+check1+","+check2);
			}
//			out.println(forjsonlist);
			RequestDispatcher failureView = request.getRequestDispatcher("/MsgServlet");
			failureView.forward(request, response);
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
			session.setAttribute("member_id", member_id);

			RequestDispatcher failureView = request.getRequestDispatcher("/MsgServlet");
			failureView.forward(request, response);
		}
	}

}

package com.message.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.financialstatements.model.FinancialStatementsService;
import com.financialstatements.model.FinancialStatementsVO;
import com.member.model.MemberVO;
import com.message.model.MsgService;

import com.mgr.model.MGRService;
import com.mgr.model.MGRVO;

public class GetNewsMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		MsgService msgservicedao = new MsgService();
		FinancialStatementsService fsdao = new FinancialStatementsService();
		MGRService mgdao = new MGRService();
		try {
			// 傳入追蹤者的id為何
			MemberVO membervo = (MemberVO) session.getAttribute("user");
			String member_id = membervo.getMemberId();

			// System.out.println(member_id);
			// 傳入追蹤者的登入時間
			java.sql.Timestamp logintime = (java.sql.Timestamp) membervo.getCreatetime();
			// System.out.println(logintime);
			// 取得會員追蹤的股號
			List<Integer> memberstockno = msgservicedao.findStockNo(member_id);

			// 取得會員新的通知訊息及排序
			List<String> newmessage = new LinkedList<>();
			for (Integer stockno : memberstockno) {
				System.out.println(stockno);
				List<FinancialStatementsVO> fsmessage = null;
				List<MGRVO> mgmessage = null;
				List<Integer> list = msgservicedao.findByKey(member_id, stockno);
				if (list.size() != 0) {
					for (Integer listno : list) {
						if (listno == 2) {
							fsmessage = fsdao.checkinsert(stockno, logintime);
						}
						if (listno == 1) {
							mgmessage = mgdao.checkinsert(stockno, logintime);
						}
					}
				}
				if (fsmessage != null) {
					for (FinancialStatementsVO FinancialStatementsVO : fsmessage) {
						newmessage.add(FinancialStatementsVO.getStockNo().toString() + "於"
								+ FinancialStatementsVO.getPostDate() + " " + FinancialStatementsVO.getPostTime() + "發佈"
								+ FinancialStatementsVO.getStatementDate().substring(0, 3) + "年第"
								+ FinancialStatementsVO.getStatementDate().substring(4) + "季的財報更新");
					}
				}
				if (mgmessage != null) {
					for (MGRVO mrgvo : mgmessage) {
						newmessage.add(mrgvo.getStockVO().getStockNo() + "於" + mrgvo.getPostDate() + "發佈"
								+ mrgvo.getRevenueDate().substring(0, 4) + "年第" + mrgvo.getRevenueDate().substring(5)
								+ "月營收");
					}
				}
			}
			List<String> newsmessage = orderByDate(newmessage);

			JSONArray json = new JSONArray(newsmessage);
			out.print(json);
		} catch (NullPointerException E) {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 日期的排序
	public List<String> orderByDate(List<String> list) {
		List<String> list1 = new LinkedList<>();
		Date[] empty1 = new Date[list.size()];
		String[] empty2 = new String[list.size()];
		Date[] time = new Date[list.size()];
		String[] String1 = new String[list.size()];
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		int k = 0;
		for (String data : list) {
			int i = data.indexOf("於");
			try {
				java.util.Date date = dateFormat.parse(data.substring(i + 1, i + 1 + 10));
				time[k] = date;
			} catch (ParseException e) {
				continue;
			}
			String1[k] = data;

			// System.out.println(data.substring(i+1, i+1 + 10));
			// System.out.println(time[k]);
			// System.out.println(String1[k]);
			k++;
			// System.out.println(k);
		}

		for (int i = 0; i < time.length; i++) {
			for (int j = 1; j < time.length - i; j++) {
				// System.out.println(time[i].before(time[i + j]));
				if (time[i].before(time[i + j])) {
					empty1[i] = time[i];
					time[i] = time[i + j];
					time[i + j] = empty1[i];

					empty2[i] = String1[i];
					String1[i] = String1[i + j];
					String1[i + j] = empty2[i];

				}
			}
		}
		;

		for (int i = 0; i < time.length; i++) {
			// System.out.println(String1[i]);
			list1.add(String1[i]);
		}
		;

		return list1;
	}
}

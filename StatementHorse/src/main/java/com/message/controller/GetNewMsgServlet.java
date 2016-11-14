package com.message.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialstatements.model.FinancialStatementsDAO;
import com.financialstatements.model.FinancialStatementsVO;
import com.member.model.MemberVO;
import com.message.model.MsgService;
import com.mgr.model.MGRDAO;
import com.mgr.model.MGRVO;

//@WebServlet("NewsServlet")
public class GetNewMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MsgService msgservicedao = new MsgService();
		FinancialStatementsDAO fsdao = new FinancialStatementsDAO();
		MGRDAO mgdao = new MGRDAO();

		// 傳入追蹤者的id為何
		MemberVO membervo = (MemberVO) session.getAttribute("user");
		String member_id = membervo.getMemberId();
		
		// 取得會員追蹤的股號
		List<Integer> memberstockno = msgservicedao.findStockNo(member_id);
		for (Integer stockno : memberstockno) {

			List<FinancialStatementsVO> fsmessage = fsdao.findByStockNo(stockno);
			List<FinancialStatementsVO> fsTwoMessage = new LinkedList<>();
			for (int i = 0; i < 2; i++) {
				fsTwoMessage.add(fsmessage.get(i));
			}
			List<MGRVO> mgmessage = mgdao.findByStockNo(stockno);
			List<MGRVO> mgTwoMessage = new LinkedList<>();
			for (int i = 0; i < 2; i++) {
				mgTwoMessage.add(mgmessage.get(i));
			}
			
			// 判斷mg和fs的日期順序
			request.removeAttribute("firstmessagefs");
			request.removeAttribute("secondmessagefs");
			request.removeAttribute("firstmessagemg");
			request.removeAttribute("secondmessagemg");
			if (fsTwoMessage.get(0).getPostDate().after(mgTwoMessage.get(1).getPostDate())) {
				if (fsTwoMessage.get(0).getPostDate().after(mgTwoMessage.get(0).getPostDate())) {
					if (fsTwoMessage.get(1).getPostDate().after(mgTwoMessage.get(0).getPostDate())) {
						request.setAttribute("firstmessagefs", fsTwoMessage.get(0));
						request.setAttribute("secondmessagefs", fsTwoMessage.get(1));
					} else {
						request.setAttribute("firstmessagefs", fsTwoMessage.get(0));
						request.setAttribute("secondmessagemg", mgTwoMessage.get(0));
					}
				} else {
					request.setAttribute("firstmessagemg", mgTwoMessage.get(0));
					request.setAttribute("secondmessagefs", fsTwoMessage.get(0));
				}
			} else {
					request.setAttribute("firstmessagemg", mgTwoMessage.get(0));
					request.setAttribute("secondmessagemg", mgTwoMessage.get(1));
			}

		}

		RequestDispatcher failureView = request.getRequestDispatcher("message/GetMessage.jsp");
		failureView.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

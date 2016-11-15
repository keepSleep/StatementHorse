package com.tracklisting.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;
import com.tracklisting.model.TrackListingHibernateDAO;
import com.tracklisting.model.TrackListingVO;

@WebServlet("/DeleteListing")
public class DeleteListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DeleteListing() {
    	
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//session
		HttpSession session = request.getSession();
		MemberVO membervo = (MemberVO) session.getAttribute("user");
		String member_id = membervo.getMemberId();

		String listingNo = request.getParameter("listingNo");
//		String memberId = request.getParameter("memberId");

		TrackListingHibernateDAO tldao = new TrackListingHibernateDAO();
		
		List<TrackListingVO> tlVO = tldao.getAllByMember(member_id);
		
		if(tlVO.size() == 1){
			
			response.getWriter().write("至少要有 1 個追蹤清單喔!!");
			
		}
		
		else{
		
		tldao.delete(Integer.parseInt(listingNo));
		
		response.getWriter().write("刪除追蹤清單成功");
		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

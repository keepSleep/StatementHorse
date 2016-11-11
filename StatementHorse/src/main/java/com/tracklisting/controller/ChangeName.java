package com.tracklisting.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberVO;
import com.tracklisting.model.TrackListingHibernateDAO;
import com.tracklisting.model.TrackListingVO;

@WebServlet("/ChangeName")
public class ChangeName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ChangeName() {
    	
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String listingName = request.getParameter("listingName");
		String memberId = request.getParameter("memberId");
		String listingNo = request.getParameter("listingNo");

		TrackListingHibernateDAO tldao = new TrackListingHibernateDAO();
		TrackListingVO tlvo = new TrackListingVO();
		
		if(listingName.trim().length() <= 0 || listingName.trim().length() > 16){
			
			response.getWriter().write("清單名稱不得空白或超過 16 個字!!");
			
		}
		
		else{
		
			MemberVO mvo = new MemberVO();
			mvo.setMemberId(memberId);
			
			tlvo.setListingNo(Integer.parseInt(listingNo));
			tlvo.setMemberVO(mvo);;
			tlvo.setListingName(listingName);
			tldao.update(tlvo);
		
			response.getWriter().write("修改追蹤清單名稱成功");
		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

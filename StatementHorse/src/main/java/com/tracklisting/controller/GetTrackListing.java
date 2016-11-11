package com.tracklisting.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.listingdetails.model.ListingDetailsHibernateDAO;
import com.listingdetails.model.ListingDetailsVO;
import com.member.model.MemberVO;
import com.tracklisting.model.TrackListingHibernateDAO;
import com.tracklisting.model.TrackListingVO;

@WebServlet("/GetTrackListing")
public class GetTrackListing extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetTrackListing() {
    	
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("id");
		TrackListingHibernateDAO tldao = new TrackListingHibernateDAO();
		
		List<TrackListingVO> tlVO = tldao.getAllByMember(memberId);
		
		if (tlVO.size() == 0){

			TrackListingVO tlvo = new TrackListingVO();
			MemberVO mvo = new MemberVO();
			mvo.setMemberId(memberId);
			
			tlvo.setMemberVO(mvo);
			tlvo.setListingName("我的追蹤清單");
			tldao.insert(tlvo);
			
			request.getRequestDispatcher("memberhome.jsp")
			.forward(request, response);
			
		}
		
		else{
		
		ArrayList<String> mId = new ArrayList<String>();
		ArrayList<String> tlName = new ArrayList<String>();
		ArrayList<Integer> tlNo = new ArrayList<Integer>();
		
		for (TrackListingVO tlVO2 : tlVO) {
			
			mId.add(tlVO2.getMemberVO().getMemberId());
			tlName.add(tlVO2.getListingName());
			tlNo.add(tlVO2.getListingNo());
							
			}	
			
		request.setAttribute("mId", mId);
		request.setAttribute("tlName", tlName);
		request.setAttribute("tlNo", tlNo);
		request.setAttribute("tlSize", tlNo.size()-1);
		
		request.getRequestDispatcher("memberhome.jsp")
		.forward(request, response);
		
		}
		
	}		

}

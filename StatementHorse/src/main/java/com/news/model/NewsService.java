package com.news.model;

import java.util.Date;
import java.util.List;

public class NewsService {
	NewsDAOinterface dao;
	
	public  NewsService(){
		dao=new NewsHibernateDAO();
	}
	
	public void insert(Date newsDate,String newsTitle,String newsWebaddress){
		NewsVO newsVO=new NewsVO();
		newsVO.setNewsDate(newsDate);
		newsVO.setNewsTitle(newsTitle);
		newsVO.setNewsWebaddress(newsWebaddress);
		dao.insert(newsVO);
	}
	
	public boolean checktitleexist(String title){
		List<NewsVO> list = dao.getOneByTitle(title);
		if(list.isEmpty()){
			return true;
		}else{
			return false;
		}
		
	}

}

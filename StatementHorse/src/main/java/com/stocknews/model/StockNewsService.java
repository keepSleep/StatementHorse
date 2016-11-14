package com.stocknews.model;

import java.util.List;

import com.news.model.NewsDAOinterface;
import com.news.model.NewsHibernateDAO;
import com.news.model.NewsService;
import com.news.model.NewsVO;
import com.stock.model.StockVO;

public class StockNewsService {
	StockNewsDAOinterface dao;
	NewsDAOinterface newsservice;
	public  StockNewsService(){
		dao=new StockNewsHibernateDAO();
		newsservice=new NewsHibernateDAO();
	}
	
	public void insert(String title,int stockno){
	    List<NewsVO> list = newsservice.getOneByTitle(title);
	    StockNewsVO stocknewsvo = new StockNewsVO();
	    StockVO stockvo=new StockVO();
	    NewsVO newsvo = new NewsVO();
	    
	    newsvo.setNewsId(list.get(0).getNewsId());
	    stockvo.setStockNo(stockno);
	    
	    stocknewsvo.setStockVO(stockvo);
	    stocknewsvo.setNewsVO(newsvo);
		dao.insert(stocknewsvo);
	}
}

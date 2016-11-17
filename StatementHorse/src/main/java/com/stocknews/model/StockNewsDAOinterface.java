package com.stocknews.model;

import java.util.*;

import com.stocknews.model.StockNewsVO;

public interface StockNewsDAOinterface {
	      public void insert(StockNewsVO stock_News_VO);
          public void update(StockNewsVO stock_News_VO);
          public void delete(Integer stockno ,Integer newsid);
          public StockNewsVO findByPrimaryKey(Integer stockno);
	      public List<StockNewsVO> getAll();
	      public List<StockNewsVO> getAllByStockNo(Integer stockno);
	      public List<Object[]> getByStockNoByShao(ArrayList stockNoArrayList);
}

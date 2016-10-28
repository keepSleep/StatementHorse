package com.news.model;

import java.util.*;

public interface NewsDAOinterface {
          public void insert(NewsVO newsVO);
          public void update(NewsVO newsVO);
          public void delete(Integer newsId);
          public NewsVO findByPrimaryKey(Integer newsId);
          public List<NewsVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}

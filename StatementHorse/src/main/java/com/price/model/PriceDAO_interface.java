package com.price.model;

import java.util.*;
import com.stock.model.StockVO;



public interface PriceDAO_interface {
	public void insert(PriceVO priceVO);
	public void update(PriceVO priceVO);
	public void delete(PriceVO priceVO);
	public void deleteByStockNo(Integer stockNo);
	public PriceVO findByPrimaryKey(PriceVO priceVO);
	public List<PriceVO> getAll();
	public List<PriceVO> getByStockNo(Integer stockNo);
	public List getStoredProcedure(Integer stockNo);
}

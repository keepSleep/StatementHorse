package com.price.model;

import java.util.*;



public interface PriceDAO_interface {
	public void insert(PriceVO priceVO);
	public void update(PriceVO priceVO);
	public void delete(PriceVO priceVO);
	public PriceVO findByPrimaryKey(PriceVO priceVO);
	public List<PriceVO> getAll();
}

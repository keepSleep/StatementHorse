package com.listingdetails.model;

import java.util.List;

public interface ListingDetails_Interface {
	
	  public void insert(ListingDetailsVO listingDetailsVO);
      public void delete(Integer listingNo,Integer stockNo);
      public List<ListingDetailsVO> getAllByListing(Integer listingNo); 

}
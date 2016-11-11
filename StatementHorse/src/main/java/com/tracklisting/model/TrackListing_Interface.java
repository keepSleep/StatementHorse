package com.tracklisting.model;

import java.util.List;

public interface TrackListing_Interface {
	
	  public void insert(TrackListingVO trackListingVO);
      public void update(TrackListingVO trackListingVO);
      public void delete(Integer listingNo);
      public TrackListingVO findByPrimaryKey(Integer listingNo);
      public List<TrackListingVO> getAllByMember(String memberId); 

}
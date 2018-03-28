package com.revature.dao;

import java.util.List;

import com.revature.pojo.GradingFormat;

public interface GradingFormatDao {
	public List<GradingFormat> retrieveAllGradingFormats();
	
	public GradingFormat retreiveFormatByName(String name);
}

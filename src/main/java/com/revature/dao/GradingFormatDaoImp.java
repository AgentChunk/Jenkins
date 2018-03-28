package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.GradingFormat;
import com.revature.util.ConnectionFactory;
import com.revature.util.LoggingUtil;

public class GradingFormatDaoImp implements GradingFormatDao {

	public List<GradingFormat> retrieveAllGradingFormats() {
		List<GradingFormat> list = new ArrayList<GradingFormat>();
		String sql = "SELECT * FROM GRADING_FORMAT";
		
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				GradingFormat gf = new GradingFormat();
				gf.setgId(rs.getInt(1));
				gf.setFormat(rs.getString(2));
				gf.setPresentation(rs.getInt(3));
				gf.setCutoff(rs.getDouble(4));
				list.add(gf);
			}
			LoggingUtil.logInfo("Retrieved all grading formats");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	//used to retrieve the grading format of with a given format name
	public GradingFormat retreiveFormatByName(String name) {
		GradingFormat gf = new GradingFormat();
		String sql = "SELECT * FROM GRADING_FORMAT WHERE FORMAT =?";
		
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				gf.setgId(rs.getInt(1));
				gf.setFormat(rs.getString(2));
				gf.setPresentation(rs.getInt(3));
				gf.setCutoff(rs.getDouble(4));
			}
			LoggingUtil.logInfo("Retrieved grading format: "+name);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return gf;
	}
	
	

}

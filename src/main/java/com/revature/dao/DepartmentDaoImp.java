package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.ConnectionFactory;

public class DepartmentDaoImp implements DepartmentDao {

	public int retrieveDepartmentHeadId(int depId) {
		int headId=0;
		String sql= "SELECT HEAD_ID FROM DEPARTMENT WHERE D_ID = ?";
		
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, depId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				headId=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return headId;
	}

}

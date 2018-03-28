package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import com.revature.pojo.Employee;
import com.revature.util.ConnectionFactory;
import com.revature.util.LoggingUtil;

public class EmployeeDaoImp implements EmployeeDao {

	public Employee retreiveEmployeeByUsername(String user) {
		String sql = "SELECT E.E_ID, E.FIRST_NAME, E.LAST_NAME, E.USERNAME, E.E_PASS, P_NAME, D_NAME, E.REIMBURSEMENT_LEFT, E.REPORTSTO, E.D_ID FROM EMPLOYEE E JOIN " + 
				"POSITION ON E.P_ID = POSITION.P_ID JOIN DEPARTMENT ON E.D_ID =DEPARTMENT.D_ID WHERE E.USERNAME = ?";
		Employee emp = new Employee();
		
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			
			ps.setString(1, user);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				emp.setId(rs.getInt(1));
				emp.setFirstName(rs.getString(2));
				emp.setLastName(rs.getString(3));
				emp.setUsername(rs.getString(4));
				emp.setPassword(rs.getString(5));
				emp.setPosition(rs.getString(6));
				emp.setDepartment(rs.getString(7));
				emp.setReimbursmentLeft(rs.getDouble(8));
				emp.setReportsTo(rs.getInt(9));
				emp.setDepId(rs.getInt(10));
				
			}
			LoggingUtil.logInfo(emp.getUsername()+ "retreived");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emp;
	}
	
	public void retrieveEmployeeUsernameById(int id) {
		
	}

	public void createEmployeePrpStmt(Employee user) {
		//First we need to get the position id and department id of the given position and
		int dId=0;
		int pId=0;
		PreparedStatement ps;
		Connection conn = null;
		Savepoint s = null;
		String sql ="SELECT D_ID FROM DEPARTMENT WHERE D_NAME =?";
		//first get D_ID
		try {
			ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			
			ps.setString(1,user.getDepartment());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				dId=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//now get P_ID
		sql = "SELECT P_ID FROM POSITION WHERE P_NAME =?";
		
		try {
			ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1,user.getPosition());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				pId=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Now use a prepared statement to create the new employee
		sql = "INSERT INTO EMPLOYEE VALUES (1,?,?,?,?,?,?,?,?)";
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			s = conn.setSavepoint("updateSavepoint");
			
			ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setDouble(2, user.getReimbursmentLeft());
			ps.setInt(3,user.getReportsTo());
			ps.setInt(4,pId);
			ps.setInt(5, dId);
			ps.setString(6, user.getUsername());
			ps.setString(7, user.getPassword());
			ps.setString(8, user.getLastName());
			
			
			ps.executeUpdate();
			LoggingUtil.logTrace("user" + user.getUsername() + "created");
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	

}

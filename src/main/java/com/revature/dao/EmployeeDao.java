package com.revature.dao;

import com.revature.pojo.Employee;

public interface EmployeeDao {

	public Employee retreiveEmployeeByUsername(String user);
	
	public void createEmployeePrpStmt(Employee user);
}

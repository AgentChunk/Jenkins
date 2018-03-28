package com.revature.dao;

import java.util.List;

import com.revature.pojo.Employee;
import com.revature.pojo.ReimbursementForm;

public interface ReimbursementDao {
	
	public List<ReimbursementForm> retreiveAllFormsOfEmployee(Employee e);
	
	public void createRFormPrpStmt(ReimbursementForm r);
	
	public void updateRForm(ReimbursementForm r);
	
	
}

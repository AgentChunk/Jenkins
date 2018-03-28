package com.revature.tests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.EmployeeDaoImp;
import com.revature.dao.ReimbursementDaoImp;
import com.revature.pojo.Employee;
import com.revature.pojo.ReimbursementForm;

public class ReimbursementDaoImpTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreation() {
		@SuppressWarnings("deprecation")
		long time = (long) 1.522e12;
		Date date = new Date(time);
		long time2 = (long) 1.524e12;
		Timestamp date2 = new Timestamp(time2);
		ReimbursementDaoImp dao = new ReimbursementDaoImp();
		ReimbursementForm rf = new ReimbursementForm(1, "TestClass", date2, 500,"OSU", 1, "University Course",
				date, 0, 2, false, 1, false,
				1, false, 0);
		
		dao.createRFormPrpStmt(rf);
	}
	
//	@Test
//	public void testGetRFFromEmployee() {
//		EmployeeDaoImp empDao = new EmployeeDaoImp();
//		ReimbursementDaoImp rfDao = new ReimbursementDaoImp();
//		Employee emp = empDao.retreiveEmployeeByUsername("ZackMan");
//		List<ReimbursementForm> rfList = rfDao.retreiveAllFormsOfEmployee(emp);
//		for(ReimbursementForm rf:rfList) {
//			System.out.println(rf.toString());
//		}
//		rfList.get(0).setSupApproval(true);
//		rfList.get(0).setdHeadApproval(true);
//		rfList.get(0).setBcoApproval(true);
//		rfList.get(0).setApprovedAmount(500*0.8);
//		rfList.get(0).setGrade(88);
//		rfList.get(0).setReason("Update test");
//		rfDao.updateRForm(rfList.get(0));
//	}
}

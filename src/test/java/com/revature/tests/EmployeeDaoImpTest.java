package com.revature.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.pojo.Employee;
import com.revature.util.LoggingUtil;

public class EmployeeDaoImpTest {

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

//	@Test
//	public void test() {
//		Employee emp = new Employee("HeresJohnny","password","John","Doe",1,"Sales personel","Sales",1000);
//		EmployeeDao dao = new EmployeeDaoImp();
//		dao.createEmployeePrpStmt(emp);
//	}
	
	@Test
	public void testRetreiveEmployee() {
		EmployeeDao dao = new EmployeeDaoImp();
		Employee user = dao.retreiveEmployeeByUsername("Jack01");
		LoggingUtil.logDebug(user.toString());
	}

}

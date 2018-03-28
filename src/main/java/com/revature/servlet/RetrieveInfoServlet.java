package com.revature.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDaoImp;
import com.revature.dao.ReimbursementDaoImp;
import com.revature.pojo.Employee;
import com.revature.pojo.ReimbursementForm;
import com.revature.util.LoggingUtil;

public class RetrieveInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1109537107176846851L;

	//This sends all of the reimbursement forms of a given employee to the front end in JSON form
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoggingUtil.logTrace("doGet in RetrieveInfoServlet");
		EmployeeDaoImp dao = new EmployeeDaoImp();
		ReimbursementDaoImp rfDao = new ReimbursementDaoImp();
		HttpSession sess = req.getSession(false);
		
		if(sess!=null) {
			String user = (String) sess.getAttribute("username");
			Employee emp = dao.retreiveEmployeeByUsername(user);
			if (emp.getId()>0) {
				LoggingUtil.logTrace("Retreiving info from RetreiveInfo");
				List<List<ReimbursementForm>> listArray = new ArrayList<List<ReimbursementForm>>();
				List<ReimbursementForm> rfList = rfDao.retreiveAllFormsOfEmployee(emp);
				List<ReimbursementForm> rfList2= rfDao.retreiveFormsToApprove(emp);
				listArray.add(rfList);
				listArray.add(rfList2);
				ObjectMapper om = new ObjectMapper();
				String rfListString = om.writeValueAsString(listArray);
				System.out.println(rfListString);
				resp.getWriter().write(rfListString);
				
				
			} else {
				resp.sendRedirect("login");
			}
		}else {
			resp.sendRedirect("login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
	
}

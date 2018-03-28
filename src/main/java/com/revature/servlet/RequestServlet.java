package com.revature.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.DepartmentDaoImp;
import com.revature.dao.EmployeeDaoImp;
import com.revature.dao.GradingFormatDaoImp;
import com.revature.dao.ReimbursementDaoImp;
import com.revature.pojo.Employee;
import com.revature.pojo.GradingFormat;
import com.revature.pojo.ReimbursementForm;

public class RequestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GradingFormatDaoImp dao = new GradingFormatDaoImp();
		//Retreive all the grading formats
		List<GradingFormat> list = dao.retrieveAllGradingFormats();
		//Send them in JSON format
		ObjectMapper om = new ObjectMapper();
		String listString = om.writeValueAsString(list);
		resp.getWriter().write(listString);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess = req.getSession(false);
		
		if(sess!=null) {
			//Dao's used to make rf form
			EmployeeDaoImp eDao = new EmployeeDaoImp();
			ReimbursementDaoImp rfDao = new ReimbursementDaoImp();
			GradingFormatDaoImp gfDao = new GradingFormatDaoImp();
			DepartmentDaoImp dDao = new DepartmentDaoImp();
			//Get all the data from the form
			String userName = (String) sess.getAttribute("username");
			String eventName = req.getParameter("event");
			String date = req.getParameter("date");
			String time = req.getParameter("time");
			String location = req.getParameter("location");
			String cost = req.getParameter("cost");
			String type = req.getParameter("type");
			String format = req.getParameter("format");
			String grade = req.getParameter("grade");
			String justification = req.getParameter("justification");
			//convert the date and time to a Timestamp
			System.out.println(eventName + ", "+ date +", "+time+", "+location+", "+cost+", "+type+", "+format+", "+grade+", "+justification);
			String dateTime = date+" "+time;
			System.out.println(dateTime);
			LocalDateTime localTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
			System.out.println(localTime.toString());
			Timestamp tsTime = Timestamp.valueOf(localTime);
			
			//get the user applying for the form to get their userId
			Employee emp = eDao.retreiveEmployeeByUsername(userName);
			//get the grading format using the format's name
			GradingFormat gf = gfDao.retreiveFormatByName(format);
			//get the department head id given the department id
			int dHead = dDao.retrieveDepartmentHeadId(emp.getDepId());
			
			//create the reimbursementForm
			ReimbursementForm rf = new ReimbursementForm(eventName,tsTime,Double.parseDouble(cost),location,gf.getgId(),type,Double.parseDouble(grade),
					emp.getId(),emp.getReportsTo(),dHead,justification);
			rfDao.createRFormPrpStmt(rf);	
			
			resp.sendRedirect("employee.html");
		}else {
			resp.sendRedirect("login");
		}
		
	}

}

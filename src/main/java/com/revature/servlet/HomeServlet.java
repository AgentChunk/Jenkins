package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeeDaoImp;
import com.revature.pojo.Employee;

public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = -2169295968068897861L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//get the username and password
		EmployeeDaoImp dao = new EmployeeDaoImp();
		System.out.println("In Trms Project");
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		System.out.println("Username: " + user);
		System.out.println("Password: " + pass);
		//First check if username exists
		Employee emp = dao.retreiveEmployeeByUsername(user);
		//if employee's id is above 0 than an employee was retrieved
		if(emp.getId()>0 && emp.getPassword().equals(pass)) {
			//setting up a session with user after loggin in
			HttpSession sess = request.getSession();
			sess.setAttribute("username", user);
			response.sendRedirect("employee.html");
			
		}else {
			response.sendRedirect("login");
		}
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doGet(request,response);
	}
}

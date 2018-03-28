package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeeDaoImp;
import com.revature.util.LoggingUtil;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 817105812389880890L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoggingUtil.logTrace("LoginServlet doGet called");
		EmployeeDaoImp dao = new EmployeeDaoImp();
		// Serve login.html
		HttpSession sess = request.getSession(false);
		if (sess != null) {
			System.out.println("Session not null");
			String user = (String) sess.getAttribute("username");
			if (dao.retreiveEmployeeByUsername(user).getId()>0) {
				response.sendRedirect("employee.html");
			} else {
				request.getRequestDispatcher("login.html").forward(request, response);
			}

		} else {
			request.getRequestDispatcher("login.html").forward(request, response);
		}
		System.out.println("doGet in LoginServlet");
	}
	
	//Executed when user enters username and password on login.html
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		LoggingUtil.logTrace("LoginServlet doPost called");
		request.getRequestDispatcher("home").forward(request, response);
	}
}

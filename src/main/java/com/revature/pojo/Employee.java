package com.revature.pojo;

public class Employee {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int reportsTo;
	private String position;
	private String department;
	private int depId;
	private double reimbursmentLeft;
	
	public Employee() {
		
	}
	
	public Employee(String user) {
		username=user;
	}
	
	public Employee(String user, String pass) {
		username=user;
		password=pass;
	}
	
	public Employee(String user, String pass, String first, String last, int reports, String pos, String dep, double reimburse) {
		username=user;
		password=pass;
		firstName=first;
		lastName =last;
		reportsTo =reports;
		position=pos;
		department=dep;
		reimbursmentLeft=reimburse;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String postion) {
		this.position = postion;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getReimbursmentLeft() {
		return reimbursmentLeft;
	}

	public void setReimbursmentLeft(double reimbursmentLeft) {
		this.reimbursmentLeft = reimbursmentLeft;
	}
	
	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", reportsTo=" + reportsTo + ", position=" + position + ", department="
				+ department + ", reimbursmentLeft=" + reimbursmentLeft + "]";
	}

	
	
	
	
}

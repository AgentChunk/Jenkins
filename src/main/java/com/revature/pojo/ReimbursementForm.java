package com.revature.pojo;

import java.sql.Date;
import java.sql.Timestamp;

public class ReimbursementForm {
	
	private int reqId;
	private String eventName;
	private Timestamp eventDate;
	private double cost;
	private String eventLocation;
	private int gradeId;
	private String eventType;
	private Date formDate;
	private double grade;
	private int requester;
	private boolean supApproval;
	private int supervisor;
	private boolean dHeadApproval;
	private int dHeadId;
	private boolean bcoApproval;
	private double approvedAmount;
	private String reason;
	
	public ReimbursementForm() {}

	
	
	public ReimbursementForm(String eventName, Timestamp eventDate, double cost, String eventLocation, int gradeId,
			String eventType, double grade, int requester, int supervisor, int dHeadId, String reason) {
		super();
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.cost = cost;
		this.eventLocation = eventLocation;
		this.gradeId = gradeId;
		this.eventType = eventType;
		this.grade = grade;
		this.requester = requester;
		this.supervisor = supervisor;
		this.dHeadId = dHeadId;
		this.reason = reason;
	}



	public ReimbursementForm(int reqId, String eventName, Timestamp date, double cost,String eventLocation, int gradeId, String eventType,
			Date formDate, double grade, int requester, boolean supApproval, int supervisor, boolean dHeadApproval,
			int dHeadId, boolean bcoApproval, double approvedAmount) {
		super();
		this.reqId = reqId;
		this.eventName = eventName;
		this.eventDate = date;
		this.eventLocation = eventLocation;
		this.cost = cost;
		this.gradeId = gradeId;
		this.eventType = eventType;
		this.formDate = formDate;
		this.grade = grade;
		this.requester = requester;
		this.supApproval = supApproval;
		this.supervisor = supervisor;
		this.dHeadApproval = dHeadApproval;
		this.dHeadId = dHeadId;
		this.bcoApproval = bcoApproval;
		this.approvedAmount = approvedAmount;
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Timestamp getEventDate() {
		return eventDate;
	}

	public void setEventDate(Timestamp date) {
		this.eventDate = date;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getFormDate() {
		return formDate;
	}

	public void setFormDate(Date formDate) {
		this.formDate = formDate;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public int getRequester() {
		return requester;
	}

	public void setRequester(int requester) {
		this.requester = requester;
	}

	public boolean isSupApproval() {
		return supApproval;
	}

	public void setSupApproval(boolean supApproval) {
		this.supApproval = supApproval;
	}

	public int getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(int supervisor) {
		this.supervisor = supervisor;
	}

	public boolean isdHeadApproval() {
		return dHeadApproval;
	}

	public void setdHeadApproval(boolean dHeadApproval) {
		this.dHeadApproval = dHeadApproval;
	}

	public int getdHeadId() {
		return dHeadId;
	}

	public void setdHeadId(int dHeadId) {
		this.dHeadId = dHeadId;
	}

	public boolean isBcoApproval() {
		return bcoApproval;
	}

	public void setBcoApproval(boolean bcoApproval) {
		this.bcoApproval = bcoApproval;
	}

	public double getApprovedAmount() {
		return approvedAmount;
	}

	public void setApprovedAmount(double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	@Override
	public String toString() {
		return "ReimbursementForm [reqId=" + reqId + ", eventName=" + eventName + ", eventDate=" + eventDate + ", cost="
				+ cost + ", gradeId=" + gradeId + ", eventType=" + eventType + ", formDate=" + formDate + ", grade="
				+ grade + ", requester=" + requester + ", supApproval=" + supApproval + ", supervisor=" + supervisor
				+ ", dHeadApproval=" + dHeadApproval + ", dHeadId=" + dHeadId + ", bcoApproval=" + bcoApproval
				+ ", approvedAmount=" + approvedAmount + ", reason=" + reason + "]";
	}
	
	
	
}

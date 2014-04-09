package com.beans.leaveapp.leavetransaction.model;

import java.util.Date;
import java.text.SimpleDateFormat;


public class AdminLeaveTransaction {
	
	
	SimpleDateFormat s = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	private int id;
	private String employeeName;
	private String leaveType;
	private Date applicationDate;
	private Date startTime;
	private Date endTime;
	private String reason;
	private double noOfDays;
	private double noOfHours;
	
	public AdminLeaveTransaction(){
	}
	
	public AdminLeaveTransaction(int id,String employeeName, String leaveType,
			Date applicationDate, Date startDate, Date endDate,
			String reason, double noOfDays, double noOfHours) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.leaveType = leaveType;
		this.applicationDate = applicationDate;
		this.startTime = startDate;
		this.endTime = endDate;
		this.setReason(reason);
		this.noOfDays = noOfDays;
		this.noOfHours = noOfHours;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		if(applicationDate == null){
			applicationDate = new Date();
		}
		this.applicationDate = applicationDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
	
		this.endTime = endTime;
	}
	public double getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(double noOfDays) {
		this.noOfDays = noOfDays;
	}
	
	public double getNoOfHours() {
		return noOfHours;
	}
	public void setNoOfHours(double noOfHours) {
		this.noOfHours = noOfHours;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	 

}

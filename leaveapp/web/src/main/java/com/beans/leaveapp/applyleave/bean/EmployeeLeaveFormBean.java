package com.beans.leaveapp.applyleave.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.beans.common.security.users.model.Users;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.yearlyentitlement.model.YearlyEntitlement;

public class EmployeeLeaveFormBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String selectedLeaveType = "";
	private Employee employee;
	private Users actorUsers;
	private YearlyEntitlement yearlyEntitlement = new YearlyEntitlement();
	private Date startDate;
	private Date endDate;
	private String reason;
	private Double numberOfDays;
	
	public String getSelectedLeaveType() {
		return selectedLeaveType;
	}
	public void setSelectedLeaveType(String selectedLeaveType) {
		this.selectedLeaveType = selectedLeaveType;
	}
	
	public void leaveTypeSelected(ValueChangeEvent e) {
		setSelectedLeaveType(e.getNewValue().toString());
		findYearlyEntitlementForLeaveType();
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Users getActorUsers() {
		return actorUsers;
	}
	public void setActorUsers(Users actorUsers) {
		this.actorUsers = actorUsers;
	}
	
	public YearlyEntitlement getYearlyEntitlement() {
		return yearlyEntitlement;
	}
	public void setYearlyEntitlement(YearlyEntitlement yearlyEntitlement) {
		this.yearlyEntitlement = yearlyEntitlement;
	}
	
	private void findYearlyEntitlementForLeaveType() {
		yearlyEntitlement = new YearlyEntitlement();
		yearlyEntitlement.setDeleted(false);
		yearlyEntitlement.setEmployee(employee);
		yearlyEntitlement.setEntitlement(12);
		yearlyEntitlement.setAvailableBalance(10);
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Double getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(Double numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	
	public void applyLeave() {
		
		if(startDate.after(endDate)) {
			FacesMessage msg = new FacesMessage("Error", "Start Date must be before End Date.");  
			  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
		} else {
		
			setSelectedLeaveType("");
			setStartDate(null);
			setEndDate(null);
			setReason("");
			
			FacesMessage msg = new FacesMessage("Info", "Leave Applied. You will be notified if it is approved.");  
			  
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
		
	}
}

package com.beans.leaveapp.applyleave.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.beans.common.security.users.model.Users;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.yearlyentitlement.model.YearlyEntitlement;
import com.beans.leaveapp.yearlyentitlement.service.YearlyEntitlementNotFound;
import com.beans.leaveapp.yearlyentitlement.service.YearlyEntitlementService;

public class EmployeeLeaveFormBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int selectedYearlyEntitlement = 0;
	private String leaveType;
	private Employee employee;
	private Users actorUsers;
	private YearlyEntitlement yearlyEntitlement = new YearlyEntitlement();
	private Date startDate;
	private Date endDate;
	private String reason;
	private Double numberOfDays;
	private YearlyEntitlementService yearlyEntitlementService;
	
	public int getSelectedYearlyEntitlement() {
		return selectedYearlyEntitlement;
	}
	public void setSelectedYearlyEntitlement(int selectedYearlyEntitlement) {
		this.selectedYearlyEntitlement = selectedYearlyEntitlement;
	}
	
	public void yearlyEntitlementSelected(ValueChangeEvent e) {
		setSelectedYearlyEntitlement(Integer.parseInt(e.getNewValue().toString()));
		findYearlyEntitlement();
		
		if(getYearlyEntitlement() != null) {
			setLeaveType(getYearlyEntitlement().getLeaveType().getName());
		}
	}
	
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
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
	
	private void findYearlyEntitlement() {
		try {
			yearlyEntitlement = yearlyEntitlementService.findOne(selectedYearlyEntitlement);
		} catch(YearlyEntitlementNotFound e) {
			FacesMessage msg = new FacesMessage("Error", "Ooops! Something serious has happened. Contact Administrator.");  
			  
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
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
	
	public YearlyEntitlementService getYearlyEntitlementService() {
		return yearlyEntitlementService;
	}
	public void setYearlyEntitlementService(
			YearlyEntitlementService yearlyEntitlementService) {
		this.yearlyEntitlementService = yearlyEntitlementService;
	}
	
	public void applyLeave() {
		
		if(startDate.after(endDate)) {
			FacesMessage msg = new FacesMessage("Error", "Start Date must be before End Date.");  
			  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
		} else {
		
			setSelectedYearlyEntitlement(0);
			setLeaveType("");
			setStartDate(null);
			setEndDate(null);
			setReason("");
			
			FacesMessage msg = new FacesMessage("Info", "Leave Applied. You will be notified if it is approved.");  
			  
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
		
	}
	
}

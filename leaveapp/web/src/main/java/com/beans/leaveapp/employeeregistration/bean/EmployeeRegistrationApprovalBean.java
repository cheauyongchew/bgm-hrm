package com.beans.leaveapp.employeeregistration.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.beans.common.audit.service.AuditTrail;
import com.beans.common.audit.service.SystemAuditTrailActivity;
import com.beans.common.audit.service.SystemAuditTrailLevel;
import com.beans.common.security.users.model.Users;
import com.beans.leaveapp.employee.model.RegisteredEmployee;
import com.beans.leaveapp.employee.service.EmployeeRegistrationService;
import com.beans.leaveapp.employeeregistration.model.RegisteredEmployeeDataModel;

public class EmployeeRegistrationApprovalBean implements Serializable{
private static final long serialVersionUID = 1L;
	
	
	EmployeeRegistrationService employeeRegistrationService;
	private List<RegisteredEmployee> registeredEmployeeList;
	private RegisteredEmployeeDataModel registeredEmployeeDataModel;
	private RegisteredEmployee selectedRegisteredEmployee = new RegisteredEmployee();
	private boolean insertDeleted = false;
	private Users actorUsers;
	private AuditTrail auditTrail;
	

	public EmployeeRegistrationService getEmployeeRegistrationService() {
		return employeeRegistrationService;
	}
	public void setEmployeeRegistrationService(
			EmployeeRegistrationService employeeRegistrationService) {
		this.employeeRegistrationService = employeeRegistrationService;
	}
	
	public RegisteredEmployee getSelectedRegisteredEmployee() {
		return selectedRegisteredEmployee;
	}
	public void setSelectedRegisteredEmployee(
			RegisteredEmployee selectedRegisteredEmployee) {
		this.selectedRegisteredEmployee = selectedRegisteredEmployee;
	}

	public boolean isInsertDeleted() {
		return insertDeleted;
	}

	public void setInsertDelete(boolean insertDeleted) {
		this.insertDeleted = insertDeleted;
	}

	public List<RegisteredEmployee> getRegisteredEmployeeList() {
		if(registeredEmployeeList == null || insertDeleted == true) {
			 try {
				 registeredEmployeeList = getEmployeeRegistrationService().getPendingRegisteredEmployee(actorUsers.getUsername());
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return registeredEmployeeList;
	}
	public void setRegisteredEmployeeDataModel(
			RegisteredEmployeeDataModel registeredEmployeeDataModel) {
		this.registeredEmployeeDataModel = registeredEmployeeDataModel;
	}
	
	public RegisteredEmployeeDataModel getRegisteredEmployeeDataModel() {
		if(registeredEmployeeDataModel == null || insertDeleted == true) {
			registeredEmployeeDataModel = new RegisteredEmployeeDataModel(getRegisteredEmployeeList());
		}
				
		return registeredEmployeeDataModel;
	}
	public void setRegisteredEmployeeList(
			List<RegisteredEmployee> registeredEmployeeList) {
		this.registeredEmployeeList = registeredEmployeeList;
	}
	
	
	public void doApproveEmployeeRegistration() {
		try {
			auditTrail.log(SystemAuditTrailActivity.UPDATED, SystemAuditTrailLevel.INFO, getActorUsers().getId(), getActorUsers().getUsername(), getActorUsers().getUsername() + " has updated a department");
			
		
		} catch(Exception e) {
			e.printStackTrace(); 
		}
	}
	
	public void doRejectEmployeeRegistration() {
		try {
			auditTrail.log(SystemAuditTrailActivity.UPDATED, SystemAuditTrailLevel.INFO, getActorUsers().getId(), getActorUsers().getUsername(), getActorUsers().getUsername() + " has updated a department");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onRowSelect(SelectEvent event) {  
		setSelectedRegisteredEmployee((RegisteredEmployee) event.getObject());
        FacesMessage msg = new FacesMessage("Employee Registration Selected", selectedRegisteredEmployee.getFullname());  
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	
	public Users getActorUsers() {
		return actorUsers;
	}
	public void setActorUsers(Users actorUsers) {
		this.actorUsers = actorUsers;
	}
	
	
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}
	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}
}

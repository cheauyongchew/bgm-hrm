package com.beans.leaveapp.employeetype.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;






import com.beans.common.audit.service.AuditTrail;
import com.beans.common.audit.service.SystemAuditTrailActivity;
import com.beans.common.audit.service.SystemAuditTrailLevel;
import com.beans.common.security.users.model.Users;
import com.beans.leaveapp.employeetype.model.EmployeeTypeDataModel;
import com.beans.leaveapp.employeetype.model.EmployeeType;
import com.beans.leaveapp.employeetype.service.EmployeeTypeNotFound;
import com.beans.leaveapp.employeetype.service.EmployeeTypeService;


public class EmployeeTypeManagementBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// EmployeeGradeRepository employeeGradeRepository;
	EmployeeTypeService employeeTypeService;
	private List<EmployeeType> employeeTypeList;
	private EmployeeTypeDataModel employeeTypeDataModel;
	private EmployeeType newEmployeeType = new EmployeeType();
	private EmployeeType selectedEmployeeType = new EmployeeType();
	private boolean insertDeleted = false;
	private Users actorUsers;
	private AuditTrail auditTrail;
	
	public EmployeeTypeService getEmployeeTypeService() {
		return employeeTypeService;
	}

	public EmployeeType getNewEmployeeType() {
		return newEmployeeType;
	}

	public void setNewEmployeeType(EmployeeType newEmployeeType) {
		this.newEmployeeType = newEmployeeType;
	}

	public EmployeeType getSelectedEmployeeType() {
		return selectedEmployeeType;
	}

	public void setSelectedEmployeeType(EmployeeType selectedEmployeeType) {
		this.selectedEmployeeType = selectedEmployeeType;
	}

	public boolean isInsertDeleted() {
		return insertDeleted;
	}

	public void setInsertDelete(boolean insertDeleted) {
		this.insertDeleted = insertDeleted;
	}

	public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
		this.employeeTypeService = employeeTypeService;
	}

	public List<EmployeeType> getEmployeeTypeList() {
		if(employeeTypeList == null || insertDeleted == true) {
			 try {
				employeeTypeList = getEmployeeTypeService().findAll();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return employeeTypeList;	
	}
	
	public EmployeeTypeDataModel getEmployeeTypeDataModel() {
		if(employeeTypeDataModel == null || insertDeleted == true) {
			employeeTypeDataModel = new EmployeeTypeDataModel(getEmployeeTypeList());
		}
		
		return employeeTypeDataModel;
	}

	public void setEmployeeTypeDataModel(
			EmployeeTypeDataModel employeeTypeDataModel) {
		this.employeeTypeDataModel = employeeTypeDataModel;
	}

	public void setEmployeeTypeList(List<EmployeeType> employeeTypeList) {
		this.employeeTypeList = employeeTypeList;
	}

	public void doCreateEmployeeType() throws EmployeeTypeNotFound {
		
		newEmployeeType.setDeleted(false);
		getEmployeeTypeService().create(newEmployeeType);
		setInsertDelete(true);
		auditTrail.log(SystemAuditTrailActivity.CREATED, SystemAuditTrailLevel.INFO, getActorUsers().getId(), getActorUsers().getUsername(), getActorUsers().getUsername() + " has created an Employee Type");
		newEmployeeType = new EmployeeType();
	}
	
	public void doUpdateEmployeeType() throws EmployeeTypeNotFound {
		try {
			System.out.println("New name:" + selectedEmployeeType.getName());
			System.out.println("ID: " + selectedEmployeeType.getId());
			getEmployeeTypeService().update(selectedEmployeeType);
			auditTrail.log(SystemAuditTrailActivity.UPDATED, SystemAuditTrailLevel.INFO, getActorUsers().getId(), getActorUsers().getUsername(), getActorUsers().getUsername() + " has updated an Employee Type");
			
		// RequestContext.getCurrentInstance().
		} catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error", "Employee Type With id: " + selectedEmployeeType.getId() + " not found!");  
			  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
		}
	}
	
	public void onRowSelect(SelectEvent event) {  
		setSelectedEmployeeType((EmployeeType) event.getObject());
        FacesMessage msg = new FacesMessage("Employee Type Selected", selectedEmployeeType.getName());  
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	
	public void doDeleteEmployeeType() throws Exception, EmployeeTypeNotFound {
		try {
			getEmployeeTypeService().delete(selectedEmployeeType.getId());
			auditTrail.log(SystemAuditTrailActivity.DELETED, SystemAuditTrailLevel.INFO, getActorUsers().getId(), getActorUsers().getUsername(), getActorUsers().getUsername() + " has deleted an Employee Type");
			
		} catch(EmployeeTypeNotFound e) {
			FacesMessage msg = new FacesMessage("Error", "Employee Type With id: " + selectedEmployeeType.getId() + " not found!");  
			  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
		}
		
		setInsertDelete(true);
	}
	
	public void doResetFrom() throws EmployeeTypeNotFound {
		
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

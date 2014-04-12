
package com.beans.leaveapp.leavetype.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.beans.common.security.users.model.Users;
import com.beans.leaveapp.employeetype.model.EmployeeType;
import com.beans.leaveapp.leavetype.model.LeaveType;
import com.beans.leaveapp.leavetype.model.LeaveTypeDataModel;
import com.beans.leaveapp.leavetype.service.LeaveTypeNotFound;
import com.beans.leaveapp.leavetype.service.LeaveTypeService;



public class LeaveTypeManagementBean implements Serializable{
		
	    private static final long serialVersionUID = 1L;
		private  LeaveTypeService leaveTypeService=getLeaveTypeService();
		private List<LeaveType> leaveTypeList;
		private LeaveTypeDataModel leaveTypeDataModel;
		private LeaveType newLeaveType = new LeaveType();
		private LeaveType selectedLeaveType = new LeaveType();
		private boolean insertDelete = false;
		private List<LeaveType> searchLeaveType;
		private String name;
		private Users actorUsers;

		
		public Users getActorUsers() {
			return actorUsers;
		}

		public void setActorUsers(Users actorUsers) {
			this.actorUsers = actorUsers;
		}

		public List<LeaveType> getSearchLeaveType() {
			return searchLeaveType;
		}
	
		public void setSearchLeaveType(List<LeaveType> searchLeaveType) {
			System.out.println("in set method");
			this.searchLeaveType = searchLeaveType;
		}
	
		public LeaveTypeService getLeaveTypeService() {
			return leaveTypeService;
		}
		
		public void setLeaveTypeService(LeaveTypeService leaveTypeService) {
			this.leaveTypeService = leaveTypeService;
		}
		
		public List<LeaveType> getLeaveTypeList() {
			if(leaveTypeList == null || insertDelete == true) {
				leaveTypeList =leaveTypeService.findAll();			
			}		
			return leaveTypeList;
		}
		
		public void setLeaveTypeList(List<LeaveType> leaveTypeList) {
			this.leaveTypeList = leaveTypeList;
		}
		
		public LeaveTypeDataModel getLeaveTypeDataModel() {
			if(leaveTypeDataModel == null || insertDelete == true) {
				leaveTypeDataModel = new LeaveTypeDataModel(getLeaveTypeList());
			}
			return leaveTypeDataModel;
		}
		
		public void setLeaveTypeDataModel(LeaveTypeDataModel leaveTypeDataModel) {
			this.leaveTypeDataModel = leaveTypeDataModel;
		}
		
		
		public LeaveType getNewLeaveType() {
			return newLeaveType;
		}
		
		public void setNewLeaveType(LeaveType newLeaveType) {
			this.newLeaveType = newLeaveType;
		}
		
		public void doCreateLeaveType() {
			EmployeeType employeeType = getLeaveTypeService().findByEmployeeName(name);
			newLeaveType.setEmployeeTypeId(employeeType);
			newLeaveType.setDeleted(false);
			newLeaveType.setCreatedBy(getActorUsers().getUsername());
			newLeaveType.setCreationTime(new java.util.Date());
			getLeaveTypeService().create(newLeaveType);
			setInsertDelete(true);
		}
		
		public LeaveType getSelectedLeaveType() {
			return selectedLeaveType;
		}
		public void setSelectedLeaveType(LeaveType selectedLeaveType) {
			this.selectedLeaveType = selectedLeaveType;
		}
			
		public void doUpdateLeaveType() {
			try {
				EmployeeType employeeType = getLeaveTypeService().findByEmployeeName(name);
				selectedLeaveType.setEmployeeTypeId(employeeType);
				System.out.println("New name:" + selectedLeaveType.getName());
				System.out.println("ID: " + selectedLeaveType.getId());
				selectedLeaveType.setLastModifiedBy(getActorUsers().getUsername());
				selectedLeaveType.setLastModifiedTime(new java.util.Date());
				getLeaveTypeService().update(selectedLeaveType);
				this.setInsertDelete(true);
			} catch(LeaveTypeNotFound e) {
				FacesMessage msg = new FacesMessage("Error", "Leave Type With id: " + selectedLeaveType.getId() + " not found!");  
				  
		        FacesContext.getCurrentInstance().addMessage(null, msg);  
			}
		}
		
		public void onRowSelect(SelectEvent event) {  
			setSelectedLeaveType((LeaveType) event.getObject());
	        FacesMessage msg = new FacesMessage("Leave Type Selected", selectedLeaveType.getName());  
	        
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
	    }  
		
		public void doDeleteLeaveType() {
			try {
				getLeaveTypeService().delete(selectedLeaveType.getId());
				this.setInsertDelete(true);
			} catch(LeaveTypeNotFound e) {
				FacesMessage msg = new FacesMessage("Error", "Leave Type With id: " + selectedLeaveType.getId() + " not found!");  
				  
		        FacesContext.getCurrentInstance().addMessage(null, msg);  
			}
			
			setInsertDelete(true);
		}
		
		public void setInsertDelete(boolean insertDelete) {
			this.insertDelete = insertDelete;
		}
		
		public boolean isInsertDelete() {
			return insertDelete;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
 


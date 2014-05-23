package com.beans.leaveapp.applyleave.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.beans.common.audit.service.AuditTrail;
import com.beans.common.audit.service.SystemAuditTrailActivity;
import com.beans.common.audit.service.SystemAuditTrailLevel;
import com.beans.common.security.users.model.Users;
import com.beans.leaveapp.applyleave.model.LeaveApprovalDataModel;
import com.beans.leaveapp.applyleave.service.LeaveApplicationService;
import com.beans.leaveapp.employee.model.RegisteredEmployee;
import com.beans.leaveapp.employeeregistration.model.RegisteredEmployeeDataModel;
import com.beans.leaveapp.leavetransaction.model.LeaveTransaction;

public class LeaveApprovalMgmtBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<LeaveTransaction>  leaveRequestList;
	private boolean insertDeleted = false;
	private Users actorUsers;
	private AuditTrail auditTrail;
	private LeaveApplicationService leaveApplicationService;
	private LeaveApprovalDataModel LeaveApprovalDataModel;
	private LeaveTransaction selectedLeaveRequest;
	
	
	public LeaveApprovalDataModel getLeaveApprovalDataModel() {
		if(LeaveApprovalDataModel == null || insertDeleted == true) {
			LeaveApprovalDataModel = new LeaveApprovalDataModel(getLeaveRequestApprovalList());
		}
		return LeaveApprovalDataModel;
	}

	public void setLeaveApprovalDataModel(LeaveApprovalDataModel leaveApprovalDataModel) {
		LeaveApprovalDataModel = leaveApprovalDataModel;
	}

	public LeaveApplicationService getLeaveApplicationService() {
		return leaveApplicationService;
	}
	public void setLeaveApplicationService(
			LeaveApplicationService leaveApplicationService) {
		this.leaveApplicationService = leaveApplicationService;
	}
	public boolean isInsertDeleted() {
		return insertDeleted;
	}
	public void setInsertDeleted(boolean insertDeleted) {
		this.insertDeleted = insertDeleted;
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
	public List<LeaveTransaction> getLeaveRequestList() {
		return leaveRequestList;
	}
	public void setLeaveRequestList(List<LeaveTransaction> leaveRequestList) {
		this.leaveRequestList = leaveRequestList;
	}
	
 	
	public LeaveTransaction getSelectedLeaveRequest() {
		return selectedLeaveRequest;
	}

	public void setSelectedLeaveRequest(LeaveTransaction selectedLeaveRequest) {
		this.selectedLeaveRequest = selectedLeaveRequest;
	}

	public List<LeaveTransaction> getLeaveRequestApprovalList() {
		if(leaveRequestList == null || insertDeleted == true) {
			 try {
				 leaveRequestList =   getLeaveApplicationService().getPendingLeaveRequestsList(actorUsers.getUsername());
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
		}
		
		return leaveRequestList;
	}
	

	public void doApproveLeaveRequest() {
		try {
			
			System.out.println("Leave Request Approved...!!!");
			//auditTrail.log(SystemAuditTrailActivity.APPROVED, SystemAuditTrailLevel.INFO, getActorUsers().getId(), getActorUsers().getUsername(), getActorUsers().getUsername() + " has approved a employee registration of " + selectedRegisteredEmployee.getFullname());
			leaveApplicationService.approveLeaveOfEmployee(selectedLeaveRequest, getActorUsers().getUsername(),getActorUsers().getUserRoles());
		    setInsertDeleted(true);
			
		} catch(Exception e) {
			e.printStackTrace(); 
		}
	}
	
	public void doRejectLeaveRequest() {
		try {
			if(selectedLeaveRequest.getReason() == null || selectedLeaveRequest.getReason().trim().equals("")) {
				FacesMessage msg = new FacesMessage("Please enter Reason for rejection");  
		        
		        FacesContext.getCurrentInstance().addMessage(null, msg);  
			} else {
				
				System.out.println("Leave Request Rejected...");
				leaveApplicationService.rejectLeaveOfEmployee(selectedLeaveRequest, getActorUsers().getUsername());
				
				auditTrail.log(SystemAuditTrailActivity.REJECTED, SystemAuditTrailLevel.INFO, getActorUsers().getId(), getActorUsers().getUsername(), getActorUsers().getUsername() + " has rejected a leave request of " + selectedLeaveRequest.getEmployee().getName() + " due to " + selectedLeaveRequest.getReason());
				
			}
			setInsertDeleted(true);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onRowSelect(SelectEvent event) {  
		setSelectedLeaveRequest((LeaveTransaction) event.getObject()); 
    }
}

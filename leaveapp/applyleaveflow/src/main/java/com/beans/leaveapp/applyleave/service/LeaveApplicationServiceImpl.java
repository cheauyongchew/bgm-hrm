package com.beans.leaveapp.applyleave.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.beans.common.security.role.model.Role;
import com.beans.common.security.role.service.RoleNotFound;
import com.beans.leaveapp.applyleave.model.ApprovalLevelModel;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.jbpm6.util.JBPM6Runtime;
import com.beans.leaveapp.leaveapplicationcomment.model.LeaveApplicationComment;
import com.beans.leaveapp.leavetransaction.model.LeaveTransaction;
import com.beans.leaveapp.leavetransaction.service.LeaveTransactionService;
import com.beans.leaveapp.yearlyentitlement.model.YearlyEntitlement;

public class LeaveApplicationServiceImpl implements LeaveApplicationService {

	private static final String PROCESS_NAME = "com.beans.leaveapp.bpmn.applyleave";
	private JBPM6Runtime applyLeaveRuntime;
	private LeaveTransactionService leaveTransactionService;
	
	@Override
	public void submitLeave(Employee employee,
			YearlyEntitlement yearlyEntitlement,
			LeaveTransaction leaveTransaction) throws RoleNotFound, LeaveApplicationException{
		Set<Role> userRoles = employee.getUsers().getUserRoles();
		
		if(!isEmployee(userRoles)) {
			throw new RoleNotFound("You are not an employee.");
		}
		Role assignedRoleInLeaveFlow = getEmployeeOrTeamLead(userRoles);
		ApprovalLevelModel approvalLevelModel = new ApprovalLevelModel();
		approvalLevelModel.setRole(assignedRoleInLeaveFlow.getRole());
		
		List<LeaveApplicationComment> leaveApplicationCommentList = new ArrayList<LeaveApplicationComment>();
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("approvalLevelModel", approvalLevelModel);
		parameterMap.put("employee", employee);
		parameterMap.put("leaveTransaction", leaveTransaction);
		parameterMap.put("yearlyEntitlement", yearlyEntitlement);
		parameterMap.put("isTeamLeadApproved", new Boolean(false));
		parameterMap.put("isOperDirApproved", new Boolean(false));
		parameterMap.put("role", assignedRoleInLeaveFlow.getRole());
		parameterMap.put("leaveApplicationCommentList", leaveApplicationCommentList);
		
		long processInstanceId = applyLeaveRuntime.startProcessWithInitialParametersAndFireBusinessRules(PROCESS_NAME, parameterMap);
		
		List<Long> taskIdList = applyLeaveRuntime.getTaskIdsByProcessInstanceId(processInstanceId);
		if(taskIdList.size() == 0) {
			throw new LeaveApplicationException("Ooops! Something serious has happened. Please contact your Administrator");
		}
		
		Long currentTaskId = taskIdList.get(0);
		
		applyLeaveRuntime.submitTask(employee.getUsers().getUsername(), currentTaskId, parameterMap);
		
		leaveTransaction.setTaskId(currentTaskId);
		
		getLeaveTransactionService().insertFromWorkflow(leaveTransaction);
	}
	
	
	public JBPM6Runtime getApplyLeaveRuntime() {
		return applyLeaveRuntime;
	}
	public void setApplyLeaveRuntime(JBPM6Runtime applyLeaveRuntime) {
		this.applyLeaveRuntime = applyLeaveRuntime;
	}
	
	public LeaveTransactionService getLeaveTransactionService() {
		return leaveTransactionService;
	}
	public void setLeaveTransactionService(
			LeaveTransactionService leaveTransactionService) {
		this.leaveTransactionService = leaveTransactionService;
	}
	
	
	private Role getEmployeeOrTeamLead(Set<Role> userRoles) {
		Role resultRole = null;
		
		Iterator<Role> roleIterator = userRoles.iterator();
		while(roleIterator.hasNext()) {
			Role currentRole = roleIterator.next();
			
			if(compareRole(currentRole, resultRole)) {
				resultRole = currentRole;
			}
		}
		
		return resultRole;
	}
	
	
	private boolean compareRole(Role firstRole, Role secondRole) {
		
		if(firstRole == null) {
			return false;
		}
		
		if(secondRole == null) {
			return true;
		}
		Integer firstRoleStanding = getRoleStanding(firstRole.getRole());
		Integer secondRoleStanding = getRoleStanding(secondRole.getRole());
		
		if(firstRoleStanding > secondRoleStanding) {
			return true;
		} else {
			return false;
		}
	}
	
	private Integer getRoleStanding(String roleName) {
		if(roleName == null || roleName.equals("")) {
			return -1;
		}
		
		if(roleName.equals("ROLE_EMPLOYEE")) {
			return 5;
		} else if(roleName.equals("ROLE_TEAMLEAD")) {
			return 10;
		} else {
			return -1;
		}
	}
	
	private boolean isEmployee(Set<Role> userRoles) {
		Iterator<Role> roleIterator = userRoles.iterator();
		while(roleIterator.hasNext()) {
			Role currentRole = roleIterator.next();
			if(currentRole.getRole().equals("ROLE_EMPLOYEE")) {
				return true;
			}
		}
		return false;
	}
}

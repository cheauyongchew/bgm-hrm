package com.beans.leaveapp.leavetransaction.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.expression.impl.ThisExpressionResolver;

import com.beans.common.audit.service.AuditTrail;
import com.beans.common.security.users.model.Users;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.service.EmployeeService;
import com.beans.leaveapp.employeetype.model.EmployeeType;
import com.beans.leaveapp.leavetransaction.model.LeaveTransaction;
import com.beans.leaveapp.leavetransaction.model.LeaveTransactionsDataModel;
import com.beans.leaveapp.leavetransaction.service.LeaveTransactionService;
import com.beans.leaveapp.leavetype.model.LeaveType;

public class LeaveTransactionManagementBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LeaveTransaction leaveTransaction = new LeaveTransaction();

	private LeaveTransactionService leaveTransactionService;
	private EmployeeService employeeService;
	private List<LeaveTransaction> leaveTransactionlist;
	private LeaveTransactionsDataModel leaveTransactionDataModel;
	private String employeename = this.getEmployeename();
	private String leaveType = this.getLeaveType();
	private LeaveTransaction selectedLeaveTransaction = new LeaveTransaction();
	private LeaveTransaction newLeaveTransaction = new LeaveTransaction();
	private List<String> employeeList;
	private List<String> leaveTypeList;
	boolean isInsert = false;
	private String name;
	private AuditTrail auditTrail;
	private Users actorUsers;

	public LeaveTransaction getSelectedLeaveTransaction() {
		return selectedLeaveTransaction;
	}

	public void setSelectedLeaveTransaction(
			LeaveTransaction selectedLeaveTransaction) {
		this.selectedLeaveTransaction = selectedLeaveTransaction;
	}

	public LeaveTransaction getNewLeaveTransaction() {
		return newLeaveTransaction;
	}

	public void setNewLeaveTransaction(LeaveTransaction newLeaveTransaction) {
		this.newLeaveTransaction = newLeaveTransaction;
	}

	public LeaveTransaction getLeaveTransaction() {
		return leaveTransaction;
	}

	public void setLeaveTransaction(LeaveTransaction leaveTransaction) {
		this.leaveTransaction = leaveTransaction;
	}

	public LeaveTransactionService getLeaveTransactionService() {
		return leaveTransactionService;
	}

	public void setLeaveTransactionService(
			LeaveTransactionService leaveTransactionService) {
		this.leaveTransactionService = leaveTransactionService;
	}

	public LeaveTransactionsDataModel getLeaveTransactionDataModel() {
		if (leaveTransactionDataModel == null || isInsert == true) {

			leaveTransactionDataModel = new LeaveTransactionsDataModel(this.getList());

		}
		return leaveTransactionDataModel;
	}

	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

	public Users getActorUsers() {
		return actorUsers;
	}

	public void setActorUsers(Users actorUsers) {
		this.actorUsers = actorUsers;
	}

	public void setLeaveTransactionDataModel(
			LeaveTransactionsDataModel leaveTransactionDataModel) {
		this.leaveTransactionDataModel = leaveTransactionDataModel;
	}

	public List<LeaveTransaction> getList() {

		if (leaveTransactionlist == null || this.isInsert == true) {

			leaveTransactionlist = getLeaveTransactionService().findAll();
			System.out.println(leaveTransactionlist.size());
		}
		return leaveTransactionlist;
	}

	public void setList(List<LeaveTransaction> leaveTransactionlist) {
		this.leaveTransactionlist = leaveTransactionlist;
	}

	public void doSearchLeaveTransaction() {

		try {
			if ((getEmployeename().trim().equals(""))
					&& (getLeaveType().trim().equals(""))) {
				this.leaveTransactionlist = null;
				this.leaveTransactionDataModel = null;
			} else {
				leaveTransactionlist = this.getLeaveTransactionService()
						.findByEmployeeORfindByLeaveTypeORBoth(
								getEmployeename(), getLeaveType());
				System.out.println(leaveTransactionlist.size());
				this.leaveTransactionDataModel = null;
				if (leaveTransactionlist != null) {
					// auditTrail.log(SystemAuditTrailActivity.ACCESSED,
					// SystemAuditTrailLevel.INFO,
					// actorUsers.getId(),actorUsers.getUsername(),
					// actorUsers.getUsername()+" searching Entitlement of : "+getEmployeename());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doUpdateLeaveTransaction() {

		Employee employee = getLeaveTransactionService().findByEmployee(
				employeename);
		LeaveType leaveType = getLeaveTransactionService().findByLeaveType(
				this.leaveType);
		selectedLeaveTransaction.setEmployee(employee);
		selectedLeaveTransaction.setLeaveType(leaveType);
		this.getLeaveTransactionService().update(selectedLeaveTransaction);
		this.setInsert(true);
	}

	public void doDeletedLeaveTransaction() {
		int id = selectedLeaveTransaction.getId();
		System.out.println(id);
		this.getLeaveTransactionService().delete(id);
		this.setInsert(true);
	}

	public void doCreateLeaveTransaction() {
		Employee employee = this.getLeaveTransactionService().findByEmployee(
				employeename);
		LeaveType leaveType = getLeaveTransactionService().findByLeaveType(
				this.leaveType);
		newLeaveTransaction.setEmployee(employee);
		newLeaveTransaction.setLeaveType(leaveType);
		getLeaveTransactionService().create(newLeaveTransaction);
		this.setInsert(true);
	}

	public void onRowSelect(SelectEvent event) {
		this.setSelectedLeaveTransaction((LeaveTransaction) event.getObject());
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public List<String> getEmployeeList() {
		employeeList = this.getLeaveTransactionService().findEmployeeNames();
		return employeeList;
	}

	public void setEmployeeList(List<String> employeeList) {
		this.employeeList = employeeList;
	}

	public List<String> getLeaveTypeList() {

		try {
			if(employeename != null){
			leaveTypeList = (List<String>) this.getLeaveTransactionService()
					.findLeaveTypes(employeename.trim());
			return leaveTypeList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaveTypeList;
	}

	public void setLeaveTypeList(List<String> leaveTypeList) {
		this.leaveTypeList = leaveTypeList;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public boolean isInsert() {
		return isInsert;
	}

	public void setInsert(boolean isInsert) {
		this.isInsert = isInsert;
	}

	// leaveTransactionReasonList =
	// Arrays.asList(LeaveTransactionReason.values());

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
  
	public void correspondingList(AjaxBehaviorEvent event){
		
	}

}

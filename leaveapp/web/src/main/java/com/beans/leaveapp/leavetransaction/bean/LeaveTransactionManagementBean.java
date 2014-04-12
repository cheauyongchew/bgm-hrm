package com.beans.leaveapp.leavetransaction.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.primefaces.event.SelectEvent;

import com.beans.common.audit.service.AuditTrail;
import com.beans.common.leavetransaction.LeaveTransactionReason;
import com.beans.common.security.users.model.Users;
import com.beans.leaveapp.employee.service.EmployeeService;
import com.beans.leaveapp.leavetransaction.model.LeaveTransaction;
import com.beans.leaveapp.leavetransaction.model.LeaveTransactionsDataModel;
import com.beans.leaveapp.leavetransaction.service.LeaveTransactionService;

public class LeaveTransactionManagementBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LeaveTransaction leaveTransaction = new LeaveTransaction();

	private LeaveTransactionService leaveTransactionService;
    private EmployeeService employeeService;
	private List<LeaveTransaction> list = new ArrayList<LeaveTransaction>();
	private LeaveTransactionsDataModel leaveTransactionDataModel;
	private String employeename = this.getEmployeename() ;
	private String leaveType = this.getLeaveType();
	private LeaveTransaction selectedLeaveTransaction = new LeaveTransaction();
	private LeaveTransaction newLeaveTransaction = new LeaveTransaction(); 
	// private List<AdminLeaveTransaction> adminLeaveTransactionList ;
	private List<String> employeeList;
	private List<String> leaveTypeList;
	boolean isInsert = false;
	List<LeaveTransactionReason>  leaveTransactionReasonList;
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

	public void setLeaveTransactionService(LeaveTransactionService leaveTransactionService) {
		this.leaveTransactionService = leaveTransactionService;
	}

	public LeaveTransactionsDataModel getLeaveTransactionDataModel() {
		if(leaveTransactionDataModel == null || isInsert == true){
			
		 System.out.println(getList().size()+" before data model");
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

	public void setLeaveTransactionDataModel(LeaveTransactionsDataModel leaveTransactionDataModel) {
		this.leaveTransactionDataModel = leaveTransactionDataModel;
	}

	public List<LeaveTransaction> getList() {
		list = leaveTransactionService.findAll();
		System.out.println(list.size());
		return list;
	}

	public void setList(List<LeaveTransaction> list) {
		this.list = list;
	}
	
	
	public void doSearchLeaveTransaction(){
		
			/*try{
				System.out.println("first step");
			if((this.getEmployeename() == null || getEmployeename().trim().equals("")) && (this.getLeaveType()== null || getLeaveType().trim().equals(""))) {
				this.adminLeaveTransactionList = null;
			    this.leaveTransactionDataModel = null;
			} else {
			
				adminLeaveTransactionList = this.getLeaveTransactionService().findByEmployeeIdAndfindByLeaveTypeId(getEmployeename(),getLeaveType() );
			   this.leaveTransactionDataModel = null;
			if(adminLeaveTransactionList != null){
				//  auditTrail.log(SystemAuditTrailActivity.ACCESSED, SystemAuditTrailLevel.INFO, actorUsers.getId(),actorUsers.getUsername(), actorUsers.getUsername()+" searching Entitlement of : "+getEmployeename());
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}*/
	}
	
	public void doUpdateLeaveTransaction(){
		
		
		this.getLeaveTransactionService().update(selectedLeaveTransaction);
		this.setInsert(true);
	}
	
	public void doDeletedLeaveTransaction(){
		int id = selectedLeaveTransaction.getId();
		System.out.println(id);
		this.getLeaveTransactionService().delete(id);
		this.setInsert(true);
	}
	
	public void doCreateLeaveTransaction(){
		
		System.out.println(newLeaveTransaction.getStartDateTime());
             getLeaveTransactionService().create(newLeaveTransaction);
             this.setInsert(true);
	}


	
	public void onRowSelect(SelectEvent event){
		this.setSelectedLeaveTransaction((LeaveTransaction)event.getObject());
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
		leaveTypeList = this.getLeaveTransactionService().findLeaveTypes();
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
	
	

	public List<LeaveTransactionReason> getLeaveTransactionReasonList() {
		leaveTransactionReasonList = Arrays.asList(LeaveTransactionReason.values());
		return leaveTransactionReasonList;
	}

	public void setLeaveTransactionReasonList(
			List<LeaveTransactionReason> leaveTransactionReasonList) {
		this.leaveTransactionReasonList = leaveTransactionReasonList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

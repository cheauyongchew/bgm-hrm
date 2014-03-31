
package com.beans.leaveapp.yearlyentitlement.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.beans.common.audit.service.AuditTrail;
import com.beans.common.audit.service.SystemAuditTrailActivity;
import com.beans.common.audit.service.SystemAuditTrailLevel;
import com.beans.common.security.users.model.Users;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.service.EmployeeService;
import com.beans.leaveapp.leavetype.model.LeaveType;
import com.beans.leaveapp.leavetype.service.LeaveTypeNotFound;
import com.beans.leaveapp.leavetype.service.LeaveTypeService;
import com.beans.leaveapp.yearlyentitlement.model.EmployeeEntitlement;
import com.beans.leaveapp.yearlyentitlement.model.EmployeeLeaveEntitlementDataModel;
import com.beans.leaveapp.yearlyentitlement.model.LeaveEntitlement;
import com.beans.leaveapp.yearlyentitlement.model.YearlyEntitleDataModel;
import com.beans.leaveapp.yearlyentitlement.model.YearlyEntitlement;
import com.beans.leaveapp.yearlyentitlement.service.YearlyEntitlementService;

public class YearlyEntitlementManagementBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private YearlyEntitlementService yearlyEntitlementService;
	private YearlyEntitleDataModel yearlyEntitlementDataModel;
	private YearlyEntitlement yearlyEntitlement = new YearlyEntitlement();
    private YearlyEntitlement selectedYearlyEntitlement = new YearlyEntitlement();
	private List<YearlyEntitlement> yearlyEntitlementList;
	private List<LeaveEntitlement> leaveEntitlementList;
	private boolean delete = false;
	private boolean InsertDelete = false;
	private LeaveEntitlement selectedLeaveEntitlement = new LeaveEntitlement();
	private LeaveEntitlement newLeaveEntitlement = new LeaveEntitlement();
	private EmployeeEntitlement employeeEntitlement = new EmployeeEntitlement();
	private List<String> employeeList;
	private List<String> leaveType;
	double availableBalance;
	private Employee employee;
	private EmployeeService employeeService;
	private LeaveTypeService leaveTypeService;
	private String employeeName = this.getEmployeeName();
	private String leaveTypeName = this.getLeaveTypeName();
	private String userName;
	List<YearlyEntitlement>  listOfYearlyEntitlement;
	List<EmployeeEntitlement> employeeEntitlementList;
	EmployeeLeaveEntitlementDataModel employeeLeaveEntitlementDataModel;
	private AuditTrail auditTrail;
	
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

	private Users actorUsers; 

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public LeaveEntitlement getNewLeaveEntitlement() {
		return newLeaveEntitlement;
	}

	public void setNewLeaveEntitlement(LeaveEntitlement newLeaveEntitlement) {
		this.newLeaveEntitlement = newLeaveEntitlement;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public YearlyEntitlementService getYearlyEntitlementService() {
		return yearlyEntitlementService;
	}

	public void setYearlyEntitlementService(
			YearlyEntitlementService yearlyEntitlementService) {
		this.yearlyEntitlementService = yearlyEntitlementService;
	}

	public YearlyEntitlement getYearlyEntitlement() {
		return yearlyEntitlement;
	}

	public void setYearlyEntitlement(YearlyEntitlement yearlyEntitlement) {
		this.yearlyEntitlement = yearlyEntitlement;
	}

	public List<LeaveEntitlement> getYearlyEntitlementList() throws Exception {
		if (leaveEntitlementList == null || InsertDelete == true) {

			leaveEntitlementList = (List<LeaveEntitlement>) getYearlyEntitlementService()
					.findLeave();

			System.out.println("entitlementListSize"
					+ leaveEntitlementList.size());

			for (LeaveEntitlement leaveEntitlementobj : leaveEntitlementList) {
				System.out.println(leaveEntitlementobj.getEmployeeName());
			}
		}
		return leaveEntitlementList;
	}

	public void setYearlyEntitlementList(
			List<YearlyEntitlement> yearlyEntitlementList) {
		this.yearlyEntitlementList = yearlyEntitlementList;
	}


	public YearlyEntitleDataModel getYearlyEntitlementDataModel()
			throws Exception {
		if (yearlyEntitlementDataModel == null || InsertDelete == true) {

			yearlyEntitlementDataModel = new YearlyEntitleDataModel(
					getYearlyEntitlementList());
		}
		return yearlyEntitlementDataModel;
	}

	public void setYearlyEntitlementDataModel(
			YearlyEntitleDataModel yearlyEntitlementDataModel) {
		this.yearlyEntitlementDataModel = yearlyEntitlementDataModel;
	}

	public YearlyEntitlement getSelectedYearlyEntitlement() {
		return selectedYearlyEntitlement;
	}

	public void setSelectedYearlyEntitlement(
			YearlyEntitlement selectedYearlyEntitlement) {
		this.selectedYearlyEntitlement = selectedYearlyEntitlement;
	}
	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public void doUpdateYearlyEntitlement() {
		try {
			System.out.println("Employee id:"
					+ selectedLeaveEntitlement.getEmployeeName());
			System.out.println("LeaveTypeId: "
					+ selectedLeaveEntitlement.getLeaveTypeId());
			System.out.println("Balance: "
					+ selectedLeaveEntitlement.getAvailableBalance());
			System.out.println("Entitlement: "
					+ selectedLeaveEntitlement.getEntitlement());
			System.out.println("id: " + selectedLeaveEntitlement.getId());

			selectedYearlyEntitlement.setId(selectedLeaveEntitlement.getId());
			// selectedYearlyEntitlement.setLeaveTypeId(selectedLeaveEntitlement.getLeaveType());
			selectedYearlyEntitlement
					.setAvailableBalance(selectedLeaveEntitlement
							.getAvailableBalance());
			selectedYearlyEntitlement.setEntitlement(selectedLeaveEntitlement
					.getEntitlement());
			getYearlyEntitlementService().update(selectedYearlyEntitlement);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Error", "Yearly Entitle With id: "
					+ selectedYearlyEntitlement.getId() + " not found!");

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void onRowSelect(SelectEvent event) {
		this.setSelectedLeaveEntitlement((LeaveEntitlement) event.getObject());
		System.out.println(this.getSelectedLeaveEntitlement().getId());
		FacesMessage msg = new FacesMessage("Yearly Entitlement Seleted");

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void doDeleteYearlyEntitlement() {
		try {
			int id = selectedLeaveEntitlement.getId();
			this.getYearlyEntitlementService().delete(id);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Error", "Yearly Entitle With id: "
					+ selectedYearlyEntitlement.getId() + " not found!");

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		setInsertDelete(true);
	}

	public void doCreateYearlyEntitlement() {
	
		this.getYearlyEntitlementService().create(newLeaveEntitlement);
		setInsertDelete(true);
		 auditTrail.log(SystemAuditTrailActivity.CREATED, SystemAuditTrailLevel.INFO, this.getActorUsers().getId(),getActorUsers().getUsername(), getActorUsers().getUsername()+"has created yearly Entitlement using EmployeeName :"+newLeaveEntitlement.getEmployeeName());
	
	}

	public void employeeList() {
	}
	
	public boolean isInsertDelete() {
		return InsertDelete;
	}

	public void setInsertDelete(boolean insertDelete) {
		InsertDelete = insertDelete;
	}

	public LeaveEntitlement getSelectedLeaveEntitlement() {
		return selectedLeaveEntitlement;
	}

	public void setSelectedLeaveEntitlement(
			LeaveEntitlement selectedLeaveEntitlement) {
		this.selectedLeaveEntitlement = selectedLeaveEntitlement;
	}

	public List<String> getEmployeeList() {
		List<String> employeeNames = (List<String>) getYearlyEntitlementService()
				.employeeNames();
		return employeeNames;

	}

	public void setEmployeeList(List<String> employeeList) {
		this.employeeList = employeeList;
	}

	public List<String> getLeaveType() {
		leaveType = this.getYearlyEntitlementService().findLeaveTypes();
		return leaveType;
	}

	public void setLeaveType(List<String> leaveType) {
		this.leaveType = leaveType;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	

	public List<EmployeeEntitlement> leaveEntitlement() throws Exception {
		try {
			int employeeId = this.employee.getId();
			employeeEntitlementList = yearlyEntitlementService
					.findByEmployeeId(employeeId);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return employeeEntitlementList;
	}

	public EmployeeLeaveEntitlementDataModel getEmployeeLeaveEntitlementDataModel() throws Exception {
		if (employeeLeaveEntitlementDataModel == null) {
			try {
				employeeLeaveEntitlementDataModel = new EmployeeLeaveEntitlementDataModel(
						leaveEntitlement());

			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
		return employeeLeaveEntitlementDataModel;
	}

	public void setEmployeeLeaveEntitlementDataModel(
			EmployeeLeaveEntitlementDataModel employeeLeaveEntitlementDataModel) {
		this.employeeLeaveEntitlementDataModel = employeeLeaveEntitlementDataModel;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	 
	public void search() {
		try{
		if((getEmployeeName() == null || getEmployeeName().trim().equals("")) && (getLeaveTypeName() == null || getLeaveTypeName().trim().equals(""))) {
			this.leaveEntitlementList = null;
		    this.yearlyEntitlementDataModel = null;
		} else {
		
		   leaveEntitlementList = this.getYearlyEntitlementService().findByEmployeeIdAndfindByLeaveTypeId(getEmployeeName(),getLeaveTypeName() );
		   this.yearlyEntitlementDataModel = null;
		
		   auditTrail.log(SystemAuditTrailActivity.ACCESSED, SystemAuditTrailLevel.INFO, actorUsers.getId(),actorUsers.getUsername(), actorUsers.getUsername()+"searching Entitlement using : "+getEmployeeName()+" "+getLeaveType());
		}
		
	}
	
	catch(Exception e){
		e.printStackTrace(); 
	}
	}

	private Object getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public LeaveTypeService getLeaveTypeService() {
		return leaveTypeService;
	}

	public void setLeaveTypeService(LeaveTypeService leaveTypeService) {
		this.leaveTypeService = leaveTypeService;
	}

	public Users getActorUsers() {
		return actorUsers;
	}

	public void setActorUsers(Users actorUsers) {
		this.actorUsers = actorUsers;
	}

}

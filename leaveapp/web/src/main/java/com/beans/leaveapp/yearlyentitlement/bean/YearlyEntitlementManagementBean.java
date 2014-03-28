package com.beans.leaveapp.yearlyentitlement.bean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.beans.common.security.users.service.UsersNotFound;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.service.EmployeeNotFound;
import com.beans.leaveapp.employee.service.EmployeeService;
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
	private String employeeName;
	private String userName;
	List<YearlyEntitlement>  listOfYearlyEntitlement;
	List<EmployeeEntitlement> employeeEntitlementList;
	EmployeeLeaveEntitlementDataModel employeeLeaveEntitlementDataModel;

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

	/*
	 * public List<YearlyEntitlement> getYearlyEntitlementList() throws
	 * Exception { if(yearlyEntitlementList == null || delete == true){
	 * yearlyEntitlementList = getYearlyEntitlementService().findAll();
	 * 
	 * System.out.println("entitlementListSize" +yearlyEntitlementList.size());
	 * } return yearlyEntitlementList; }
	 */
	


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
			FacesMessage msg = new FacesMessage("Error", "Leave Type With id: "
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
	
	public void search(){
		int id = getEmployeeService().findByEmployee(employeeName).getId();
		// List<YearlyEntitlement> list = yearlyEntitlementService.findByEmployeeId(id);
	}
	

}

package com.beans.leaveapp.employeeprofile.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.beans.leaveapp.address.model.Address;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.service.EmployeeNotFound;
import com.beans.leaveapp.employee.service.EmployeeService;
import com.beans.leaveapp.employeeprofile.model.AddressDataModel;
import com.beans.leaveapp.employeeprofile.model.EmployeeProfileDataModel;


public class EmployeeProfileManagementBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private  EmployeeService employeeService= getEmployeeService();
	private List<Employee> employeeList;
	private EmployeeProfileDataModel employeeProfileDataModel;
	private Employee newEmployee = new Employee();
	private Employee selectedEmployee = new Employee();
	private boolean insertDelete = false;
	private List<Employee> searchEmployee;
	private AddressDataModel newAddressDataModel;
	private Address selectedAddress = new Address();
	
	public List<Employee> getSearchEmployee() {
		
		return searchEmployee;
	}

	public void setSearchEmployee(List<Employee> searchEmployee) {
		System.out.println("in set method");
		this.searchEmployee = searchEmployee;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public List<Employee> getEmployeeList() {
		if(employeeList == null || insertDelete == true) {
	
			employeeList = employeeService.findAll();			
		}		
		
		return employeeList;
	}
	
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	public EmployeeProfileDataModel getEmployeeDataModel() {
		if(employeeProfileDataModel == null || insertDelete == true) {
			employeeProfileDataModel = new EmployeeProfileDataModel(getEmployeeList());
		}
		return employeeProfileDataModel;
	}
	
	public void setEmployeeProfileDataModel(EmployeeProfileDataModel employeeProfileDataModel) {
		this.employeeProfileDataModel = employeeProfileDataModel;
	}
	
	public AddressDataModel getNewAddressDataModel() {
		if(newAddressDataModel == null || insertDelete == true) {
			newAddressDataModel = new AddressDataModel(newEmployee.getAddresses());
		}
		
		return newAddressDataModel;
	}
	public void setNewAddressDataModel(AddressDataModel newAddressDataModel) {
		this.newAddressDataModel = newAddressDataModel;
	}
	
	public Employee getNewEmployee() {
		return newEmployee;
	}
	
	public void setNewEmployee(Employee newEmployee) {
		this.newEmployee = newEmployee;
	}
	
	public void doCreateEmployee() {
		newEmployee.setDeleted(false);
		newEmployee.setResigned(false);
		getEmployeeService().create(newEmployee);
		setInsertDelete(true);
	}
	
	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}
	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}
	
	public Address getSelectedAddress() {
		return selectedAddress;
	}
	public void setSelectedAddress(Address selectedAddress) {
		this.selectedAddress = selectedAddress;
	}
	
	
	
	public void doUpdateEmployee() {
		try {
			System.out.println("New name:" + selectedEmployee.getName());
			System.out.println("ID: " + selectedEmployee.getId());
			getEmployeeService().update(selectedEmployee);
		} catch(EmployeeNotFound e) {
			FacesMessage msg = new FacesMessage("Error", "Leave Type With id: " + selectedEmployee.getId() + " not found!");  
			  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
		}
	}
	
	public void onRowSelect(SelectEvent event) {  
		setSelectedEmployee((Employee) event.getObject());
        FacesMessage msg = new FacesMessage("Leave Type Selected", selectedEmployee.getName());  
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void doDeleteEmployee() {
		try {
			getEmployeeService().delete(selectedEmployee.getId());
		} catch(EmployeeNotFound e) {
			FacesMessage msg = new FacesMessage("Error", "Leave Type With id: " + selectedEmployee.getId() + " not found!");  
			  
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
	
	
} 


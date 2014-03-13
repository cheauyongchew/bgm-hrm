package com.beans.leaveapp.employeeprofile.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.beans.common.security.users.model.Users;
import com.beans.leaveapp.address.model.Address;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.service.EmployeeNotFound;
import com.beans.leaveapp.employee.service.EmployeeService;
import com.beans.leaveapp.employeeprofile.model.AddressDataModel;
import com.beans.leaveapp.employeeprofile.model.EmployeeProfileDataModel;


public class EmployeeProfileManagementBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService= getEmployeeService();
	private List<Employee> employeeList;
	private EmployeeProfileDataModel employeeProfileDataModel;
	private Employee newEmployee = new Employee();
	private Employee selectedEmployee = new Employee();
	private boolean insertDelete = false;
	private boolean insertDeleteAddress = false;
	private List<Employee> searchEmployee;
	private AddressDataModel newAddressDataModel;
	private Address selectedAddress = new Address();
	private String page = "details";
	private int selectedDepartment;
	private int selectedEmployeeType;
	private int selectedEmployeeGrade;
	private Users users = new Users();
	private boolean isRenderAddress = false;
	private String addressOperation = "Create";
	private String selectedAddressType = "Permanent";
	private boolean isEmployeeCreation = false;
	
	
	public boolean isEmployeeCreation() {
		return isEmployeeCreation;
	}
	public void setEmployeeCreation(boolean isEmployeeCreation) {
		this.isEmployeeCreation = isEmployeeCreation;
	}
	
	
	public String getSelectedAddressType() {
		return selectedAddressType;
	}
	public void setSelectedAddressType(String selectedAddressType) {
		this.selectedAddressType = selectedAddressType;
	}
	
	public void setAddressOperation(boolean isRenderAddress, String addressOperation) {
		setRenderAddress(isRenderAddress);
		this.addressOperation = addressOperation;
		this.selectedAddress = new Address();
	}
	public String getAddressOperation() {
		return addressOperation;
	}
	
	public boolean isRenderAddress() {
		return isRenderAddress;
	}
	
	public void setRenderAddress(boolean isRenderAddress) {
		this.isRenderAddress = isRenderAddress;
	}
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	public List<Employee> getSearchEmployee() {
		
		return searchEmployee;
	}

	public void setSearchEmployee(List<Employee> searchEmployee) {
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
		if(newAddressDataModel == null || insertDeleteAddress == true) {
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
		getEmployeeService().createEmployee(newEmployee, selectedEmployeeGrade, selectedEmployeeType, selectedDepartment, users);
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
		setEmployeeCreation(false);
        FacesMessage msg = new FacesMessage("Leave Type Selected", selectedEmployee.getName());  
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    } 
	
	public void onAddressRowSelect(SelectEvent event) {  
		setAddressOperation(true, "Update");
		setSelectedAddress((Address) event.getObject());		 
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
	public int getSelectedDepartment() {
		return selectedDepartment;
	}
	public void setSelectedDepartment(int selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
	}
	public int getSelectedEmployeeType() {
		return selectedEmployeeType;
	}
	public void setSelectedEmployeeType(int selectedEmployeeType) {
		this.selectedEmployeeType = selectedEmployeeType;
	}
	public int getSelectedEmployeeGrade() {
		return selectedEmployeeGrade;
	}
	public void setSelectedEmployeeGrade(int selectedEmployeeGrade) {
		this.selectedEmployeeGrade = selectedEmployeeGrade;
	}
	
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	public void addAddressToNewEmployee() {
		int index = newEmployee.getAddresses().size();
		selectedAddress.setId(index);
		selectedAddress.setAddressType(selectedAddressType);
		selectedAddress.setDeleted(false);
		Address addressToBeAdded = selectedAddress;
		newEmployee.getAddresses().add(addressToBeAdded);
		
		setAddressOperation(false, "Create");
		setInsertDeleteAddress(true);
	}
	
	
	public void setInsertDeleteAddress(boolean insertDeleteAddress) {
		this.insertDeleteAddress = insertDeleteAddress;
	}
	
} 


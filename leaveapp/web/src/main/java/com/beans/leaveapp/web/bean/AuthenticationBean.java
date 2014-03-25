package com.beans.leaveapp.web.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.beans.common.security.users.model.Users;
import com.beans.common.security.users.service.UsersNotFound;
import com.beans.common.security.users.service.UsersService;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.service.EmployeeNotFound;
import com.beans.leaveapp.employee.service.EmployeeService;


public class AuthenticationBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService;
	private UsersService usersService;
	private Employee employee;
	private String username;
	private Users users;
	private HashMap<String, Boolean> accessRightsMap = new HashMap<String, Boolean>();
	
	public String doLogin() throws IOException, ServletException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher requestDispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
		requestDispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			setUsername(auth.getName());
			initEmployee();
			populateAccessRightsMap();
		}
		FacesContext.getCurrentInstance().responseComplete();
		
		return null;
		
	}
	
	public String doLogout() throws IOException, ServletException{
		SecurityContextHolder.clearContext();
		return "/login.xhtml";
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	private void initEmployee() {
		try {
			employee = getEmployeeService().findByUsername(getUsername());
			setUsers(employee.getUsers());
		} catch(EmployeeNotFound e) {
			System.out.println("Employee not found for " + getUsername());
			initUsers();
		}
	}
	
	private void initUsers() {
		try {
			users = getUsersService().findByUsername(getUsername());
		} catch(UsersNotFound e) {
			e.printStackTrace();
		}
	}
	
	private void populateAccessRightsMap() {
		
	}
	
	public Boolean hasAccess(String key) {
		if (key != null) {
			if (accessRightsMap.containsKey(key)) {
				return accessRightsMap.get(key);

			}
		}		
		return false;
	}
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public UsersService getUsersService() {
		return usersService;
	}
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
}
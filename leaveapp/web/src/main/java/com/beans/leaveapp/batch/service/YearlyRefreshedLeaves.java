package com.beans.leaveapp.batch.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.service.EmployeeService;
import com.beans.leaveapp.jbpm6.util.ApplicationContextProvider;
import com.beans.leaveapp.yearlyentitlement.model.YearlyEntitlement;
import com.beans.leaveapp.yearlyentitlement.service.YearlyEntitlementService;

@Service
public class YearlyRefreshedLeaves implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
	EmployeeService employeeService = (EmployeeService) applicationContext.getBean("employeeService");
	YearlyEntitlementService yearlyEntitlementService = (YearlyEntitlementService) applicationContext.getBean("yearlyEntitlementService");

	public void YearlyrefreshedLeaves() throws Exception {
		List<Employee> employeeList = employeeService.findAll();
		for(Employee employee : employeeList){
		List<YearlyEntitlement> yearlyEntitlementList = yearlyEntitlementService.findByEmployeeIdPermForRemainingLeaves(employee.getId());
		for(YearlyEntitlement yearlyEntitlement : yearlyEntitlementList){
			if(yearlyEntitlement.getLeaveType().getId() == 6){
				double entitlement = 14.0;
				yearlyEntitlement.setEntitlement(entitlement);
				yearlyEntitlementService.update(yearlyEntitlement);
			}
			else if(yearlyEntitlement.getLeaveType().getId() == 7){
				double entitlement = 3.0;
				yearlyEntitlement.setEntitlement(entitlement);
				yearlyEntitlementService.update(yearlyEntitlement);
			}
			else if(yearlyEntitlement.getLeaveType().getId() == 8){
				double entitlement = 3.0;
				yearlyEntitlement.setEntitlement(entitlement);
				yearlyEntitlementService.update(yearlyEntitlement);
			}
			else if(yearlyEntitlement.getLeaveType().getId() == 9){
				double entitlement = 60.0;
				yearlyEntitlement.setEntitlement(entitlement);
				yearlyEntitlementService.update(yearlyEntitlement);
			}
			else if(yearlyEntitlement.getLeaveType().getId() == 10){
				double entitlement = 3.0;
				yearlyEntitlement.setEntitlement(entitlement);
				yearlyEntitlementService.update(yearlyEntitlement);
			}
			else if(yearlyEntitlement.getLeaveType().getId() == 11){
				double entitlement = 30.0;
				yearlyEntitlement.setEntitlement(entitlement);
				yearlyEntitlementService.update(yearlyEntitlement);
			}
			else if(yearlyEntitlement.getLeaveType().getId() == 12){
				double entitlement = 30.0;
				yearlyEntitlement.setEntitlement(entitlement);
				yearlyEntitlementService.update(yearlyEntitlement);
			}
			else if(yearlyEntitlement.getLeaveType().getId() == 13){
				double entitlement = 30.0;
				yearlyEntitlement.setEntitlement(entitlement);
				yearlyEntitlementService.update(yearlyEntitlement);
			}
		}			
		}		
	}
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public YearlyEntitlementService getyearlyEntitlementService() {
		return yearlyEntitlementService;
	}

	public void setYearEntitlementService(
			YearlyEntitlementService yearlyEntitlementService) {
		this.yearlyEntitlementService = yearlyEntitlementService;
	}
	
	
}

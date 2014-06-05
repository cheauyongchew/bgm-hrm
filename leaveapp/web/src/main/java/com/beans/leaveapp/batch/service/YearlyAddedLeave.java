package com.beans.leaveapp.batch.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.service.EmployeeService;
import com.beans.leaveapp.jbpm6.util.ApplicationContextProvider;
import com.beans.leaveapp.yearlyentitlement.model.YearlyEntitlement;
import com.beans.leaveapp.yearlyentitlement.service.YearlyEntitlementService;

@Service
public class YearlyAddedLeave implements Serializable{

	private static final long serialVersionUID = 1L;
	
	ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
	EmployeeService employeeService = (EmployeeService) applicationContext.getBean("employeeService");
	YearlyEntitlementService yearlyEntitlementService = (YearlyEntitlementService) applicationContext.getBean("yearlyEntitlementService");
	
	public void YearlyAddedLeaves() throws Exception {		
	
		int currentDateDay;
		int currentDateMonth;
		int currentDateYear;
		int joinDateday;
		int joinDateMonth;
		int joinDateYear;
		int joinDateYear1;
		int joinDateYear2;
		int joinDateYear3;
		int joinDateYear4;
		
		
		Date currentDate = new Date();		
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		currentDateDay = cal.get(Calendar.DAY_OF_MONTH);
		currentDateMonth = cal.get(Calendar.MONTH);
		currentDateYear = cal.get(Calendar.YEAR);		
				
		List<Employee> employeeList = employeeService.findByEmployeeTypePerm();
		for(Employee employee : employeeList){
			
			Date joinDate;
			joinDate = employee.getJoinDate();
			cal.setTime(joinDate);
			joinDateday = cal.get(Calendar.DAY_OF_MONTH);
			joinDateMonth = cal.get(Calendar.MONTH);
			joinDateYear = cal.get(Calendar.YEAR);
			
			joinDateYear1 = joinDateYear+1;
			joinDateYear2 = joinDateYear1+1;
			joinDateYear3 = joinDateYear2+1;
			joinDateYear4 = joinDateYear3+1;
			
			YearlyEntitlement yearlyEntitleMent = yearlyEntitlementService.findByEmployeeIdPerm(employee.getId());
			
			if(yearlyEntitleMent != null){
			if(yearlyEntitleMent.getEntitlement() < 16){
				if(currentDateDay == joinDateday && currentDateMonth == joinDateMonth && currentDateYear == joinDateYear1){
					double entitlement =yearlyEntitleMent.getEntitlement();
					double addedEntitlement = entitlement+1.00;
					System.out.println(addedEntitlement);
					yearlyEntitleMent.setEntitlement(addedEntitlement);
					yearlyEntitlementService.update(yearlyEntitleMent);
				}else if(currentDateDay == joinDateday && currentDateMonth == joinDateMonth && currentDateYear == joinDateYear2){
					double entitlement =yearlyEntitleMent.getEntitlement();
					double addedEntitlement = entitlement+1.00;
					System.out.println(addedEntitlement);
					yearlyEntitleMent.setEntitlement(addedEntitlement);
					yearlyEntitlementService.update(yearlyEntitleMent);
				}else if(currentDateDay == joinDateday && currentDateMonth == joinDateMonth && currentDateYear == joinDateYear3){
					double entitlement =yearlyEntitleMent.getEntitlement();
					double addedEntitlement = entitlement+1.00;
					System.out.println(addedEntitlement);
					yearlyEntitleMent.setEntitlement(addedEntitlement);
					yearlyEntitlementService.update(yearlyEntitleMent);
				}else if(currentDateDay == joinDateday && currentDateMonth == joinDateMonth && currentDateYear == joinDateYear4){
					double entitlement =yearlyEntitleMent.getEntitlement();
					double addedEntitlement = entitlement+1.00;
					System.out.println(addedEntitlement);
					yearlyEntitleMent.setEntitlement(addedEntitlement);
					yearlyEntitlementService.update(yearlyEntitleMent);
				} else if(joinDateday == 29 && joinDateMonth == 1){
					double entitlement =yearlyEntitleMent.getEntitlement();
					double addedEntitlement = entitlement+1.00;
					System.out.println(addedEntitlement);
					yearlyEntitleMent.setEntitlement(addedEntitlement);
					yearlyEntitlementService.update(yearlyEntitleMent);
				}				
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

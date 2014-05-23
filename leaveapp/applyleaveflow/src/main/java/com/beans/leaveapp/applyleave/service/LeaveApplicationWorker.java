package com.beans.leaveapp.applyleave.service;

import org.springframework.context.ApplicationContext;

import com.beans.leaveapp.jbpm6.util.ApplicationContextProvider;
import com.beans.leaveapp.leavetransaction.model.LeaveTransaction;
import com.beans.leaveapp.yearlyentitlement.service.YearlyEntitlementService;

public class LeaveApplicationWorker {
	
	public static void sendingIntimationEmail(LeaveTransaction leaveTransaction,String assignedRoleInLeaveFlow)
	{
		System.out.println("Data coming from process is assignedRoleInLeaveFlow :"+assignedRoleInLeaveFlow);
		if("ROLE_TEAMLEAD".equalsIgnoreCase(assignedRoleInLeaveFlow))
			System.out.println("Hurray... Team Lead is going to take leave.. Send mail to Operational Director..");
		else
			System.out.println("Hurray... Employee is going to take leave.. Send mail to Team Lead..");
	}
	
	public static void updateLeaveBalanceAfterApproval(LeaveTransaction leaveTransaction,Boolean isOperDirApproved){
		System.out.println("Data coming from process is  leaveTransaction : "+leaveTransaction+" and isDirApproved :"+isOperDirApproved);
		if(leaveTransaction!=null && isOperDirApproved==true)
		{
			ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
			YearlyEntitlementService yearlyEntitlementService = (YearlyEntitlementService) applicationContext.getBean("yearlyEntitlementService");
			yearlyEntitlementService.updateLeaveBalanceAfterApproval(leaveTransaction.getEmployee().getId(), leaveTransaction.getLeaveType().getId(), leaveTransaction.getNumberOfDays());
		
		}
	}

}

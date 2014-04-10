package com.beans.leaveapp.yearlyentitlement.service;

import java.util.List;

import com.beans.leaveapp.yearlyentitlement.model.EmployeeEntitlement;
import com.beans.leaveapp.yearlyentitlement.model.LeaveEntitlement;
import com.beans.leaveapp.yearlyentitlement.model.YearlyEntitlement;

public interface YearlyEntitlementService {

	public List<YearlyEntitlement> findAll() throws Exception;

	public YearlyEntitlement update(YearlyEntitlement selectedYearlyEntitlement) throws Exception;

	public YearlyEntitlement delete(int id);

	public YearlyEntitlement create(LeaveEntitlement leaveEntitlement);
	
	public List<String> employeeNames();
	
	public List<LeaveEntitlement> findLeave();

	public List<String> findLeaveTypes();
	
	public List<EmployeeEntitlement> findByEmployeeId(int x);
	
	public List<LeaveEntitlement> findByEmployee(int x);
	
	public List<LeaveEntitlement> findBySearchLeave(int id);
	
	public List<LeaveEntitlement> findByEmployeeAndfindByLeaveType(String x,String y);
	
	public YearlyEntitlement findByEmployeeAndLeaveType(int employeeId, int leaveTypeId) throws YearlyEntitlementNotFound;
	//TODO Change method name after Pradeep removed his
	public List<YearlyEntitlement> findYearlyEntitlementListByEmployee(int employeeId);
	
	public YearlyEntitlement findOne(int yearlyEntitlementId) throws YearlyEntitlementNotFound;
	
}

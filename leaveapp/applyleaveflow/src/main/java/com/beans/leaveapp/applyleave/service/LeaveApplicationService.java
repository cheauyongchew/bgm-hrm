package com.beans.leaveapp.applyleave.service;

import com.beans.common.security.role.service.RoleNotFound;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.leavetransaction.model.LeaveTransaction;
import com.beans.leaveapp.yearlyentitlement.model.YearlyEntitlement;

public interface LeaveApplicationService {
	public void submitLeave(Employee employee, YearlyEntitlement yearlyEntitlement, LeaveTransaction leaveTransaction) throws RoleNotFound, LeaveApplicationException;
	
}

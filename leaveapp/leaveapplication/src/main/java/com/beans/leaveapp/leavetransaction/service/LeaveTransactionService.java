package com.beans.leaveapp.leavetransaction.service;

import java.util.List;

import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.leavetransaction.model.LeaveTransaction;
import com.beans.leaveapp.leavetype.model.LeaveType;

public interface LeaveTransactionService {

	public List<LeaveTransaction> findAll();
	
	public List<String> findEmployeeNames();
	
	public List<String> findLeaveTypes(String name);
	
	public int create(LeaveTransaction adminLeaveTransaction);
	
	public void update(LeaveTransaction adminLeaveTransaction);
	
	public void delete(int id);
	
	public Employee findByEmployee(String name);
	
	public LeaveType findByLeaveType(String name);

	public List<LeaveTransaction> findByEmployeeORfindByLeaveTypeORBoth(
			String employeename, String leaveType);
	
	
    
}

package com.beans.leaveapp.leavetransaction.service;

import java.util.List;

import com.beans.leaveapp.leavetransaction.model.AdminLeaveTransaction;
import com.beans.leaveapp.leavetransaction.model.LeaveTransaction;

public interface LeaveTransactionService {

	public List<LeaveTransaction> findAll();
	
	public List<AdminLeaveTransaction> findLeaveTransactions();
	
	public List<String> findEmployeeNames();
	
	public List<String> findLeaveTypes();
	
	public int create(AdminLeaveTransaction adminLeaveTransaction);
	
	public void update(AdminLeaveTransaction adminLeaveTransaction);
	
	public void delete(int id);
	
	public List<AdminLeaveTransaction> findByEmployeeIdAndfindByLeaveTypeId(String empName,String leaveType);

    
}

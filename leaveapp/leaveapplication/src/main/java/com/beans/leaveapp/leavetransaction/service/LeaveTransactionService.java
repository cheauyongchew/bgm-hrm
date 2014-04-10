package com.beans.leaveapp.leavetransaction.service;

import java.util.List;

import com.beans.leaveapp.leavetransaction.model.LeaveTransaction;

public interface LeaveTransactionService {

	public List<LeaveTransaction> findAll();
	
	public List<String> findEmployeeNames();
	
	public List<String> findLeaveTypes();
	
	public int create(LeaveTransaction adminLeaveTransaction);
	
	public void update(LeaveTransaction adminLeaveTransaction);
	
	public void delete(int id);
	
	
 
    
}

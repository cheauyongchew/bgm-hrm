package com.beans.leaveapp.leavetransaction.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.repository.EmployeeRepository;
import com.beans.leaveapp.leavetransaction.model.LeaveTransaction;
import com.beans.leaveapp.leavetransaction.repository.LeaveTransactionRepository;
import com.beans.leaveapp.leavetype.model.LeaveType;
import com.beans.leaveapp.leavetype.repository.LeaveTypeRepository;

public class LeaveTransactionServiceImpl implements LeaveTransactionService {

	@Resource
	LeaveTransactionRepository leaveTransactionRepository;

	@Resource
	LeaveTypeRepository leaveTypeRepository;

	@Resource
	EmployeeRepository employeeRepository;

	@Override
	public List<LeaveTransaction> findAll() {
		List<LeaveTransaction> ls = leaveTransactionRepository.findAll(0);

		System.out.println(ls.size());
		return ls;
	}


	@Override
	public List<String> findEmployeeNames() {
		List<String> employeeNames = (List<String>) employeeRepository
				.findByNames();
		return employeeNames;
	}

	@Override
	public List<String> findLeaveTypes() {
		List<String> leaveTypeNames = (List<String>) this.leaveTypeRepository
				.findNamesList();
		return leaveTypeNames;
	}

	@Override
	public int create(LeaveTransaction adminLeaveTransaction) {

		try {
			LeaveTransaction leaveTransaction = new LeaveTransaction();
			LeaveType leaveType = new LeaveType();
			Employee employee = new Employee();
			if (adminLeaveTransaction != null
					&& !(adminLeaveTransaction.equals(""))) {
				int employeeId = 0, leaveTypeId = 0;
				adminLeaveTransaction.setApplicationDate(new Date());
				adminLeaveTransaction.setReason("Pending");
				leaveTransaction = leaveTransactionRepository
						.save(adminLeaveTransaction);
				return leaveTransaction.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void update(LeaveTransaction adminLeaveTransaction) {
		try {
			LeaveTransaction leaveTransaction = new LeaveTransaction();
			LeaveTransaction leaveTransactionUpdated = leaveTransactionRepository
					.findOne(adminLeaveTransaction.getId());
			if (adminLeaveTransaction != null) {

				Employee employee = new Employee();
				LeaveType leaveType = new LeaveType();
				leaveTransactionRepository.save(adminLeaveTransaction);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	@Override
	public void delete(int id) {
		try{
		LeaveTransaction leaveTransaction = leaveTransactionRepository
				.findOne(id);
		System.out.println(leaveTransaction.isDeleted());
		leaveTransaction.setDeleted(true);
		LeaveTransaction l = leaveTransactionRepository.save(leaveTransaction);
		System.out.println(leaveTransaction.isDeleted());
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public LeaveTransaction insertFromWorkflow(LeaveTransaction leaveTransaction) {
		
		return leaveTransactionRepository.save(leaveTransaction);
	}

	

}


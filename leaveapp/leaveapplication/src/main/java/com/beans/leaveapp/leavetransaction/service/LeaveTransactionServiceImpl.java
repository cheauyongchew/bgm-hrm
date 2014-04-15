package com.beans.leaveapp.leavetransaction.service;

import java.util.Date;
import java.util.LinkedList;
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
		List<String> employees = (List<String>) employeeRepository.findByNames();
				
		return employees;
	}

	@Override
	public List<String> findLeaveTypes(String name) {
		
		List<String> leaveTypeNames  = new LinkedList<String>();
		
		try{
			if(employeeRepository.findByEmployeeTypeId(name) != null){
		leaveTypeNames = leaveTypeRepository.findByLeaveTypes(this.employeeRepository.findByEmployeeTypeId(name));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return leaveTypeNames;
	}

	@Override
	public int create(LeaveTransaction leaveTransaction) {

		try {
			if (leaveTransaction != null) {
				leaveTransaction.setApplicationDate(new Date());
				LeaveTransaction leaveTransactionObj = leaveTransactionRepository.save(leaveTransaction);
				return leaveTransactionObj.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void update(LeaveTransaction leaveTransaction) {
		try {
			if (leaveTransaction != null) {
				leaveTransactionRepository.save(leaveTransaction);
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
	public Employee findByEmployee(String name) {
		Employee employee = employeeRepository.findByName(name);
		return employee;
	}


	@Override
	public LeaveType findByLeaveType(String name) {
		
		LeaveType leaveType = leaveTypeRepository.findByName(name);
		return leaveType;
	}


	@Override
	public List<LeaveTransaction> findByEmployeeORfindByLeaveTypeORBoth(
			String employeeName, String leaveType) {
		
		String name = "%"+ employeeName.trim()+"%";
		String leaveName = "%"+ leaveType.trim()+"%";
		
		List<LeaveTransaction> leaveTransaction = null;
		 if(!employeeName.trim().equals("") && (!leaveType.trim().equals(""))){
			 leaveTransaction =	leaveTransactionRepository.findByEmployeeAndLeaveTypeLike(name,leaveName);
		    	   	
		    	
		    }else if(!employeeName.trim().equals("")){
		    	leaveTransaction = leaveTransactionRepository.findByEmployeeLike(name);
		    }else if(leaveType.trim() != null || !leaveType.trim().equals(""))
		    {
		    	leaveTransaction = leaveTransactionRepository.findByLeaveTypeLike(leaveName);
		    }
			return leaveTransaction;
		// leaveTransaction = leaveTransactionRepository.findByEmployeeOrLeaveTypeLike(employeename.trim(), leaveType.trim());
		
	}

	@Override
	public LeaveTransaction insertFromWorkflow(LeaveTransaction leaveTransaction) {
		
		return leaveTransactionRepository.save(leaveTransaction);
	}

	
}


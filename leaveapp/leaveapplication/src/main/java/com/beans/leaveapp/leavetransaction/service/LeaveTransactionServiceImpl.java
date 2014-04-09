package com.beans.leaveapp.leavetransaction.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.repository.EmployeeRepository;
import com.beans.leaveapp.leavetransaction.model.AdminLeaveTransaction;
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
	public List<AdminLeaveTransaction> findLeaveTransactions() {
try{
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		List<AdminLeaveTransaction> adminLeaveTransactionList = new LinkedList<AdminLeaveTransaction>();
		List<LeaveTransaction> leaveTransactionList = findAll();
		for (LeaveTransaction leaveTransaction : leaveTransactionList) {
			int id = leaveTransaction.getId();
			Date applicationDate = (Date) leaveTransaction.getApplicationDate();
			String leaveTypeName = null, employeeName = null;

			if (leaveTransaction.getLeaveTypeId().getId() != 0) {
				int leaveTypeId = leaveTransaction.getLeaveTypeId().getId();
				LeaveType leaveType = leaveTypeRepository.findOne(leaveTransaction.getId());
			}
			if (leaveTransaction.getEmployeeId().getId()!= 0) {
				System.out.println(leaveTransaction.getEmployeeId().getId());
				List<LeaveTransaction> l = leaveTransactionRepository.findByEmployeeId(leaveTransaction.getEmployeeId().getId());
				for(LeaveTransaction ll :l){
				System.out.println(ll.getName());
				}
				int employeeId = leaveTransaction.getEmployeeId().getId();
				Employee employee = employeeRepository.findOne(employeeId);
				
			}
			java.util.Date endDate = (java.util.Date) leaveTransaction.getEndDateTime();
			java.util.Date startDate = (java.util.Date) leaveTransaction.getStartDateTime();
			
			String reason = leaveTransaction.getReason();
			Double days = leaveTransaction.getNumberOfDays();
			Double hours = leaveTransaction.getNumberOfHours();

			adminLeaveTransactionList.add(new AdminLeaveTransaction(id,
					employeeName, leaveTypeName, applicationDate, startDate,
					endDate, reason, days, hours));
		}
			return adminLeaveTransactionList;
}catch(Exception e){
	e.printStackTrace();
}
return null;
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
	public int create(AdminLeaveTransaction adminLeaveTransaction) {
		
		try{
		LeaveTransaction leaveTransaction = new LeaveTransaction();
		LeaveType leaveType = new LeaveType();
		Employee employee = new Employee();
		if (adminLeaveTransaction != null
				&& !(adminLeaveTransaction.equals(""))) {
			int employeeId = 0, leaveTypeId = 0;
			if (adminLeaveTransaction.getEmployeeName() != null) {
				 employee = employeeRepository
						.findByName(adminLeaveTransaction.getEmployeeName());
				employeeId = employee.getId();
			}
			if (adminLeaveTransaction.getLeaveType() != null) {
				 leaveType = leaveTypeRepository
						.findByName(adminLeaveTransaction.getLeaveType());
				leaveTypeId = leaveType.getId();

			}
			leaveTransaction.setReason(adminLeaveTransaction.getReason());
			leaveTransaction.setApplicationDate(new Date());
			leaveTransaction.setLeaveTypeId(leaveType);
			leaveTransaction.setEmployeeId(employee);
			leaveTransaction.setApplicationDate(new Date());
			leaveTransaction.setStartDateTime(adminLeaveTransaction
					.getStartTime());
			leaveTransaction.setEndDateTime(adminLeaveTransaction.getEndTime());
			// Timestamp endTime = adminLeaveTransaction.getEndTime();
			leaveTransaction.setNumberOfDays(adminLeaveTransaction
					.getNoOfDays());
			leaveTransaction.setNumberOfHours(adminLeaveTransaction
					.getNoOfHours());
			LeaveTransaction leaveTransactionSave = leaveTransactionRepository
					.save(leaveTransaction);
			return leaveTransactionSave.getId();
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void update(AdminLeaveTransaction adminLeaveTransaction) {
		try {
			LeaveTransaction leaveTransaction = new LeaveTransaction();
			LeaveTransaction leaveTransactionUpdated = leaveTransactionRepository
					.findOne(adminLeaveTransaction.getId());
			if (leaveTransactionUpdated != null) {

				int employeeId = 0, leaveTypeId = 0;
				Employee employee = new Employee();
				LeaveType leaveType  = new LeaveType();
				 
				if (adminLeaveTransaction.getEmployeeName() != null
						&& !adminLeaveTransaction.equals("")) {
					employee = employeeRepository
							.findByName(adminLeaveTransaction.getEmployeeName());
					employeeId = employee.getId();
				}

				leaveTransactionUpdated.setEmployeeId(employee);

				if (adminLeaveTransaction.getLeaveType() != null
						&& !adminLeaveTransaction.equals("")) {
					 leaveType = leaveTypeRepository
							.findByName(adminLeaveTransaction.getLeaveType());
					leaveTypeId = leaveType.getId();
				}

				leaveTransactionUpdated.setLeaveTypeId(leaveType);
				
				
				leaveTransactionUpdated.setApplicationDate(adminLeaveTransaction
								.getApplicationDate());
				leaveTransactionUpdated.setStartDateTime(adminLeaveTransaction
						.getStartTime());
				leaveTransactionUpdated.setEndDateTime(adminLeaveTransaction
						.getEndTime());
				leaveTransactionUpdated.setReason(adminLeaveTransaction.getReason());
						
				leaveTransactionUpdated.setNumberOfDays(adminLeaveTransaction
						.getNoOfDays());
				leaveTransactionUpdated.setNumberOfHours(adminLeaveTransaction
						.getNoOfHours());
				leaveTransactionRepository.save(leaveTransactionUpdated);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		LeaveTransaction leaveTransaction = leaveTransactionRepository.findOne(id);
		System.out.println(leaveTransaction.isDeleted());
		leaveTransaction.setDeleted(true);
		LeaveTransaction l = leaveTransactionRepository.save(leaveTransaction);
		System.out.println(leaveTransaction.isDeleted());
		
	}

	@Override
	public List<AdminLeaveTransaction> findByEmployeeIdAndfindByLeaveTypeId(String employeeName,
			String leaveType) {

		List<Integer> leaveTypeIds = new LinkedList<Integer>();
		List<Integer> employeeNamesIds = new LinkedList<Integer>();
		List<LeaveTransaction> leaveTransaction = new LinkedList<LeaveTransaction>();
		String leaveTypeSearchTerm = "%" + leaveType + "%";
		String employeeNameSearchTerm = "%" + employeeName + "%";
	
		if(!employeeName.trim().equals("") && !leaveType.trim().equals("")){
			
			List<LeaveType> leaveTypeList = (List<LeaveType>) leaveTypeRepository
					.findByNameLike(leaveTypeSearchTerm);
			
			
			if(leaveTypeList.size() > 0){
				for(LeaveType leaveTypeObj : leaveTypeList){
					int id = leaveTypeObj.getId();
					leaveTypeIds.add(id);
				}
			}
			
			List<Employee> employeeList = employeeRepository.findByEmployeeNameLike(employeeNameSearchTerm);
			for(Employee employee :employeeList){
				int id = employee.getId();
				employeeNamesIds.add(id);
			}
			
			for(Integer employeeId :employeeNamesIds){
				for(Integer leaveTypeId : leaveTypeIds){
					leaveTransaction  = leaveTransactionRepository.findByLeaveTypeIdAndEmployeeTypeIdLike(leaveTypeId,employeeId);
				}
			}
			
		}else if (!employeeName.trim().equals("")) {

			List<Employee> employeelist = employeeRepository
					.findByEmployeeNameLike(employeeNameSearchTerm);
			for (Employee employeeObject : employeelist) {
				leaveTransaction = leaveTransactionRepository
						.findByEmployeeIdLike(employeeObject.getId());
			}

		} else if (!leaveType.trim().equals("")) {

			List<LeaveType> leaveTypeList = leaveTypeRepository
					.findByNameLike(leaveTypeSearchTerm);
			for (LeaveType leaveTypeObj : leaveTypeList) {
				leaveTransaction = leaveTransactionRepository
						.findByLeaveTypeIdLike(leaveTypeObj.getId());
			}
		}
		List<AdminLeaveTransaction> adminLeaveTransactionList = new ArrayList<AdminLeaveTransaction>();
		AdminLeaveTransaction adminLeaveTransaction = new AdminLeaveTransaction();
	     if(leaveTransaction.size() > 0){
	    	 
	    	 for(LeaveTransaction leaveTransactionObj : leaveTransaction){
	    		 adminLeaveTransaction.setId(leaveTransactionObj.getId());
	    		 adminLeaveTransaction.setApplicationDate(leaveTransactionObj.getApplicationDate());
	    		 adminLeaveTransaction.setStartTime(leaveTransactionObj.getStartDateTime());
	    		 adminLeaveTransaction.setEndTime(leaveTransactionObj.getEndDateTime());
	    		 adminLeaveTransaction.setNoOfDays(leaveTransactionObj.getNumberOfDays());
	    		 adminLeaveTransaction.setNoOfHours(leaveTransactionObj.getNumberOfHours());
	    		 adminLeaveTransaction.setReason(leaveTransactionObj.getReason());
	    
	    		String name = employeeRepository.findOne(leaveTransactionObj.getEmployeeId().getId()).getName();
	    		
	    		 String leaveTypeName = leaveTypeRepository.findOne(leaveTransactionObj.getLeaveTypeId().getId()).getName();
	    		 adminLeaveTransactionList.add(new AdminLeaveTransaction(leaveTransactionObj.getId(),name,leaveTypeName,leaveTransactionObj.getApplicationDate(),leaveTransactionObj.getStartDateTime(),leaveTransactionObj.getEndDateTime(),leaveTransactionObj.getReason(),leaveTransactionObj.getNumberOfDays(),leaveTransactionObj.getNumberOfHours()));
	    		 
	    		
	    	 }
	    	 return adminLeaveTransactionList;
	     }
			
		return null;
		// TODO Auto-generated method stub
		
	}

	
	
}

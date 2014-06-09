package com.beans.leaveapp.yearlyentitlement.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.transaction.annotation.Transactional;

import com.beans.common.audit.service.AuditTrail;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.repository.EmployeeRepository;
import com.beans.leaveapp.leavetype.model.LeaveType;
import com.beans.leaveapp.leavetype.repository.LeaveTypeRepository;
import com.beans.leaveapp.yearlyentitlement.model.YearlyEntitlement;
import com.beans.leaveapp.yearlyentitlement.repository.YearlyEntitlementRepository;

public class YearlyEntitlementServiceImpl implements YearlyEntitlementService {

	@Resource
	YearlyEntitlementRepository yearlyEntitleRepository;

	@Resource
	EmployeeRepository employeeRepository;

	@Resource
	LeaveTypeRepository leaveTypeRepository;

	YearlyEntitlement yearlyEntitlement = new YearlyEntitlement();
	AuditTrail auditTrail;

	public YearlyEntitlement getYearlyEntitlement() {
		return yearlyEntitlement;
	}

	public void setYearlyEntitlement(YearlyEntitlement yearlyEntitlement) {
		this.yearlyEntitlement = yearlyEntitlement;
	}

	@Override
	public List<YearlyEntitlement> findAll() {
		List<YearlyEntitlement> yearlyEntitlementList = (List<YearlyEntitlement>) yearlyEntitleRepository
				.findByIsDeleted(0);

		return yearlyEntitlementList;
	}

	@Override
	public YearlyEntitlement update(YearlyEntitlement selectedYearlyEntitlement)
			throws Exception {
		YearlyEntitlement yearlyEntitlementToBeUpdated = yearlyEntitleRepository
				.findOne(selectedYearlyEntitlement.getId());

		if (yearlyEntitlementToBeUpdated != null) {

			yearlyEntitlementToBeUpdated.setYearlyLeaveBalance(selectedYearlyEntitlement.getYearlyLeaveBalance());
			yearlyEntitlementToBeUpdated.setEntitlement(selectedYearlyEntitlement.getEntitlement());
			yearlyEntitlementToBeUpdated.setLastModifiedBy(selectedYearlyEntitlement.getLastModifiedBy());
			yearlyEntitlementToBeUpdated.setLastModifiedTime(selectedYearlyEntitlement.getLastModifiedTime());
			yearlyEntitleRepository.save(yearlyEntitlementToBeUpdated);
			return yearlyEntitlementToBeUpdated;
		}
		return yearlyEntitlementToBeUpdated;
	}

	@Override
	public YearlyEntitlement delete(int id) {

		YearlyEntitlement YearlyEntitlement = yearlyEntitleRepository
				.findOne(id);

		if (YearlyEntitlement != null) {

			YearlyEntitlement.setDeleted(true);
			yearlyEntitleRepository.save(YearlyEntitlement);
			return YearlyEntitlement;
		}
		return null;
	}

	/**/

	@Override
	public List<String> employeeNames() {
		List<String> employeeNamesList = employeeRepository.findByNames();
		return employeeNamesList;
	}

	@Override
	public List<String> findLeaveTypes(int id) {
		
		List<String> leavesNameList = (List<String>) leaveTypeRepository.findByLeaveTypes(id);		
		return leavesNameList;
	}

	@Override
	public YearlyEntitlement create(YearlyEntitlement yearlyEntitlement) {
		yearlyEntitlement.setDeleted(false);
		YearlyEntitlement yearlyEntitlementObj = yearlyEntitleRepository.save(yearlyEntitlement);
		return yearlyEntitlementObj;
	}

	@Override
	public Employee findByEmployee(String name) {

		Employee employee = employeeRepository.findByName(name);
		return employee;
	}

	@Override
	public LeaveType findByLeaveType(String name,int employeeTypeId) {
		LeaveType leaveType = leaveTypeRepository.findByName(name,employeeTypeId);
		return leaveType;
	}

	@Override
	public List<YearlyEntitlement> findByEmployeeOrfindByLeaveTypeOrBoth(
			String employeeName, String leaveType) {

		String name = "%" + employeeName.trim() + "%";
		String leaveName = "%" + leaveType.trim() + "%";
		List<YearlyEntitlement> yearlyEntitlementList = new LinkedList<YearlyEntitlement>();
		if (!employeeName.trim().equals("") && (!leaveType.trim().equals(""))) {
			yearlyEntitlementList = yearlyEntitleRepository
					.findByEmployeeAndLeaveTypeLike(name, leaveName);

			// yearlyEntitlementList =
			// yearlyEntitleRepository.findByEmployeeOrLeaveTypeLike(employeeName,leaveType);

		} else if (!employeeName.trim().equals("")) {
			yearlyEntitlementList = yearlyEntitleRepository
					.findByEmployeeLike(name);
		} else if (leaveType.trim() != null || !leaveType.trim().equals("")) {
			yearlyEntitlementList = yearlyEntitleRepository
					.findByLeaveTypeLike(leaveName);
		}
		return yearlyEntitlementList;
	}

	@Override
	public List<YearlyEntitlement> findByEmployeeId(int employeeId) {

		List<YearlyEntitlement> listOfYearlyEntitlement = new LinkedList<YearlyEntitlement>();
		try {

			if (employeeId > 0) {
				listOfYearlyEntitlement = yearlyEntitleRepository.findByEmployeeId(employeeId);
				return listOfYearlyEntitlement;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfYearlyEntitlement;
	}

	@Override
	public YearlyEntitlement findByEmployeeAndLeaveType(int employeeId,
			int leaveTypeId) throws YearlyEntitlementNotFound {
		List<YearlyEntitlement> resultList = yearlyEntitleRepository.findByEmployeeIdAndLeaveTypeId(employeeId, leaveTypeId);
		
		if(resultList == null || resultList.size() == 0) {
			throw new YearlyEntitlementNotFound("This Employee does not have the entitlement for this leave type");
		}
		return resultList.get(0);
	}

	@Override
	public List<YearlyEntitlement> findYearlyEntitlementListByEmployee(
			int employeeId)  {
		
		return yearlyEntitleRepository.findByEmployeeId(employeeId);
	}

	@Override
	public YearlyEntitlement findOne(int yearlyEntitlementId)
			throws YearlyEntitlementNotFound {
		YearlyEntitlement yearlyEntitlement = yearlyEntitleRepository.findOne(yearlyEntitlementId);
		
		if(yearlyEntitlement == null) {
			throw new YearlyEntitlementNotFound("Can't find Yearly Entitlement with id: " + yearlyEntitlementId);
		}
		return yearlyEntitlement;
	}

	@Override
	public YearlyEntitlement findByEmployeeIdPermAndCont(int employeeId) {
		 YearlyEntitlement yearlyEntitlementPermAndCont = yearlyEntitleRepository.findByEmployeeIdPermAndCont(employeeId);
		return yearlyEntitlementPermAndCont;
	}

	@Override
	public YearlyEntitlement findByEmployeeIdPerm(int employeeId) {
		YearlyEntitlement yearlyEntitlementPerm = yearlyEntitleRepository.findByEmployeeIdPerm(employeeId);
		return yearlyEntitlementPerm;
	}	
	
	@Override
	public List<YearlyEntitlement> findByEmployeeIdPermForRemainingLeaves(int employeeId) {
		List<YearlyEntitlement> yearlyEntitlementList = yearlyEntitleRepository.findByEmployeeIdPermForRemainingLeaves(employeeId);
		return yearlyEntitlementList;
	}

	@Override
	@Transactional
	public void updateLeaveBalanceAfterApproval(int employeeId,int leaveTypeId,double numberOfDaysLeaveApproved) {
		System.out.println("Input parameters values in updateLeaveBalanceAfterApproval() employeeId: "+employeeId+" leaveTypeId :"+leaveTypeId);
		YearlyEntitlement yearlyEntitlement =	(YearlyEntitlement) yearlyEntitleRepository.findByEmployeeAndLeaveTypeId(employeeId, leaveTypeId);
		yearlyEntitlement.setcurrentLeaveBalance(yearlyEntitlement.getCurrentLeaveBalance()-numberOfDaysLeaveApproved);
		yearlyEntitlement.setYearlyLeaveBalance(yearlyEntitlement.getYearlyLeaveBalance()-numberOfDaysLeaveApproved);
		yearlyEntitleRepository.save(yearlyEntitlement);
		System.out.println("After updating, no'of leaves left is :"+yearlyEntitlement.getCurrentLeaveBalance());
	}
	

	

}


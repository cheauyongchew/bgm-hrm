
package com.beans.leaveapp.leavetype.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.leaveapp.employeetype.model.EmployeeType;
import com.beans.leaveapp.employeetype.repository.EmployeeTypeRepository;
import com.beans.leaveapp.leavetype.model.LeaveType;
import com.beans.leaveapp.leavetype.repository.LeaveTypeRepository;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

	@Resource
	private LeaveTypeRepository leaveTypeRepository;
	
	@Resource
	private EmployeeTypeRepository employeeTypeRepository;
	
	@Override
	@Transactional
	public LeaveType create(LeaveType leaveType) {
		LeaveType leaveTypeToBeCreated = leaveType;
		
		return leaveTypeRepository.save(leaveTypeToBeCreated);
	}

	@Override
	@Transactional(rollbackFor=LeaveTypeNotFound.class)
	public LeaveType delete(int id) throws LeaveTypeNotFound {
		LeaveType leaveType = leaveTypeRepository.findOne(id);
		
		if(leaveType == null)
			throw new LeaveTypeNotFound();
		leaveType.setDeleted(true);
		leaveTypeRepository.save(leaveType);
		return leaveType;
	}

	@Override
	public List<LeaveType> findAll() {
		List<LeaveType> resultList = leaveTypeRepository.findByisDeleted(0);
		List<LeaveType> list = new LinkedList<LeaveType>();
		/*for(LeaveType leaveType: resultList){
			//EmployeeType e = employeeTypeRepository.findOne();
			// String employeeTypeName = e.getName();
			leaveType.getEmployeeTypeId().getName();
		}
		*/
		return resultList;
	}

	@Override
	@Transactional(rollbackFor=LeaveTypeNotFound.class)
	public LeaveType update(LeaveType leaveType) throws LeaveTypeNotFound {
		//int id = employeeTypeRepository.findByName(leaveType.getEmployeeTypeId().getName()).getId();
		//leaveType.setEmployeeTypeId(id);
		LeaveType leaveTypeToBeUpdated = leaveTypeRepository.save(leaveType);
		
		/*if(leaveTypeToBeUpdated == null)
			throw new LeaveTypeNotFound();
		
		leaveTypeToBeUpdated.setName(leaveType.getName());
		leaveTypeToBeUpdated.setDescription(leaveType.getDescription());
		leaveTypeToBeUpdated.setDeleted(leaveType.isDeleted());
		if(leaveType != null){
			EmployeeType employeeTypeObj = employeeTypeRepository.findByName(leaveType.getEmployeeTypeName());
			int employeeTypeId = employeeTypeObj.getId();
			leaveTypeToBeUpdated.setEmployeeTypeId(employeeTypeId);
		}
			leaveTypeRepository.save(leaveTypeToBeUpdated);
		*/
		return leaveTypeToBeUpdated;
	}

	@Override
	public LeaveType findById(int id) throws LeaveTypeNotFound{
		LeaveType leaveType = leaveTypeRepository.findOne(id);
		
		if(leaveType == null)
			throw new LeaveTypeNotFound();
		
		return leaveType;
	}

	@Override
	public LeaveType findByLeaveType(String name) throws LeaveTypeNotFound {
		LeaveType leaveTypeList =  leaveTypeRepository.findByName(name);
		return leaveTypeList;
	}

	@Override
	public List<String> findByName() {
		List<String> list = leaveTypeRepository.findNamesList();
		return list;
	}

	@Override
	public EmployeeType findByEmployeeName(String name) {
		EmployeeType employeeType =  employeeTypeRepository.findByName(name);
		return employeeType;
	}
	

	
}


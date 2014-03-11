package com.beans.leaveapp.employee.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.leaveapp.address.repository.AddressRepository;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Resource
	EmployeeRepository employeeRepository;
	
	@Resource
	AddressRepository addressRepository;
	
	@Override
	@Transactional
	public Employee create(Employee employee) {
		Employee employeeToBeUpdated = employee;
		
		return employeeRepository.save(employeeToBeUpdated);
	}

	@Override
	@Transactional(rollbackFor=EmployeeNotFound.class)
	public Employee delete(int id) throws EmployeeNotFound {
		Employee employee = employeeRepository.findOne(id);
		
		if(employee == null)
			throw new EmployeeNotFound();
		
		employee.setDeleted(true);
		
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> resultList = employeeRepository.findByisDeleted(1);
		return resultList;
	}
	
	
	
	@Override
	@Transactional(rollbackFor=EmployeeNotFound.class)
	public Employee update(Employee employee) throws EmployeeNotFound {
		Employee employeeToBeUpdated = employeeRepository.findOne(employee.getId());
		
		if(employeeToBeUpdated == null)
			throw new EmployeeNotFound();
		
		employeeToBeUpdated.setEmployeeNumber(employee.getEmployeeNumber());
		employeeToBeUpdated.setName(employee.getName());
		employeeToBeUpdated.setPosition(employee.getPosition());
		employeeToBeUpdated.setIdNumber(employee.getIdNumber());
		employeeToBeUpdated.setPassportNumber(employee.getPassportNumber());
		employeeToBeUpdated.setGender(employee.getGender());
		employeeToBeUpdated.setReligion(employee.getReligion());
		employeeToBeUpdated.setMaritalStatus(employee.getMaritalStatus());
		employeeToBeUpdated.setWorkEmailAddress(employee.getWorkEmailAddress());
		employeeToBeUpdated.setPersonalEmailAddress(employee.getPersonalEmailAddress());
		employeeToBeUpdated.setOfficePhone(employee.getOfficePhone());
		employeeToBeUpdated.setPersonalPhone(employee.getPersonalPhone());
		employeeToBeUpdated.setNationality(employee.getNationality());
		
		//TODO save users information
		
		employeeToBeUpdated.setEmployeeGrade(employee.getEmployeeGrade());
		employeeToBeUpdated.setDepartment(employee.getDepartment());
		employeeToBeUpdated.setEmployeeType(employee.getEmployeeType());
		employeeToBeUpdated.setJoinDate(employee.getJoinDate());
		employeeToBeUpdated.setResignationDate(employee.getResignationDate());
		employeeToBeUpdated.setDeleted(employee.isDeleted());
		employeeToBeUpdated.setResigned(employee.isResigned());
		employeeToBeUpdated.setAddresses(employee.getAddresses());
		return employeeRepository.save(employeeToBeUpdated);
	}

	@Override
	public Employee findById(int id) throws EmployeeNotFound {
		Employee employee = employeeRepository.findOne(id);
		
		if(employee == null)
			throw new EmployeeNotFound();
		
		return employee;
	}

}

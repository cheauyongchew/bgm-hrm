package com.beans.leaveapp.employee.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.beans.leaveapp.address.repository.AddressRepository;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	
	
	@Override
	public Employee create(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee delete(int id) throws EmployeeNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee update(Employee employee) throws EmployeeNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findById(int id) throws EmployeeNotFound {
		// TODO Auto-generated method stub
		return null;
	}

}

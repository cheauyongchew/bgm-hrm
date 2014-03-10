package com.beans.leaveapp.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.beans.leaveapp.employee.model.Employee;


public interface EmployeeRepository {
	@Query("select e from Employee e where isDeleted = ?")
	List<Employee> findByisDeleted(int isDeleted);
	
	@Query("select e from Employee e where isResigned = ? and isDeleted = ?")
	List<Employee> findByIsResignedAndIsDeleted(int isResigned, int isDeleted);
}

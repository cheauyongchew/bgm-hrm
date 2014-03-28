package com.beans.leaveapp.yearlyentitlement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.beans.leaveapp.yearlyentitlement.model.LeaveEntitlement;
import com.beans.leaveapp.yearlyentitlement.model.YearlyEntitlement;


public interface YearlyEntitlementRepository extends CrudRepository<YearlyEntitlement, Integer>{

	
	@Query("select y.entitlement,y.availableBalance from YearlyEntitlement y join y.employee e where y.employeeId = e.id")
	public List<YearlyEntitlement> find();
	
	@Query("select y from YearlyEntitlement y where isDeleted =?")
	public List<YearlyEntitlement> findByIsDeleted(int x);
	
	
	@Query("select y.id,y.leaveTypeId,y.entitlement, y.availableBalance,e.name from YearlyEntitlement y,Employee e where y.employeeId=e.id")
	public List<LeaveEntitlement> findLeaveEntitlement();
	
	@Query("select y from YearlyEntitlement y where y.employeeId = ?")
	public List<YearlyEntitlement> findByEmployeeId(int x);
	
}

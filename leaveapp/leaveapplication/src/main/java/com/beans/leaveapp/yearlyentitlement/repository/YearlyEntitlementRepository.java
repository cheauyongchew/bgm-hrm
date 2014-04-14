
package com.beans.leaveapp.yearlyentitlement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.beans.leaveapp.yearlyentitlement.model.LeaveEntitlement;
import com.beans.leaveapp.yearlyentitlement.model.YearlyEntitlement;


public interface YearlyEntitlementRepository extends CrudRepository<YearlyEntitlement, Integer>{

	
	/*@Query("select y.entitlement,y.availableBalance from YearlyEntitlement y join y.employee e where y.employeeId = e.id")
	public List<YearlyEntitlement> find();*/
	
	@Query("select y from YearlyEntitlement y where isDeleted =?")
	public List<YearlyEntitlement> findByIsDeleted(int x);
	
	
	/*@Query("select y.id,y.leaveType,y.entitlement, y.availableBalance,e.name from YearlyEntitlement y,Employee e where y.employee=e.id")
	public List<LeaveEntitlement> findLeaveEntitlement();
	*/
	@Query("select y from YearlyEntitlement y where  employeeId like ? and isDeleted = 0")
	public List<YearlyEntitlement> findByEmployeeIdLike(int x);
	
	@Query("select y from  YearlyEntitlement y where leaveTypeId like ? and isDeleted = 0")
	public List<YearlyEntitlement> findByLeaveTypeIdLike(int x);
	
	@Query("select y from YearlyEntitlement y where leaveTypeId like ? and employeeId  like ? and isDeleted = 0")
	public List<YearlyEntitlement> findByEmployeeIdAndLeaveTypeIdLike(int x,int y);
	
	@Query("select y from YearlyEntitlement y join y.employee e where e.name like ?")
	public List<YearlyEntitlement> findByEmployeeLike(String name);
	
	@Query("select y from YearlyEntitlement y join y.leaveType l where l.name = :name")
	public YearlyEntitlement findByLeaveType(@Param("name") String name);
	
	@Query("select y from YearlyEntitlement y where leaveTypeId = ? and employeeId  = ? and isDeleted = 0")
	public List<YearlyEntitlement> findByEmployeeIdAndLeaveTypeId(int employeeId, int leaveTypeId);
	
	@Query("select y from YearlyEntitlement y where  employeeId = ? and isDeleted = 0")
	public List<YearlyEntitlement> findByEmployeeId(int employeeId);
	
}

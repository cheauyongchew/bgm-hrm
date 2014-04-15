package com.beans.leaveapp.leavetransaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.beans.leaveapp.leavetransaction.model.LeaveTransaction;

public interface LeaveTransactionRepository extends CrudRepository<LeaveTransaction, Integer> {
	
	
	@Query("select l from LeaveTransaction l where isDeleted =?")
	List<LeaveTransaction> findAll(int x);

	@Query("select l from LeaveTransaction l join l.employee e where e.name like :employeeName" )
	public List<LeaveTransaction> findByEmployeeLike(@Param("employeeName") String employeeName);
	
	@Query("select l from  LeaveTransaction l join l.leaveType lt where lt.name like :leaveTypeName")
	public List<LeaveTransaction> findByLeaveTypeLike(@Param("leaveTypeName") String leaveTypeName);
	
	/*@Query("select l from LeaveTransaction l join l.employee e join l.leaveType lt  where  e.name like :employeeName and lt.name like :leaveTypeName")
	List<LeaveTransaction> findByEmployeeAndLeaveTypeLike(@Param("employeeName") String employeeName,@Param("leaveTypeName") String leaveTypeName);
	*/
	
	@Query("select l from LeaveTransaction l join l.employee e join l.leaveType lt  where  e.name like :employeeName or lt.name like :leaveTypeName")
	List<LeaveTransaction> findByEmployeeOrLeaveTypeLike(@Param("employeeName") String employeeName,@Param("leaveTypeName") String leaveTypeName);
	
	@Query("select y from LeaveTransaction y join y.employee e join y.leaveType l where e.name like :employeeName and l.name like :leaveTypeName")
	public List<LeaveTransaction> findByEmployeeAndLeaveTypeLike(@Param("employeeName") String employeeName,@Param("leaveTypeName") String leaveTypeName);
	
	
	
	/*@Query("select l from LeaveTransaction l join l.employeeId e where e.id = :id ")
	List<LeaveTransaction> findByEmployeeId(@Param("id") int id);
	
	
	@Query("select l from LeaveTransaction l join l.leaveTypeId ll where ll.id = :id")
	LeaveTransaction findByLeaveTypeId(@Param("id") int id);
	*/
	
	

}

package com.beans.leaveapp.leavetransaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.beans.leaveapp.leavetransaction.model.LeaveTransaction;

public interface LeaveTransactionRepository extends CrudRepository<LeaveTransaction, Integer> {
	
	
	@Query("select l from LeaveTransaction l where isDeleted =?")
	List<LeaveTransaction> findAll(int x);

	@Query("select l from LeaveTransaction l where  employeeId like ? and isDeleted = 0")
	public List<LeaveTransaction> findByEmployeeIdLike(int x);
	
	@Query("select l from  LeaveTransaction l where leaveTypeId like ? and isDeleted = 0")
	public List<LeaveTransaction> findByLeaveTypeIdLike(int x);
	
	@Query("select l from LeaveTransaction l where leaveTypeId like ? and employeeId like ? and isDeleted = 0")
	List<LeaveTransaction> findByLeaveTypeIdAndEmployeeTypeIdLike(int x,int y);
	
	@Query("select l from LeaveTransaction l join l.employeeId e where e.id = :id ")
	List<LeaveTransaction> findByEmployeeId(@Param("id") int id);
	
	
	@Query("select l from LeaveTransaction l join l.leaveTypeId ll where ll.id = :id")
	LeaveTransaction findByLeaveTypeId(@Param("id") int id);
	
	
	

}

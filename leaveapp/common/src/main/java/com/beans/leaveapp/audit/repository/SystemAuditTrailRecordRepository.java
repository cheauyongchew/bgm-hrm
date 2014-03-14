package com.beans.leaveapp.audit.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.beans.leaveapp.common.audit.model.SystemAuditTrail;

public interface SystemAuditTrailRecordRepository extends CrudRepository<SystemAuditTrail, Integer>{

	
	 @Query("select l from SystemAuditTrail l where isDeleted = ?")
	 List<SystemAuditTrail> findByisDeleted(int x);
	 
	/* @Query("select l from SystemAuditTrail l where date = ?")
	  List<SystemAuditTrail> findByDate(Date x);*/
	
	 @Query("select l from SystemAuditTrail l where actiondate between ? and  ?")	 
	 List<SystemAuditTrail> findByDate(Date x,Date y);

	
	
}

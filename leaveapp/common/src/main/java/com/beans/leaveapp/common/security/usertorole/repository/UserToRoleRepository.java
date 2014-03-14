package com.beans.leaveapp.common.security.usertorole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.beans.leaveapp.common.security.usertorole.model.UserToRole;

public interface UserToRoleRepository extends CrudRepository<UserToRole, Integer>{
	
	@Query("select utr from UserToRole utr where isDeleted = ?")
	 List<UserToRole> findByisDeleted(int x);
	
	 @Query("select utr from UserToRole utr where userRole = ?")
	 UserToRole findByRole(String userrole);
}

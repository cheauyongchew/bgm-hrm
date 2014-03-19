package com.beans.common.security.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.beans.common.security.role.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{

	
	 @Query("select r from Role r where isDeleted = ?")
	 List<Role> findByisDeleted(int x);
	
	@Query("select r from Role r")
	 List<Role> findById(int id);
	
}

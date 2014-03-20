package com.beans.common.security.role.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.common.security.role.model.Role;
import com.beans.common.security.role.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	
	@Resource
	private RoleRepository roleRepository;
	
	
	@Override
	@Transactional
	public Role create(Role role) {
		Role roleToBeCreated = role;		
		return roleRepository.save(roleToBeCreated);
	}

	@Override
	@Transactional
	public Role delete(int id) throws RoleNotFound {
		Role role = roleRepository.findOne(id);
		
		if(role == null)
			throw new RoleNotFound();
		role.setDeleted(true);
		roleRepository.save(role);
		return role;
	}

	@Override
	public List<Role> findAll() {
		List<Role> resultList = roleRepository.findByisDeleted(0);
		return resultList;
	}

	@Override
	@Transactional
	public Role update(Role role) throws RoleNotFound {
		Role roleToBeUpdated = roleRepository.findOne(role.getId());
		
		if(roleToBeUpdated == null)
			throw new RoleNotFound();
		roleToBeUpdated.setId(role.getId());
		roleToBeUpdated.setRole(role.getRole());
		roleToBeUpdated.setDescription(role.getDescription());
		roleRepository.save(roleToBeUpdated);
		return roleToBeUpdated;
	}

	@Override
	@Transactional
	public Role findById(int id) throws RoleNotFound {
	    Role role = roleRepository.findOne(id);
	     
	       if(role == null)
	    	   throw new RoleNotFound();	     
		return role;
	}

}

package com.beans.leaveapp.common.security.usertorole.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.leaveapp.common.security.usertorole.model.UserToRole;
import com.beans.leaveapp.common.security.usertorole.repository.UserToRoleRepository;

@Service
public class UserToRoleServiceImpl implements UserToRoleService {
	
	@Resource
	UserToRoleRepository userToRoleRepository;
    
	@Override
	@Transactional
	public UserToRole create(UserToRole userToRole) {
		UserToRole userToRoleToBeCreated = userToRole;		
		return userToRoleRepository.save(userToRoleToBeCreated);
	}

	@Override
	@Transactional
	public UserToRole delete(int userId) throws UserToRoleNotFound {
		     UserToRole userToRole = userToRoleRepository.findOne(userId);
		     
		     if(userToRole == null)
		    	throw new UserToRoleNotFound();
		     userToRole.setDeleted(true);
		     userToRoleRepository.save(userToRole);		     
		return userToRole;
	}

	@Override
	public List<UserToRole> findAll() {
		List<UserToRole> resultList = userToRoleRepository.findByisDeleted(0);
		return resultList;
	}

	@Override
	@Transactional
	public UserToRole update(UserToRole userToRole) throws UserToRoleNotFound {
			UserToRole userToRoleToBeUpdated = userToRoleRepository.findOne(userToRole.getUserId());
			
			if(userToRoleToBeUpdated == null)
				throw new UserToRoleNotFound();
			userToRoleToBeUpdated.setUserId(userToRole.getUserId());
			userToRoleToBeUpdated.setUserRole(userToRole.getUserRole());
			userToRoleToBeUpdated.setDeleted(userToRole.isDeleted());
			userToRoleRepository.save(userToRoleToBeUpdated);
		return userToRoleToBeUpdated;
	}
	
	@Override
	@Transactional
	public UserToRole findById(int userId) throws UserToRoleNotFound {
		UserToRole userToRole = userToRoleRepository.findOne(userId);
		
		if(userToRole == null) 
			throw new UserToRoleNotFound();
		return userToRole;
	}
	
	

}

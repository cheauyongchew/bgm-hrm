package com.beans.common.security.usertoaccessrights.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.beans.common.security.accessrights.model.AccessRights;
import com.beans.common.security.accessrights.repository.AccessRightsRepository;
import com.beans.common.security.usertoaccessrights.model.AssignedAccessRights;
import com.beans.common.security.usertoaccessrights.model.UserToAccessRights;
import com.beans.common.security.usertoaccessrights.repository.UserToAccessRightsRepository;

@Service
public class UserToAccessRightsServiceImpl implements UserToAccessRightsService{
	
	@Resource
	private UserToAccessRightsRepository userToAccessRightsRepository;
	@Resource
	private AccessRightsRepository accessRightsRepository;

	@Override
	public UserToAccessRights delete(int id) throws UserToAccessRightsNotFound {
		UserToAccessRights userToAccessRights = userToAccessRightsRepository.findOne(id);
		
		if(userToAccessRights == null)
			throw new UserToAccessRightsNotFound();
		userToAccessRights.setDeleted(true);
		userToAccessRightsRepository.save(userToAccessRights);		
		return userToAccessRights;
	}

	@Override
	public List<UserToAccessRights> findAll() {
		List<UserToAccessRights> userToAccessRightsList = userToAccessRightsRepository.findByIsDeleted(0);
		return userToAccessRightsList;
	}

	@Override
	public UserToAccessRights update(UserToAccessRights userToAccessRights)
			throws UserToAccessRightsNotFound {
		UserToAccessRights userAccessRightsToBeUpdated = new UserToAccessRights();
		userAccessRightsToBeUpdated.setId(userToAccessRights.getId());
		userAccessRightsToBeUpdated.setAccessRights(userToAccessRights.getAccessRights());
		userAccessRightsToBeUpdated.setUsers(userToAccessRights.getUsers());
		userAccessRightsToBeUpdated.setEnabled(userToAccessRights.isEnabled());
		userAccessRightsToBeUpdated.setDeleted(userToAccessRights.isDeleted());
		userToAccessRightsRepository.save(userAccessRightsToBeUpdated);
		return userAccessRightsToBeUpdated;
	}

	

	@Override
	public List<UserToAccessRights> findByUserId(int userId) {
		List<UserToAccessRights> userToAccessRightsList = userToAccessRightsRepository.findByUserId(userId);
		return userToAccessRightsList;
	}

	@Override
	public List<AccessRights> findAllAccessRights() {
		List<AccessRights> accessRightsList = accessRightsRepository.findByIsDeleted(0);
		return accessRightsList;
	}		

}
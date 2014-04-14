
package com.beans.common.security.usertoaccessrights.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.beans.common.security.usertoaccessrights.model.AssignedAccessRights;
import com.beans.common.security.usertoaccessrights.model.UserToAccessRights;
import com.beans.common.security.usertoaccessrights.repository.UserToAccessRightsRepository;

@Service
public class UserToAccessRightsServiceImpl implements UserToAccessRightsService{
	
	@Resource
	private UserToAccessRightsRepository userToAccessRightsRepository;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssignedAccessRights> findAssignedAccessRights(int id) {
	    List<AssignedAccessRights> assignedAccessRightsList = new ArrayList<AssignedAccessRights>();	    
	    List<UserToAccessRights> userToAccessRightsList = userToAccessRightsRepository.findByUserId(id);
	    for(UserToAccessRights userToAccessRights: userToAccessRightsList){
	    	String accessRights = userToAccessRights.getAccessRights().getAccessRights();
	    	Boolean enabled= userToAccessRights.isEnabled();
	    	
	    	assignedAccessRightsList.add(new AssignedAccessRights(accessRights,enabled));
	    }
		return assignedAccessRightsList;
	}

	@Override
	public List<UserToAccessRights> findByUserId(int userId) {
		List<UserToAccessRights> userToAccessRightsList = userToAccessRightsRepository.findByUserId(userId);
		return userToAccessRightsList;
	}	
	
	

}


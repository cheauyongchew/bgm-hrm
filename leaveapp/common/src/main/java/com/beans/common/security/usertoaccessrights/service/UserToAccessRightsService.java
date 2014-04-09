
package com.beans.common.security.usertoaccessrights.service;

import java.util.List;

import com.beans.common.security.usertoaccessrights.model.AssignedAccessRights;
import com.beans.common.security.usertoaccessrights.model.UserToAccessRights;

public interface UserToAccessRightsService {
	
	public UserToAccessRights delete(int id) throws UserToAccessRightsNotFound;
	public List<UserToAccessRights> findAll();
	public UserToAccessRights update(UserToAccessRights userToAccessRights) throws UserToAccessRightsNotFound;
	public List<AssignedAccessRights> findAssignedAccessRights(int id);
	public List<UserToAccessRights> findByUserId(int userId);
}


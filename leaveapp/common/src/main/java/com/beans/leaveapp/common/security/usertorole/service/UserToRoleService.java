package com.beans.leaveapp.common.security.usertorole.service;

import java.util.List;

import com.beans.leaveapp.common.security.usertorole.model.UserToRole;


public interface UserToRoleService {
	
	public UserToRole create(UserToRole userToRole);
	public UserToRole delete(int userId) throws UserToRoleNotFound;
	public List<UserToRole> findAll();
	public UserToRole update(UserToRole userToRole) throws UserToRoleNotFound;
	public UserToRole findById(int userId) throws UserToRoleNotFound;

}

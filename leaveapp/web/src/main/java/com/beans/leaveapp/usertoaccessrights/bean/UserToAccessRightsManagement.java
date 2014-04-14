package com.beans.leaveapp.usertoaccessrights.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;











import org.primefaces.event.SelectEvent;

import com.beans.common.security.accessrights.model.AccessRights;
import com.beans.common.security.accessrights.service.AccessRightsService;
import com.beans.common.security.users.model.Users;
import com.beans.common.security.users.service.UsersService;
import com.beans.common.security.usertoaccessrights.model.AssignedAccessRights;
import com.beans.common.security.usertoaccessrights.model.UserToAccessRights;
import com.beans.common.security.usertoaccessrights.service.UserToAccessRightsService;
import com.beans.leaveapp.usertoaccessrights.model.UserToAccessRightsDataModel;
import com.beans.leaveapp.usertoaccessrights.model.UserToAssignedAccessRightsDataModel;

public class UserToAccessRightsManagement implements Serializable{

	private static final long serialVersionUID = 1L;
	private UsersService usersService;
	private AccessRightsService accessRightsService;
	private UserToAccessRightsService userToAccessRightsService;
	private List<Users> usersList;
	private UserToAccessRightsDataModel userToAccessRightsDataModel;
	private Users selectedUsers = new Users();
	private boolean insertDelete = false;
	private List<Users> searchUsers;
	private List<AccessRights> accessRightsList = null;	
	private UserToAssignedAccessRightsDataModel userToAssignedAccessRightsDataModel;
	private UserToAccessRights selectedUserToAccessRights = new UserToAccessRights();	
	private int userId;
	private List<AssignedAccessRights> assignedAccessRightsList;
	
	private String searchUsername = "";
	
	public UsersService getUsersService() {
		return usersService;
	}
	
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	
	public AccessRightsService getAccessRightsService() {
		return accessRightsService;
	}
	
	public void setAccessRightsService(AccessRightsService accessRightsService) {
		this.accessRightsService = accessRightsService;
	}
	
	
	public List<Users> getUsersList() {
			if(usersList == null){
					
					usersList = usersService.findAll();
				}		
		return usersList;
	}
	
	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}
	
	
	public UserToAccessRightsDataModel getUserToAccessRightsDataModel() {
		if(userToAccessRightsDataModel == null)
			userToAccessRightsDataModel = new UserToAccessRightsDataModel(getUsersList());		
		return userToAccessRightsDataModel;
	}
	
	public void setUserToAccessRightsDataModel(
			UserToAccessRightsDataModel userToAccessRightsDataModel) {
		this.userToAccessRightsDataModel = userToAccessRightsDataModel;
	}
	
	
	public Users getSelectedUsers() {
		return selectedUsers;
	}
	
	public void setSelectedUsers(Users selectedUsers) {				
		this.selectedUsers = selectedUsers;
	}
	
	
	public void assignedAccessRights(){
		userId = getSelectedUsers().getId();		
	}
	
	
	public void saveUserToAccessRightsMapping(){		
		
	}	
	
	public void addAccessRightsToUser(){
		
	}
	
	public void onRowSelect(SelectEvent event){
		setSelectedUsers((Users) event.getObject());
	}	
	
	public boolean isInsertDelete() {
		return insertDelete;
	}
	
	public void setInsertDelete(boolean insertDelete) {
		this.insertDelete = insertDelete;
	}
	
	
	public List<Users> getSearchUsers() {
		return searchUsers;
	}
	
	public void setSearchUsers(List<Users> searchUsers) {
		this.searchUsers = searchUsers;
	}
	
	public String getSearchUsername() {
		return searchUsername;
	}
	public void setSearchUsername(String searchUsername) {
		this.searchUsername = searchUsername;
	}
	
	public void searchUser(){		
		if(getSearchUsername() == null || getSearchUsername().trim().equals("")){
			this.usersList = null;
			this.userToAccessRightsDataModel = null;			
		}else {
			this.usersList = usersService.findUsersByUsername(getSearchUsername());
			this.userToAccessRightsDataModel = null;
		}		
	}
	
	public List<AssignedAccessRights> getAssignedAccessRightsList() {
		 if(assignedAccessRightsList == null || insertDelete == true){
			 
			 assignedAccessRightsList = userToAccessRightsService.findAssignedAccessRights(userId);
			 System.out.println("" +userId);
		 }				
		return assignedAccessRightsList;
	}

	public void setAssignedAccessRightsList(
			List<AssignedAccessRights> assignedAccessRightsList) {
		this.assignedAccessRightsList = assignedAccessRightsList;
	}

	public UserToAssignedAccessRightsDataModel getUserToAssignedAccessRightsDataModel() {	
		if(userToAssignedAccessRightsDataModel == null || insertDelete == true){
					
			userToAssignedAccessRightsDataModel = new UserToAssignedAccessRightsDataModel(getAssignedAccessRightsList());
		}		
		return userToAssignedAccessRightsDataModel;
	}

	public void setUserToAssignedAccessRightsDataModel(
			UserToAssignedAccessRightsDataModel userToAssignedAccessRightsDataModel) {
		this.userToAssignedAccessRightsDataModel = userToAssignedAccessRightsDataModel;
	}

	public UserToAccessRightsService getUserToAccessRightsService() {
		return userToAccessRightsService;
	}

	public void setUserToAccessRightsService(
			UserToAccessRightsService userToAccessRightsService) {
		this.userToAccessRightsService = userToAccessRightsService;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}	
	
}

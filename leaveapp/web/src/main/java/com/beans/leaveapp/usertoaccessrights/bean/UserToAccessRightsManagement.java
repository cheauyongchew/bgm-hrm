package com.beans.leaveapp.usertoaccessrights.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;








import org.primefaces.event.SelectEvent;

import com.beans.common.security.accessrights.model.AccessRights;
import com.beans.common.security.accessrights.service.AccessRightsService;
import com.beans.common.security.users.model.Users;
import com.beans.common.security.users.service.UsersService;
import com.beans.leaveapp.usertoaccessrights.model.UserToAccessRightsDataModel;
import com.beans.leaveapp.usertoaccessrights.model.UserToAssignedAccessRightsDataModel;

public class UserToAccessRightsManagement implements Serializable{

	private static final long serialVersionUID = 1L;
	private UsersService usersService;
	private AccessRightsService accessRightsService;
	private List<Users> usersList;
	private UserToAccessRightsDataModel userToAccessRightsDataModel;
	private Users selectedUsers = new Users();
	private boolean insertDelete = false;
	private List<Users> searchUsers;
	private List<AccessRights> accessRightsList;
	private List<AccessRights> assignedAccessRightsList = new ArrayList<AccessRights>();
	private List<AccessRights> unAssignedAccessRightsLisgt = new ArrayList<AccessRights>();
	private UserToAssignedAccessRightsDataModel userToAssignedAccessRightsDataModel;
	
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
			if(usersList == null || insertDelete == true ){
					
					usersList = usersService.findAll();
				}		
		return usersList;
	}
	
	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}
	
	
	public UserToAccessRightsDataModel getUserToAccessRightsDataModel() {
		if(userToAccessRightsDataModel == null || insertDelete == true)
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

	public UserToAssignedAccessRightsDataModel getUserToAssignedAccessRightsDataModel() {		
		return userToAssignedAccessRightsDataModel;
	}

	public void setUserToAssignedAccessRightsDataModel(
			UserToAssignedAccessRightsDataModel userToAssignedAccessRightsDataModel) {
		this.userToAssignedAccessRightsDataModel = userToAssignedAccessRightsDataModel;
	}
	
	
	
}

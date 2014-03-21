package com.beans.leaveapp.usertorole.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.beans.common.security.role.model.Role;
import com.beans.common.security.users.model.Users;
import com.beans.common.security.users.service.UsersNotFound;
import com.beans.common.security.users.service.UsersService;
import com.beans.leaveapp.role.bean.RoleManagement;
import com.beans.leaveapp.usertorole.model.UserToRoleDataModel;

public class UserToRoleManagement implements Serializable{
	private static final long serialVersionUID = 1L;
	UsersService usersService;
	private List<Users> usersList;
	private UserToRoleDataModel userToRoleDataModel;
	private Users newUsers = new Users();	
	private Users selectedUsers = new Users();
	private boolean insertDelete = false;
	private List<Users> searchUsers;
	private DualListModel<Role> roles;	

	
	public UsersService getUsersService() {
		return usersService;
	}
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
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
	
	
	public UserToRoleDataModel getUserToRoleDataModel() {
		if(userToRoleDataModel == null || insertDelete == true){			
			userToRoleDataModel = new UserToRoleDataModel(getUsersList());			
		}		
		return userToRoleDataModel;
	}	
	
	public void setUserToRoleDataModel(UserToRoleDataModel userToRoleDataModel) {
		this.userToRoleDataModel = userToRoleDataModel;
	}
	
	
	public Users getNewUsers() {		
		return newUsers;
	}
	
	public void setNewUsers(Users newUsers) {
		this.newUsers = newUsers;
	}
	
	
	
	public Users getSelectedUsers() {
		return selectedUsers;
	}
	
	public void setSelectedUsers(Users selectedUsers) {
		this.selectedUsers = selectedUsers;
	}
	
	
	public void doUpdateUserToRole() {
		try{
		System.out.println("New User Role:" + selectedUsers.getUserRoles());
		System.out.println("User Id" + selectedUsers.getId());
		getUsersService().update(selectedUsers);
		}catch(UsersNotFound e){
			
			FacesMessage msg = new FacesMessage("Error" , "User Role With userId" + selectedUsers.getId() + "not found");
			 FacesContext.getCurrentInstance().addMessage(null, msg);  
		}		
	}
	
	public void onRowSelect(SelectEvent event){
		setSelectedUsers((Users) event.getObject());
//		FacesMessage msg = new FacesMessage("User Role Selected", selectedUsers.getUserRoles());
//		
//		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void doUserToRole() {
		try {
			getUsersService().delete(selectedUsers.getId());
		} catch (UsersNotFound e) {		
			FacesMessage msg = new FacesMessage("Error" , "User Role With userId" + selectedUsers.getId() + "not found");
			 FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	
	public void userRoles() {
		
		RoleManagement roleManagement = new RoleManagement();		
		List<Role> availableRoles = roleManagement.getRoleList();
		List<Role> selectedRoles = new ArrayList<Role>();		
		roles = new DualListModel<Role>(availableRoles, selectedRoles);
		
	}
	
	public void onTransfer(TransferEvent event){
		
		StringBuilder builder = new StringBuilder();  
        for(Object item : event.getItems()) {  
        	 builder.append(((Role) item).getRole()).append("<br />"); 
        }
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
	public void setSerachUserToRole(List<Users> serachUsers) {
		this.searchUsers = serachUsers;
	}
	public DualListModel<Role> getRoles() {
		return roles;
	}
	public void setRoles(DualListModel<Role> roles) {
		this.roles = roles;
	}		
	
	
}

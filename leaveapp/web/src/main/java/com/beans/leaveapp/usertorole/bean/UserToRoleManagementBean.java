package com.beans.leaveapp.usertorole.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.beans.leaveapp.common.security.role.model.Role;
import com.beans.leaveapp.common.security.usertorole.model.UserToRole;
import com.beans.leaveapp.common.security.usertorole.service.UserToRoleNotFound;
import com.beans.leaveapp.common.security.usertorole.service.UserToRoleService;
import com.beans.leaveapp.role.bean.RoleManagement;
import com.beans.leaveapp.usertorole.model.UserToRoleDataModel;

public class UserToRoleManagementBean implements Serializable{
	private static final long serialVersionUID = 1L;
	UserToRoleService userToRoleService;
	private List<UserToRole> userToRoleList;
	private UserToRoleDataModel userToRoleDataModel;
	private UserToRole newUserToRole = new UserToRole();	
	private UserToRole selectedUserToRole = new UserToRole();
	private boolean insertDelete = false;
	private List<UserToRole> searchUserToRole;
	private DualListModel<Role> roles;
	
	
	
	public UserToRoleService getUserToRoleService() {
		return userToRoleService;
	}
	public void setUserToRoleService(UserToRoleService userToRoleService) {
		this.userToRoleService = userToRoleService;
	}
	
	
	public List<UserToRole> getUserToRoleList() {
		
		if(userToRoleList == null || insertDelete == true ){
			
			userToRoleList = userToRoleService.findAll();
		}
		
		return userToRoleList;
	}
	public void setUserToRoleList(List<UserToRole> userToRoleList) {
		this.userToRoleList = userToRoleList;
	}
	
	
	public UserToRoleDataModel getUserToRoleDataModel() {
		if(userToRoleDataModel == null || insertDelete == true){			
			userToRoleDataModel = new UserToRoleDataModel(getUserToRoleList());			
		}		
		return userToRoleDataModel;
	}	
	
	public void setUserToRoleDataModel(UserToRoleDataModel userToRoleDataModel) {
		this.userToRoleDataModel = userToRoleDataModel;
	}
	
	
	public UserToRole getNewUserToRole() {		
		return newUserToRole;
	}
	
	public void setNewUserToRole(UserToRole newUserToRole) {
		this.newUserToRole = newUserToRole;
	}
	
	public void doCreateUserToRoleType() {
		
		newUserToRole.setDeleted(false);
		getUserToRoleService().create(newUserToRole);
		setInsertDelete(true);	
		
	}	
	
	public UserToRole getSelectedUserToRole() {
		return selectedUserToRole;
	}
	
	public void setSelectedUserToRole(UserToRole selectedUserToRole) {
		this.selectedUserToRole = selectedUserToRole;
	}
	
	
	public void doUpdateUserToRole() {
		try{
		System.out.println("New User Role:" + selectedUserToRole.getUserRole());
		System.out.println("User Id" + selectedUserToRole.getUserId());
		getUserToRoleService().update(selectedUserToRole);
		}catch(UserToRoleNotFound e){
			
			FacesMessage msg = new FacesMessage("Error" , "User Role With userId" + selectedUserToRole.getUserId() + "not found");
			 FacesContext.getCurrentInstance().addMessage(null, msg);  
		}		
	}
	
	public void onRowSelect(SelectEvent event){
		setSelectedUserToRole((UserToRole) event.getObject());
		FacesMessage msg = new FacesMessage("User Role Selected", selectedUserToRole.getUserRole());
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void doUserToRole() {
		try {
			getUserToRoleService().delete(selectedUserToRole.getUserId());
		} catch (UserToRoleNotFound e) {		
			FacesMessage msg = new FacesMessage("Error" , "User Role With userId" + selectedUserToRole.getUserId() + "not found");
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
	public List<UserToRole> getSearchUserToRole() {
		return searchUserToRole;
	}
	public void setSerachUserToRole(List<UserToRole> serachUserToRole) {
		this.searchUserToRole = serachUserToRole;
	}
	public DualListModel<Role> getRoles() {
		return roles;
	}
	public void setRoles(DualListModel<Role> roles) {
		this.roles = roles;
	}		
	
	
}

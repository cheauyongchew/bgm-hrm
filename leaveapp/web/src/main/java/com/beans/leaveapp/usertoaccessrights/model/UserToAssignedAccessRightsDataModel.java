package com.beans.leaveapp.usertoaccessrights.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.beans.common.security.usertoaccessrights.model.AssignedAccessRights;
import com.beans.common.security.usertoaccessrights.model.UserToAccessRights;


public class UserToAssignedAccessRightsDataModel extends ListDataModel<AssignedAccessRights> implements SelectableDataModel<UserToAccessRights>{

	
	UserToAssignedAccessRightsDataModel(){
		
	} 
	
	public UserToAssignedAccessRightsDataModel(List<AssignedAccessRights> data){
		
		super(data);
	}

//	@SuppressWarnings("unchecked")
	@Override
	public UserToAccessRights getRowData(String rowKey) {	
		return null;
	}
	
	@Override
	public Object getRowKey(UserToAccessRights userToAccessRights) {
		
		return userToAccessRights.getId();
	}	


}
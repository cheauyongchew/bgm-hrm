package com.beans.leaveapp.usertoaccessrights.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.beans.common.security.usertoaccessrights.model.UserToAccessRights;

public class UserToAssignedAccessRightsDataModel extends ListDataModel<UserToAccessRights> implements SelectableDataModel<UserToAccessRights>{

	
	UserToAssignedAccessRightsDataModel(){
		
	} 
	
	public UserToAssignedAccessRightsDataModel(List<UserToAccessRights> data){
		
		super(data);
	}
	
	
	
	@Override
	public Object getRowKey(UserToAccessRights object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserToAccessRights getRowData(String rowKey) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

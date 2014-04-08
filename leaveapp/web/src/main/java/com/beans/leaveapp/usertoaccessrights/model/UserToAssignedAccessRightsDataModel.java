package com.beans.leaveapp.usertoaccessrights.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.beans.common.security.users.model.Users;
import com.beans.common.security.usertoaccessrights.model.AssignedAccessRights;
import com.beans.common.security.usertoaccessrights.model.UserToAccessRights;

public class UserToAssignedAccessRightsDataModel extends ListDataModel<AssignedAccessRights> implements SelectableDataModel<AssignedAccessRights>{

	
	UserToAssignedAccessRightsDataModel(){
		
	} 
	
	public UserToAssignedAccessRightsDataModel(List<AssignedAccessRights> data){
		
		super(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AssignedAccessRights getRowData(String rowKey) {
		List<AssignedAccessRights> assignedAccessRightsList = (List<AssignedAccessRights>)getWrappedData();
		  Integer rowKeyInt = Integer.parseInt(rowKey);
	      for(AssignedAccessRights assignedAccessRights : assignedAccessRightsList) {
	          if(assignedAccessRights.getId() == rowKeyInt) {
	              return assignedAccessRights;
	          }
	      }
		return null;
	}
	
	@Override
	public Object getRowKey(AssignedAccessRights assignedAccessRights) {
		
		return assignedAccessRights.getId();
	}

	
	


}

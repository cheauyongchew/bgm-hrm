package com.beans.leaveapp.usertorole.model;

import java.util.List;

import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import com.beans.leaveapp.common.security.usertorole.model.UserToRole;

public class UserToRoleDataModel extends ListDataModel<UserToRole> implements SelectableDataModel<UserToRole> {
	
   public UserToRoleDataModel(){  	   
   }
   
   public UserToRoleDataModel(List<UserToRole> data) {
       super(data);
   }  
   
   
			@SuppressWarnings("unchecked")
			@Override
			public UserToRole getRowData(String rowKey) {
			    
				  List<UserToRole> userToRoleList = (List<UserToRole>)getWrappedData();
				  Integer rowKeyInt = Integer.parseInt(rowKey);
			      for(UserToRole userToRole : userToRoleList) {
			          if(userToRole.getUserId() == rowKeyInt) {
			              return userToRole;
			          }
			      }
				return null;
			}
			
			@Override
			public Object getRowKey(UserToRole userToRole) {
			
				return userToRole.getUserId();
			}
	
}

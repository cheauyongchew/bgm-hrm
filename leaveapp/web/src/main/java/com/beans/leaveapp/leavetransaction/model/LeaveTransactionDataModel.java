package com.beans.leaveapp.leavetransaction.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class LeaveTransactionDataModel extends ListDataModel<AdminLeaveTransaction> implements
		SelectableDataModel<AdminLeaveTransaction> {

	public LeaveTransactionDataModel(){
	
		
	}
	
	public LeaveTransactionDataModel(List<AdminLeaveTransaction> data){
		super(data);
	}
	
	@Override
	public Object getRowKey(AdminLeaveTransaction object) {
		// TODO Auto-generated method stub
		return object.getId();
	}

	@Override
	public AdminLeaveTransaction getRowData(String rowKey) {
		// TODO Auto-generated method stub
		return null;
	}

}

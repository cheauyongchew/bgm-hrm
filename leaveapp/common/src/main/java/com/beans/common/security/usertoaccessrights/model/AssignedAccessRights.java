package com.beans.common.security.usertoaccessrights.model;

public class AssignedAccessRights {

	private int id;
	private String assignedAccessRights;
	private boolean enabled;
	
	
	public AssignedAccessRights(int id, String assignedAccessRights, boolean enabled) {
		super();
		this.id = id;
		this.assignedAccessRights = assignedAccessRights;
		this.enabled = enabled;
	}
	
	public String getAssignedAccessRights() {
		return assignedAccessRights;
	}
	
	public void setAssignedAccessRights(String assignedAccessRights) {
		this.assignedAccessRights = assignedAccessRights;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	
}
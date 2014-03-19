package com.beans.common.security.role.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.beans.common.security.users.model.Users;


@Entity
@Table(name="Role")
public class Role {

	 private int id;
	 private String role;
	 private boolean isDeleted= false;
	 private List<Users> userList;
	 
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="role", nullable=false)
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	@Column(name="isDeleted", columnDefinition="TINYINT(1)")
	@Type(type="org.hibernate.type.NumericBooleanType")
	public boolean isDeleted() {
		return isDeleted;
	}


	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

 
	 
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
	public List<Users> getUserList() {
		return userList;
	}


	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}	
	
}

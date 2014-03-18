package com.beans.leaveapp.common.audit.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;


@Entity
@Table(name="systemaudittrail")
public class SystemAuditTrail {
	
	private int id;
	private Date date;
	private int actorUserId;
	private String description;
	private boolean isDeleted = false;
	
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="actionDate", nullable=false)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="actorUserId", nullable=false)
	public int getActorUserId() {
		return actorUserId;
	}
	public void setActorUserId(int actotUserId) {
		this.actorUserId = actotUserId;
	}
	
	@Column(name="description",nullable=false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="iSDeleted", columnDefinition="TINYINT(1)") 
	@Type(type="org.hibernate.type.NumericBooleanType")
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	

}

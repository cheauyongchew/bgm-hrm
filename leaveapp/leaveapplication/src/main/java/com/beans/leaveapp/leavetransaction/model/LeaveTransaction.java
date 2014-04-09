package com.beans.leaveapp.leavetransaction.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name="LeaveTransaction")
public class LeaveTransaction {

	private int id;
	private Date applicationDate;
	private Date  startDateTime;
	private Date  endDateTime;
	private Double numberOfHours;
	private Double numberOfDays;
	private String reason;
	private int leaveTypeId;
	private int employeeId;
	
	private boolean isDeleted;
	
	public LeaveTransaction(int id, Date applicationDate,
			Date startDateTime, Date endDateTime,
			Double numberOfHours, Double numberOfDays, String reason,
			int leaveTypeId, int employeeId, boolean isDeleted) {
		super();
		this.id = id;
		this.applicationDate = applicationDate;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.numberOfHours = numberOfHours;
		this.numberOfDays = numberOfDays;
		this.reason = reason;
		this.leaveTypeId = leaveTypeId;
		this.employeeId = employeeId;
	
		this.isDeleted = isDeleted;
	}
	public LeaveTransaction() {
		
	}
	
	@Id
	@GeneratedValue
	@Column( name="id",nullable=false,unique=true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="applicationDate",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(java.util.Date applicationDate) {
		
		this.applicationDate = applicationDate;
	}
	
	@Column(name="startDateTime",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	
	@Column(name="endDateTime",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	@Column(name="numberOfHours",nullable=false)
	public Double getNumberOfHours() {
		return numberOfHours;
	}
	public void setNumberOfHours(Double numberOfHours) {
		this.numberOfHours = numberOfHours;
	}
	
	@Column(name="numberOfDays",nullable=false)
	public Double getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(Double numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	
	@Column(name="reason",nullable=false)
	public String getReason() {
		return reason;
	} 
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Column(name="isdeleted", columnDefinition="TINYINT(1)") 
	@Type(type="org.hibernate.type.NumericBooleanType")
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	

	
	@Column(name="leaveTypeId",nullable=false)
	public int getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	

	@Column(name="employeeId",nullable=false)
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
}

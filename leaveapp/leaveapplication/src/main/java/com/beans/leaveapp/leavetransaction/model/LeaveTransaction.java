package com.beans.leaveapp.leavetransaction.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.leaveapplicationcomment.model.LeaveApplicationComment;
import com.beans.leaveapp.leavetype.model.LeaveType;

@Entity
@Table(name="LeaveTransaction")
public class LeaveTransaction implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date applicationDate;
	private Date  startDateTime;
	private Date  endDateTime;
	private Double numberOfHours;
	private Double numberOfDays;
	private String reason;
	private LeaveType leaveTypeId;
	private Employee employeeId;
	private String name;
	private List<LeaveApplicationComment> leaveApplicationComments;
	private Long taskId;
	private boolean isDeleted;
	
	public LeaveTransaction(int id, Date applicationDate,
			Date startDateTime, Date endDateTime,
			Double numberOfHours, Double numberOfDays, String reason,
			LeaveType leaveTypeId, Employee employeeId, boolean isDeleted) {
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
	
	@Column(name="numberOfHours",nullable=true)
	public Double getNumberOfHours() {
		return numberOfHours;
	}
	public void setNumberOfHours(Double numberOfHours) {
		this.numberOfHours = numberOfHours;
	}
	
	@Column(name="numberOfDays",nullable=true)
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
	

	@OneToOne
	@JoinColumn(name="leaveTypeId",nullable=false)
	public LeaveType getLeaveTypeId(){
		return leaveTypeId;
	}
	public void setLeaveTypeId(LeaveType leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	
    @OneToOne
	@JoinColumn(name="employeeId")
	public Employee getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="taskId", nullable=true)
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="leaveTransaction")
	public List<LeaveApplicationComment> getLeaveApplicationComments() {
		return leaveApplicationComments;
	}
	public void setLeaveApplicationComments(
			List<LeaveApplicationComment> leaveApplicationComments) {
		this.leaveApplicationComments = leaveApplicationComments;
	}
}

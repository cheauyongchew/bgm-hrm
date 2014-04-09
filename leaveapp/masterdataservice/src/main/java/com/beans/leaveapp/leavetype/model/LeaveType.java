package com.beans.leaveapp.leavetype.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.beans.leaveapp.employeetype.model.EmployeeType;


@Entity
@Table(name="LeaveType")
public class LeaveType {
	private int id;
	private String name;
	private String description;
	private double entitlement;
	private boolean isAccountable = false;
	private boolean isDeleted= false;
	private EmployeeType employeeTypeId; 

	
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description", nullable=true)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Column(name="entitlement", nullable=false)
	public double getEntitlement() {
		return entitlement;
	}
	public void setEntitlement(double entitlement) {
		this.entitlement = entitlement;
	}
	
	@Column(name="isAccountable",columnDefinition="TINYINT(1)")
	@Type(type="org.hibernate.type.NumericBooleanType")
	public boolean isAccountable() {
		return isAccountable;
	}
	public void setAccountable(boolean isAccountable) {
		this.isAccountable = isAccountable;
	}
	@Column(name="isDeleted", columnDefinition="TINYINT(1)") 
	@Type(type="org.hibernate.type.NumericBooleanType")
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
    @OneToOne
	@JoinColumn(name="employeeTypeId")
	public EmployeeType getEmployeeTypeId() {
		return employeeTypeId;
	}
	public void setEmployeeTypeId(EmployeeType employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	
	
	
	
	
	
	
}

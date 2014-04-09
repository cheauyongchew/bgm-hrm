package com.beans.leaveapp.yearlyentitlement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.leavetype.model.LeaveType;

@Entity
@Table(name="YearlyEntitlement")
public class YearlyEntitlement {

		
		private int id;
	//	private int employeeId;
	//	private int leaveTypeId;
		private double entitlement;
		private double availableBalance;
		private boolean isDeleted = false;
		private Employee employee;
		private LeaveType leaveType;
		
		
		
		@Id
		@GeneratedValue
		@Column(name="id", nullable=false, unique=true)
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
	/*	@Column(name="employeeId",nullable=false)
		public int getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}
		*/
	/*	@Column(name="leaveTypeId",nullable=false)
		public int getLeaveTypeId() {
			return leaveTypeId;
		}
		public void setLeaveTypeId(int leaveTypeId) {
			this.leaveTypeId = leaveTypeId;
		}*/
		
		@Column(name="entitlement",nullable=false)
		public double getEntitlement() {
			return entitlement;
		}
		public void setEntitlement(double entitlement) {
			this.entitlement = entitlement;
		}
		
		@Column(name="leaveBalance",nullable=false)
		public double getAvailableBalance() {
			return availableBalance;
		}
		public void setAvailableBalance(double availableBalance) {
			this.availableBalance = availableBalance;
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
		@JoinColumn(name="employeeId")
		public Employee getEmployee() {
			return employee;
		}
		public void setEmployee(Employee employee) {
			this.employee = employee;
		}
		
		
		@OneToOne
		@JoinColumn(name="leaveTypeId")
		public LeaveType getLeaveType() {
			return leaveType;
		}
		public void setLeaveType(LeaveType leaveType) {
			this.leaveType = leaveType;
		}
		
}

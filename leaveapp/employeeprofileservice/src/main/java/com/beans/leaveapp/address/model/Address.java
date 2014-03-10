package com.beans.leaveapp.address.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name="address")
public class Address {
	private int id;
	private String addessType;
	private String line1;
	private String line2;
	private String line3;
	private String city;
	private String state;
	private String country;
	private String postcode;
	private boolean isDeleted= false;
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="addresstype", nullable=false)
	public String getAddessType() {
		return addessType;
	}
	public void setAddessType(String addessType) {
		this.addessType = addessType;
	}
	
	@Column(name="line1", nullable=true)
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	
	@Column(name="line2", nullable=true)
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	
	@Column(name="line3", nullable=true)
	public String getLine3() {
		return line3;
	}
	public void setLine3(String line3) {
		this.line3 = line3;
	}
	
	@Column(name="city", nullable=true)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="state", nullable=true)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="country", nullable=false)
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name="postcode", nullable=true)
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Column(name="isDeleted", columnDefinition="TINYINT(1)") 
	@Type(type="org.hibernate.type.NumericBooleanType")
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}

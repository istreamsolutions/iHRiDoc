package com.istream.ihr.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.istream.ihr.util.JsonDateDeserializer;
import com.istream.ihr.util.JsonDateSerializer;

public class EmployeeAddressRes {
	
	private Integer addressId;
	private Integer employeeID;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date effectiveDate;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date termDate;
	
	private Date creationTs;
	private Date lastUpdateTs;
	
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	public Date getTermDate() {
		return termDate;
	}
	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}
	
	public Date getCreationTs() {
		return creationTs;
	}
	public void setCreationTs(Date creationTs) {
		this.creationTs = creationTs;
	}
	
	public Date getLastUpdateTs() {
		return lastUpdateTs;
	}
	public void setLastUpdateTs(Date lastUpdateTs) {
		this.lastUpdateTs = lastUpdateTs;
	}
}

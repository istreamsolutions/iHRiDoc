package com.istream.ihr.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.istream.ihr.util.JsonDateDeserializer;
import com.istream.ihr.util.JsonDateSerializer;

public class EmployeeImmigrationDetailRes {
	
	private Integer empImmDtlId;
	private Integer employeeID;
	private String immType;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date startDate;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date endDate;
	
	private Date creationTs;
	private Date lastUpdateTs;
	
	public Integer getEmpImmDtlId() {
		return empImmDtlId;
	}
	public void setEmpImmDtlId(Integer empImmDtlId) {
		this.empImmDtlId = empImmDtlId;
	}
	
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getImmType() {
		return immType;
	}
	public void setImmType(String immType) {
		this.immType = immType;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

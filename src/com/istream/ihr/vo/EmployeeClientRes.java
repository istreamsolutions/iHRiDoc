package com.istream.ihr.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.istream.ihr.util.JsonDateDeserializer;
import com.istream.ihr.util.JsonDateSerializer;

public class EmployeeClientRes {
	
	private Integer empClientId;
	private String clientName;
	private Integer employeeID;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date startDate;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date endDate;
	
	private Date creationTs;
	private Date lastUpdateTs;
	
	public Integer getEmpClientId() {
		return empClientId;
	}
	public void setEmpClientId(Integer empClientId) {
		this.empClientId = empClientId;
	}
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
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

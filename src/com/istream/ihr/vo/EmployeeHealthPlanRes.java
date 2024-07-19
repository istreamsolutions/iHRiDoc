package com.istream.ihr.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.istream.ihr.util.JsonDateDeserializer;
import com.istream.ihr.util.JsonDateSerializer;

public class EmployeeHealthPlanRes {
	
	private Integer empPlanId;
	private Integer employeeID;
	private String carrierName;
	private String planType;
	private short numCovdMembers;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date effectiveDate;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date termDate;
	
	private Date creationTs;
	private Date lastUpdateTs;
	
	public Integer getEmpPlanId() {
		return empPlanId;
	}
	public void setEmpPlanId(Integer empPlanId) {
		this.empPlanId = empPlanId;
	}
	
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	
	public short getNumCovdMembers() {
		return numCovdMembers;
	}
	public void setNumCovdMembers(short numCovdMembers) {
		this.numCovdMembers = numCovdMembers;
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

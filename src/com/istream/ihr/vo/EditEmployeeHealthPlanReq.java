package com.istream.ihr.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.istream.ihr.util.JsonDateDeserializer;
import com.istream.ihr.util.JsonDateSerializer;

public class EditEmployeeHealthPlanReq {
	
	private Integer empPlanId;
	private String planType;
	private short numCovdMembers;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date effectiveDate;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date termDate;
	
	public Integer getEmpPlanId() {
		return empPlanId;
	}
	public void setEmpPlanId(Integer empPlanId) {
		this.empPlanId = empPlanId;
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
}

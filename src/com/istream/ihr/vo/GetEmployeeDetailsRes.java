package com.istream.ihr.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.istream.ihr.util.JsonDateDeserializer;
import com.istream.ihr.util.JsonDateSerializer;

public class GetEmployeeDetailsRes {
	
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String email;
	private String homePhone;
	private String mobilePhone;
	private String workPhone;
	private String ssn;
	private String gender;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date birthDate;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date startDate;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date endDate;
	
	private BigDecimal baseSalaryAmt;
	private BigDecimal bonusAmt;
	private String designation;
	private Date creationTs;
	private Date lastUpdateTs;
	
	private EmployeeAddressRes empAddress = new EmployeeAddressRes();
	private EmployeeClientRes empClient = new EmployeeClientRes();
	private EmployeeHealthPlanRes empHealthPlan = new EmployeeHealthPlanRes();
	private EmployeeImmigrationDetailRes empImmigrationDtl = new EmployeeImmigrationDetailRes();
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	
	public BigDecimal getBaseSalaryAmt() {
		return baseSalaryAmt;
	}
	public void setBaseSalaryAmt(BigDecimal baseSalaryAmt) {
		this.baseSalaryAmt = baseSalaryAmt;
	}
	
	public BigDecimal getBonusAmt() {
		return bonusAmt;
	}
	public void setBonusAmt(BigDecimal bonusAmt) {
		this.bonusAmt = bonusAmt;
	}
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
	
	public EmployeeAddressRes getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(EmployeeAddressRes empAddress) {
		this.empAddress = empAddress;
	}
	
	public EmployeeClientRes getEmpClient() {
		return empClient;
	}
	public void setEmpClient(EmployeeClientRes empClient) {
		this.empClient = empClient;
	}
	
	public EmployeeHealthPlanRes getEmpHealthPlan() {
		return empHealthPlan;
	}
	public void setEmpHealthPlan(EmployeeHealthPlanRes empHealthPlan) {
		this.empHealthPlan = empHealthPlan;
	}
	
	public EmployeeImmigrationDetailRes getEmpImmigrationDtl() {
		return empImmigrationDtl;
	}
	public void setEmpImmigrationDtl(EmployeeImmigrationDetailRes empImmigrationDtl) {
		this.empImmigrationDtl = empImmigrationDtl;
	}
	
	
}

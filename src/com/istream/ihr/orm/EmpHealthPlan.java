package com.istream.ihr.orm;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "emp_health_plan", catalog = "ihr")
public class EmpHealthPlan implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer empPlanId;
	private Integer employeeID;
	private HealthCarrier healthCarrier;
	private String planType;
	private short numCovdMembers;
	private Date effectiveDate;
	private Date termDate;
	private Date creationTs;
	private Date lastUpdateTs;
	
	public EmpHealthPlan() {
	}
	
	public EmpHealthPlan(Integer empPlanId, Integer employeeId, HealthCarrier healthCarrier, String planType, short numCovdMembers,
			Date effectiveDate, Date termDate, Date creationTs, Date lastUpdateTs) {
		this.empPlanId = empPlanId;
		this.employeeID = employeeId;
		this.healthCarrier = healthCarrier;
		this.planType = planType;
		this.numCovdMembers = numCovdMembers;
		this.effectiveDate = effectiveDate;
		this.termDate = termDate;
		this.creationTs = creationTs;
		this.lastUpdateTs = lastUpdateTs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "emp_plan_id", unique = true, nullable = false)
	public Integer getEmpPlanId() {
		return this.empPlanId;
	}

	public void setEmpPlanId(Integer empPlanId) {
		this.empPlanId = empPlanId;
	}

	@Column(name = "employee_id", nullable = false)
	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}


	@ManyToOne
	@JoinColumn(name = "Carrier_id", nullable = false)
	public HealthCarrier getHealthCarrier() {
		return this.healthCarrier;
	}

	public void setHealthCarrier(HealthCarrier healthCarrier) {
		this.healthCarrier = healthCarrier;
	}

	@Column(name = "Plan_type", nullable = false, length = 20)
	public String getPlanType() {
		return this.planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	@Column(name = "Num_covd_members", nullable = false)
	public short getNumCovdMembers() {
		return this.numCovdMembers;
	}

	public void setNumCovdMembers(short numCovdMembers) {
		this.numCovdMembers = numCovdMembers;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Effective_date", nullable = false, length = 0)
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Term_date", length = 0)
	public Date getTermDate() {
		return this.termDate;
	}

	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Creation_ts", nullable = false, length = 0)
	public Date getCreationTs() {
		return this.creationTs;
	}

	public void setCreationTs(Date creationTs) {
		this.creationTs = creationTs;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Last_update_ts", nullable = false, length = 0)
	public Date getLastUpdateTs() {
		return this.lastUpdateTs;
	}

	public void setLastUpdateTs(Date lastUpdateTs) {
		this.lastUpdateTs = lastUpdateTs;
	}

}

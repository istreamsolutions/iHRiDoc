package com.istream.ihr.orm;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "emp_immigration_dtl", catalog = "ihr")
public class EmpImmigrationDtl implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer empImmDtlId;
	private Integer employeeID;
	private Integer immiTypeId;
	private Date startDate;
	private Date endDate;
	private Date creationTs;
	private Date lastUpdateTs;
	
	public EmpImmigrationDtl() {
	}

	public EmpImmigrationDtl(Integer empImmDtlId, Integer employeeID, Integer immiTypeId, Date startDate, Date endDate, Date creationTs, Date lastUpdateTs) {
		this.empImmDtlId = empImmDtlId;
		this.employeeID = employeeID;
		this.immiTypeId = immiTypeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationTs = creationTs;
		this.lastUpdateTs = lastUpdateTs;
	}

	@Column(name = "employee_id", nullable = false)
	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Emp_imm_dtl_id", unique = true, nullable = false)
	public Integer getEmpImmDtlId() {
		return this.empImmDtlId;
	}

	public void setEmpImmDtlId(Integer empImmDtlId) {
		this.empImmDtlId = empImmDtlId;
	}



	@Column(name = "Immi_type_id", nullable = false, length = 11)
	public Integer getImmiTypeId() {
		return this.immiTypeId;
	}

	public void setImmiTypeId(Integer immiTypeId) {
		this.immiTypeId = immiTypeId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false, length = 0)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", nullable = false, length = 0)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

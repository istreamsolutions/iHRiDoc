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
@Table(name = "emp_client_as", catalog = "ihr")
public class EmpClientAs implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer empClientId;
	private Client client;
	private Integer employeeID;
	private Date startDate;
	private Date endDate;
	private Date creationTs;
	private Date lastUpdateTs;
	
	public EmpClientAs() {
	}

	public EmpClientAs(Integer empClientId, Client client, Integer employeeID, Date startDate, Date endDate, Date creationTs,
			Date lastUpdateTs) {
		this.empClientId = empClientId;
		this.client = client;
		this.employeeID = employeeID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationTs = creationTs;
		this.lastUpdateTs = lastUpdateTs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Emp_client_id", unique = true, nullable = false)
	public Integer getEmpClientId() {
		return this.empClientId;
	}

	public void setEmpClientId(Integer empClientId) {
		this.empClientId = empClientId;
	}

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Column(name = "emp_id", nullable = false)
	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Start_date", nullable = false, length = 0)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "End_date", length = 0)
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

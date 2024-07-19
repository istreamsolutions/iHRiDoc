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
@Table(name = "health_carrier", catalog = "ihr")
public class HealthCarrier implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer carrierId;
	private String name;
	private String address;
	private Date startDate;
	private Date endDate;
	private Date creationTs;
	private Date lastUpdateTs;
	
	public HealthCarrier() {
	}

	public HealthCarrier(Integer carrierId, String name, String address, Date startDate, Date endDate, Date creationTs, Date lastUpdateTs) {
		this.carrierId = carrierId;
		this.name = name;
		this.address = address;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationTs = creationTs;
		this.lastUpdateTs = lastUpdateTs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "carrier_id", unique = true, nullable = false)
	public Integer getCarrierId() {
		return this.carrierId;
	}

	public void setCarrierId(Integer carrierId) {
		this.carrierId = carrierId;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Address", nullable = false, length = 250)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

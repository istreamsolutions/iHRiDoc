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
@Table(name = "client", catalog = "ihr")
public class Client implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer clientId;
	private String name;
	private String address;
	private Date effectiveDate;
	private Character type;
	private Date termDate;
	private Date creationTs;
	private Date lastUpdateTs;

	public Client() {
	}
	
	public Client(Integer clientId, String name, String address, Date effectiveDate, Character type, Date termDate, Date creationTs, Date lastUpdateTs) {
		this.clientId = clientId;
		this.name = name;
		this.address = address;
		this.effectiveDate = effectiveDate;
		this.type = type;
		this.termDate = termDate;
		this.creationTs = creationTs;
		this.lastUpdateTs = lastUpdateTs;
		
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "client_id", unique = true, nullable = false)
	public Integer getClientId() {
		return this.clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	@Column(name = "Name", nullable = false, length = 100)
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
	@Column(name = "effective_date", nullable = false, length = 0)
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Column(name = "Type", length = 1)
	public Character getType() {
		return this.type;
	}

	public void setType(Character type) {
		this.type = type;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "term_date", length = 0)
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

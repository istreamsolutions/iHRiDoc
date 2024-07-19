package com.istream.idoc.orm;


import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the immigration_type database table.
 * 
 */
@Entity
@Table(name="immigration_type")
@NamedQuery(name="ImmigrationType.findAll", query="SELECT i FROM ImmigrationType i")
public class ImmigrationType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="immi_type_id")
	@GeneratedValue(strategy = IDENTITY)
	private int immiTypeId;

	@Column(name="country")
	private String country;

	
	@Column(name="description")
	private String description;

	@Column(name="immi_type_nm")
	private String immiTypeNm;

	public ImmigrationType() {
	}

	public int getImmiTypeId() {
		return this.immiTypeId;
	}

	public void setImmiTypeId(int immiTypeId) {
		this.immiTypeId = immiTypeId;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImmiTypeNm() {
		return this.immiTypeNm;
	}

	public void setImmiTypeNm(String immiTypeNm) {
		this.immiTypeNm = immiTypeNm;
	}

}
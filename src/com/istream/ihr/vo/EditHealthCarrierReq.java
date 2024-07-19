package com.istream.ihr.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.istream.ihr.util.JsonDateDeserializer;
import com.istream.ihr.util.JsonDateSerializer;

public class EditHealthCarrierReq implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer carrierId;
	private String name;
	private String address;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date startDate;
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date endDate;
	
	public Integer getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(Integer carrierId) {
		this.carrierId = carrierId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
}

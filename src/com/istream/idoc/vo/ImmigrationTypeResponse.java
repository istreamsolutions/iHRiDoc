package com.istream.idoc.vo;

import java.math.BigInteger;
import java.util.List;

public class ImmigrationTypeResponse {
	
	private int immi_type_id;
	private String immi_type_nm;
	private String description;
	private String country;
	
	private List<FolderResponse> folders;
	
	public int getImmi_type_id() {
		return immi_type_id;
	}

	public void setImmi_type_id(int immi_type_id) {
		this.immi_type_id = immi_type_id;
	}

	public String getImmi_type_nm() {
		return immi_type_nm;
	}
	public void setImmi_type_nm(String immi_type_nm) {
		this.immi_type_nm = immi_type_nm;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public List<FolderResponse> getFolders() {
		return folders;
	}

	public void setFolders(List<FolderResponse> folders) {
		this.folders = folders;
	}
}

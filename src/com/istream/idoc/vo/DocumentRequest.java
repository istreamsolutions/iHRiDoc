package com.istream.idoc.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

public class DocumentRequest {
	
	private String document_uuid;
	private String document_name;
	private String document_type;
	private int folder_id;
	private String folderName;
	private int employee_id;
	private int Created_by;
	private int	Updated_by;
	private MultipartFile file;
	/**
	 * @return the document_uuid
	 */
	public String getDocument_uuid() {
		return document_uuid;
	}
	/**
	 * @param document_uuid the document_uuid to set
	 */
	public void setDocument_uuid(String document_uuid) {
		this.document_uuid = document_uuid;
	}
	/**
	 * @return the document_name
	 */
	public String getDocument_name() {
		return document_name;
	}
	/**
	 * @param document_name the document_name to set
	 */
	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}
	/**
	 * @return the document_type
	 */
	public String getDocument_type() {
		return document_type;
	}
	/**
	 * @param document_type the document_type to set
	 */
	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
	/**
	 * @return the folder_id
	 */
	public String getFolderName() {
		return folderName;
	}
	/**
	 * @param folder_id the folder_id to set
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	/**
	 * @return the employee_id
	 */
	public int getEmployee_id() {
		return employee_id;
	}
	/**
	 * @param employee_id the employee_id to set
	 */
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	/**
	 * @return the created_by
	 */
	public int getCreated_by() {
		return Created_by;
	}
	/**
	 * @param created_by the created_by to set
	 */
	public void setCreated_by(int created_by) {
		Created_by = created_by;
	}
	/**
	 * @return the updated_by
	 */
	public int getUpdated_by() {
		return Updated_by;
	}
	/**
	 * @param updated_by the updated_by to set
	 */
	public void setUpdated_by(int updated_by) {
		Updated_by = updated_by;
	}
	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}
	/**
	 * @return the folder_id
	 */
	public int getFolder_id() {
		return folder_id;
	}
	/**
	 * @param folder_id the folder_id to set
	 */
	public void setFolder_id(int folder_id) {
		this.folder_id = folder_id;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	


}

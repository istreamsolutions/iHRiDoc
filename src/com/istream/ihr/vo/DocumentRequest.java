package com.istream.ihr.vo;

public class DocumentRequest {

	
	private int docuementId;
	
	private int documentUuid;
	
	private String documentName; //Assumption bucketname = documentname; just to sync with the entity field
	
	private int categoryId;
	
	private String categoryName;
	
	private int subCategoryId;
	
	private String subCategoryName;
	
	private int fileId;
	
	private String fileName;

	public int getDocuementId() {
		return docuementId;
	}

	public void setDocuementId(int docuementId) {
		this.docuementId = docuementId;
	}

	public int getDocumentUuid() {
		return documentUuid;
	}

	public void setDocumentUuid(int documentUuid) {
		this.documentUuid = documentUuid;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}

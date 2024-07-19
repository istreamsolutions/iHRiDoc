package com.istream.idoc.vo;



public class FolderResponse {
	
	private int FOLDER_ID;
	private String FOLDER_NAME;
	private int PARENT_FOLDER_ID;
	private int IMMI_TYPE_ID;
	private String CREATED_BY;
	private String UPDATED_BY;
	
	public int getFOLDER_ID() {
		return FOLDER_ID;
	}
	public void setFOLDER_ID(int fOLDER_ID) {
		FOLDER_ID = fOLDER_ID;
	}
	
	public String getFOLDER_NAME() {
		return FOLDER_NAME;
	}
	public void setFOLDER_NAME(String fOLDER_NAME) {
		FOLDER_NAME = fOLDER_NAME;
	}

	public int getPARENT_FOLDER_ID() {
		return PARENT_FOLDER_ID;
	}
	public void setPARENT_FOLDER_ID(int pARENT_FOLDER_ID) {
		PARENT_FOLDER_ID = pARENT_FOLDER_ID;
	}
	
	public int getIMMI_TYPE_ID() {
		return IMMI_TYPE_ID;
	}
	public void setIMMI_TYPE_ID(int iMMI_TYPE_ID) {
		IMMI_TYPE_ID = iMMI_TYPE_ID;
	}

	public String getCREATED_BY() {
		return CREATED_BY;
	}
	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}

	public String getUPDATED_BY() {
		return UPDATED_BY;
	}
	public void setUPDATED_BY(String uPDATED_BY) {
		UPDATED_BY = uPDATED_BY;
	}

}

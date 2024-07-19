package com.istream.idoc.service;

import java.util.List;

import com.istream.idoc.orm.Document;
import com.istream.idoc.orm.Folder;
import com.istream.idoc.vo.DocumentRequest;
import com.istream.idoc.vo.DocumentResponse;
import com.istream.idoc.vo.FolderResponse;
import com.istream.idoc.vo.ImmigrationTypeResponse;

public interface DocumentService {
	
	public List<Folder> getFolderList(long immigrationTypeId);
	public DocumentResponse createDocument(DocumentRequest document);
	//TODO: get id converted to long
	public void deleteDocument(DocumentResponse document);
	
	
	//empty
	public void updateDocument(Document document);
	public void deleteDocument(Document document);
	public Document getDocumentById(long documentId);
	public List<DocumentResponse> getDocumentByEmployee(long employeeId);

	List<ImmigrationTypeResponse> getAllImmigrationFolder();
	List<FolderResponse> getFoldersByImmigrationType(int immiTypeId);
	List<DocumentResponse> getListOfDocumentsByFolder(int immigrationTypeId,int folderId);
	List<DocumentResponse> getListOfDocumentsWithoutFolder();

	public byte[] dowloadDocument(DocumentResponse document);

}

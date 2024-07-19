package com.istream.idoc.dao;

import java.util.List;

import com.istream.idoc.orm.Document;
import com.istream.idoc.orm.Folder;
import com.istream.idoc.orm.ImmigrationType;

public interface DocumentDao {

	public List<Document> getDocumentByEmployee(long employeeId);

	public void createDocument(Document document);
	
	public void deleteDocument(String document_uuid);

	List<Folder> getFoldersByImmigrationType(int immiType);

	List<ImmigrationType> getImmigrationTypes();
	
	List<Document> getDocumentByFolder(int immigrationTypeId,int folderId);

	List<Document> getDocumentWithoutFolder();
}

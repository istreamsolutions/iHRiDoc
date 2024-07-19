package com.istream.idoc.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.istream.idoc.dao.DocumentDao;
import com.istream.idoc.orm.Document;
import com.istream.idoc.orm.Folder;
import com.istream.idoc.orm.ImmigrationType;
import com.istream.idoc.service.DocumentService;
import com.istream.idoc.vo.DocumentRequest;
import com.istream.idoc.vo.DocumentResponse;
import com.istream.idoc.vo.FolderResponse;
import com.istream.idoc.vo.ImmigrationTypeResponse;
import com.istream.ihr.orm.Employee;

public class DocumentServiceImpl implements DocumentService {
	private FileStorageServiceImpl fileStorageService;
	
	private DocumentDao documentDao;
		
	public void setDocumentDao(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}

	public List<Folder> getFolderList(long immigrationTypeId){ //TODO: Modify return as per requirements
			return null;
	}
	
	@Transactional(readOnly=false)
	public DocumentResponse createDocument(DocumentRequest documentRequest){
		
		documentRequest.setDocument_uuid(UUID.randomUUID().toString());
		Document document = convertReqToDao(documentRequest);
		
		documentDao.createDocument(document);
		String fileType = documentRequest.getDocument_name().substring(documentRequest.getDocument_name().lastIndexOf("."));
		String fullFilePath=null;
		//IF No folder is given upload under generic folder
		if(StringUtils.isNotEmpty(documentRequest.getFolderName())) {
			fullFilePath="H1B/"+documentRequest.getFolderName()+"/"+documentRequest.getDocument_uuid()+fileType;
		} else {
			fullFilePath="Generic Documents/"+documentRequest.getDocument_uuid()+fileType;
		}
		System.out.println("Document Request Object:"+documentRequest);
		System.out.println("Full Path"+fullFilePath);
		try {
			fileStorageService.uploadDocumentByKey(documentRequest.getFile(),fullFilePath);
		} catch (Exception e) {
			System.out.println("***Error while calling uploadDocumentByKey***");
			e.printStackTrace();
		}
		
		System.out.println("Document Object to be persisted:"+document);
			//TODO: replace with no return void
			return new DocumentResponse();
		}
		
		private Document convertReqToDao(DocumentRequest newDocumentRequest) {
			
			Document document = new Document();
			
			//TODO: test
			//BeanUtils.copyProperties(newDocumentRes, document);
            document.setDocumentUuid(newDocumentRequest.getDocument_uuid());
            document.setDocumentName(newDocumentRequest.getDocument_name());
            document.setDocumentType(newDocumentRequest.getDocument_type());
            //document.setFolder_id(newDocumentRes.getFolder_id());
            
            //employee_id
            Employee employeeId= new Employee();
            if(newDocumentRequest.getEmployee_id() != 0) {
            
	            employeeId.setEmployeeId(newDocumentRequest.getEmployee_id());
	            document.setEmployee(employeeId);	            
            }
            
            //created By
            Employee createdBy= new Employee();
            createdBy.setEmployeeId(newDocumentRequest.getEmployee_id());
            document.setEmpCreatedBy(createdBy);
            
            Timestamp crationTime=new Timestamp(new Date().getTime());
			document.setCreationTs(crationTime);
			document.setLastUpdatedTs(crationTime);		
			
			Folder documentFolder= new Folder();
			if(newDocumentRequest.getFolder_id() != 0) {

	            documentFolder.setFolder_id(newDocumentRequest.getFolder_id());
	            document.setFolder(documentFolder);
			}

			return document;
	}

	@Transactional(readOnly=false)
	public void deleteDocument(DocumentResponse document){
		System.out.println("Service:::::Document Request for Delete::::::"+document);
		documentDao.deleteDocument(document.getUuid());
		String fileType = document.getFileName().substring(document.getFileName().lastIndexOf("."));
		String fullFilePath="";
		if(StringUtils.isNotEmpty(document.getFolderName())) {
			fullFilePath=document.getCategoryName()+"/"+document.getFolderName()+"/"+document.getUuid()+fileType;
		} else {
			fullFilePath="Generic Documents/"+document.getUuid()+fileType;

		}
		fileStorageService.deleteDocumentByKey(fullFilePath);
	}
	
		
   public void updateDocument(Document document){
		
	}
	public void deleteDocument(Document document){
		
	}
	public Document getDocumentById(long documentId){  //TODO: Modify return as per requirements
		return null;
		
	}
	
	@Override
	public List<DocumentResponse> getDocumentByEmployee(long employeeId){ 
		List<Document> docList = documentDao.getDocumentByEmployee(employeeId); 
		return convertDocumentsToResponseList(docList);
	}
	
	
	@Override
	public List<DocumentResponse> getListOfDocumentsByFolder(int immigartionTypeId,int folderId) {
		List<Document> docList = documentDao.getDocumentByFolder(immigartionTypeId,folderId);
		return convertDocumentsToResponseList(docList);
	}
	
	@Override
	public List<DocumentResponse> getListOfDocumentsWithoutFolder() {
		List<Document> docList = documentDao.getDocumentWithoutFolder();
		return convertDocumentsToResponseList(docList);
	}
	
	private List<DocumentResponse> convertDocumentsToResponseList(List<Document> docList) {
		List<DocumentResponse> responseList = new ArrayList<DocumentResponse>();
		if( docList != null && docList.size() >0 ) {
			for(Document doc : docList){
				DocumentResponse docRes = translateDocument(doc);
				responseList.add(docRes);
			}
		}
		return responseList;
	}
	
	@Override
	public List<FolderResponse> getFoldersByImmigrationType(int immiTypeId) {
		List<FolderResponse> resFolders = new ArrayList<FolderResponse>();
		List<Folder> folders = documentDao.getFoldersByImmigrationType(immiTypeId);
		if(folders != null && folders.size()>0) {
			for(Folder folder : folders) {
				FolderResponse folderRes = translateFolder(folder);
				resFolders.add(folderRes);
			}
		}
		return resFolders;
	}
	
	@Override
	public List<ImmigrationTypeResponse> getAllImmigrationFolder(){  
		List<ImmigrationTypeResponse> response = new ArrayList<ImmigrationTypeResponse>();
		List<ImmigrationType> immiTypes = documentDao.getImmigrationTypes();
		if(immiTypes !=null && immiTypes.size()>0) {
			for(ImmigrationType type: immiTypes) {
				ImmigrationTypeResponse immigrationTypeRes = translasteImmigrationType(type);
				List<FolderResponse> resFolders = getFoldersByImmigrationType(immigrationTypeRes.getImmi_type_id());
				immigrationTypeRes.setFolders(resFolders);
				response.add(immigrationTypeRes);
			}
		}
		return response;
	}
	
	private FolderResponse translateFolder(Folder folder){
		FolderResponse response = new FolderResponse();
		response.setFOLDER_ID(folder.getFolder_id());
		response.setFOLDER_NAME(folder.getFolder_name());
		if( folder.getParent_folder_id() != 0){
			response.setPARENT_FOLDER_ID(folder.getParent_folder_id());
		}
		response.setIMMI_TYPE_ID(folder.getImmi_type_id());
		/*
		System.out.println("Folder ID : " +folder.getFolder_id());
		System.out.println("Folder Name : " +folder.getFolder_name());
		System.out.println("Folder Immi Type ID : " +folder.getImmi_type_id());
		System.out.println("Folder Parent ID : " +folder.getParent_folder_id());
		*/
		
		return response;
	}
	
	private ImmigrationTypeResponse translasteImmigrationType(ImmigrationType type)
	{
		ImmigrationTypeResponse response = new ImmigrationTypeResponse();
		response.setImmi_type_id(type.getImmiTypeId());
		response.setImmi_type_nm(type.getImmiTypeNm());
		response.setCountry(type.getCountry());
		response.setDescription(type.getDescription());
		return response;
	}
	
	
	private DocumentResponse translateDocument(Document doc) {
		DocumentResponse response = new DocumentResponse();
		/*
		System.out.println("UUid : " +doc.getDocumentUuid());
		System.out.println("Name : " +doc.getDocumentName());
		System.out.println("Type : " +doc.getDocumentType());
		System.out.println("CreationTimeStamp : " +doc.getCreationTs());
		System.out.println("Folder : " +doc.getFolder());
		*/
		
		response.setUuid(doc.getDocumentUuid());
		response.setFileName(doc.getDocumentName());
		response.setFileType(doc.getDocumentType());
		response.setUploadDate(new Timestamp (doc.getCreationTs().getTime()));
		response.setCategoryName("H1B");
		if(doc.getFolder() != null && doc.getFolder().getFolder_id() != 0)
			response.setFolderName(doc.getFolder().getFolder_name());
		//response.setFolderRes(translateFolder(doc.getFolder()));
		
		return response;
	}

	@Override
	public byte[] dowloadDocument(DocumentResponse document) {
		
		String fileType = document.getFileName().substring(document.getFileName().lastIndexOf("."));
		String fullFilePath="";
		if(StringUtils.isNotEmpty(document.getFolderName())) {
			fullFilePath=document.getCategoryName()+"/"+document.getFolderName()+"/"+document.getUuid()+fileType;
		} else {
			fullFilePath="Generic Documents/"+document.getUuid()+fileType;

		}
		return fileStorageService.dowloadDocument(fullFilePath);
	}

	public FileStorageServiceImpl getFileStorageService() {
		return fileStorageService;
	}

	public void setFileStorageService(FileStorageServiceImpl fileStorageService) {
		this.fileStorageService = fileStorageService;
	}

	
	
	public DocumentDao getDocumentDao() {
		return documentDao;
	}

}

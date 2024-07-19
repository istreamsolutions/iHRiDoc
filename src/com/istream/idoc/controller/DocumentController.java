package com.istream.idoc.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.istream.idoc.exception.IDocRuntimeException;
import com.istream.idoc.service.DocumentService;
import com.istream.idoc.vo.DocumentRequest;
import com.istream.idoc.vo.DocumentResponse;
import com.istream.idoc.vo.FolderResponse;
import com.istream.ihr.orm.Employee;

@RestController
public class DocumentController {
	
	
	
	public List<Employee> getEmployeesWithDocument() {
		 List<Employee> empList = new ArrayList<Employee>();
		 return empList;
	}
	
	@Autowired
	DocumentService documentService;  // Service to create/retrieve/delete documents
   
	
	@RequestMapping(value = "/employee/attachFile", method = RequestMethod.POST, produces = "application/json")
	public void uploadFilesForEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		MultipartHttpServletRequest mRequest;
		
		try {
			
			mRequest = (MultipartHttpServletRequest) request;
			mRequest.getParameterMap();
			
			Iterator<String> itr = mRequest.getFileNames();
			MultipartFile mFile = mRequest.getFile(itr.next());
	        String fileName = mFile.getOriginalFilename();
	        System.out.println(fileName);

	        DocumentRequest req = new DocumentRequest();
	        req.setDocument_name(mFile.getOriginalFilename());
	        req.setDocument_type(mFile.getContentType());
	        
	        if(StringUtils.isNotEmpty(request.getParameter("documentTypeId")))
	        	req.setFolder_id(Integer.parseInt(request.getParameter("documentTypeId")));
	        
	        if(StringUtils.isNotEmpty(request.getParameter("documentType")))
	        	req.setFolderName(request.getParameter("documentType"));
	        
	        if(StringUtils.isNotEmpty(request.getParameter("employeeId")))
	        	req.setEmployee_id(Integer.parseInt(request.getParameter("employeeId")));
	        
	        req.setFile(mFile);
	        System.out.println(req.toString());
	        
	        documentService.createDocument(req);
	        
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new IDocRuntimeException(e);
    	}
		
		//return "Success";
	}
	
	@RequestMapping(value="/employee/downloadFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> dowloadS3Resource(DocumentResponse docResponse) throws Exception {
				
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf(docResponse.getFileType()));
		headers.setContentDispositionFormData("filename", docResponse.getFileName());
		
		byte[] documentData = documentService.dowloadDocument(docResponse);
		
	    return new ResponseEntity<byte[]>(documentData, headers, HttpStatus.OK);
	}
	
	//------------------- Retrieve documents based on employee id --------------------------------------------------------
    
    @RequestMapping(value = "/employee/documents/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentResponse>> getDocumentsByEmployeeId(@PathVariable("id") long employeeId) {
    	
        System.out.println("Fetching User with id " + employeeId);
        List<DocumentResponse> documentResponse = documentService.getDocumentByEmployee(employeeId);
        
        if(documentResponse.isEmpty()){
            return new ResponseEntity<List<DocumentResponse>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<DocumentResponse>>(documentResponse, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/category/documents/{immigrationTypeId}/{folderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentResponse>> getDocumentsByFolderId(@PathVariable("immigrationTypeId") int immigrationTypeId,@PathVariable("folderId") int folderId) {
    	
        System.out.println("Fetching User with Immigration Type ID: " + immigrationTypeId +" Folder ID: "+folderId);
        List<DocumentResponse> documentResponse = documentService.getListOfDocumentsByFolder(immigrationTypeId,folderId);
        
        if(documentResponse.isEmpty()){
            return new ResponseEntity<List<DocumentResponse>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<DocumentResponse>>(documentResponse, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/generic/documents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentResponse>> getGenericDocuments() {
    	
        System.out.println("Fetching Generic Documents");
        List<DocumentResponse> documentResponse = documentService.getListOfDocumentsWithoutFolder();
        
        if(documentResponse.isEmpty()){
            return new ResponseEntity<List<DocumentResponse>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<DocumentResponse>>(documentResponse, HttpStatus.OK);
    }

	
	//------------------- Create  Document --------------------------------------------------------
	
    @RequestMapping(value = "/document/", method = RequestMethod.POST)
    public ResponseEntity<DocumentResponse> createDocument(@RequestBody DocumentRequest document) {
        
    	DocumentResponse response = documentService.createDocument(document);
 
        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/employee/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
        return new ResponseEntity<DocumentResponse>(response, HttpStatus.CREATED);
    }
 
    
	//------------------- Delete  Document --------------------------------------------------------
	@RequestMapping(value = "/employee/documents/delete", method = RequestMethod.POST)
	public void deleteDocument(@RequestBody DocumentResponse document) {
		System.out.println("Controller:::::Document Request for Delete::::::"+document);
		documentService.deleteDocument(document);
    }

	@RequestMapping(value = "/document/folders/{id}", method = RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<List<FolderResponse>> getFoldersByImmigrationType(@PathVariable("id") int immiTypeId) {
		
		List<FolderResponse> response = documentService.getFoldersByImmigrationType(immiTypeId);
		return new ResponseEntity<List<FolderResponse>> (response, HttpStatus.OK);
	}
}

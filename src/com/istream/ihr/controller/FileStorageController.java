package com.istream.ihr.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.istream.idoc.service.FileStorageService;
import com.istream.idoc.vo.DocumentResponse;
import com.istream.ihr.vo.DocumentRequest;

@RestController
public class FileStorageController {

	@Autowired
	private FileStorageService fileStorageService;
	
	/*@RequestMapping(value = "/download", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseStatus( HttpStatus.OK)
    public void dowloadS3Resource(@RequestBody DocumentRequest documentRequest,
							      HttpServletResponse response) {
		
		//key="Performance/emp001/Performance Review 2016.docx";
		//bucket = "istream-idocs-documents";
		if (documentRequest != null) {
			String bucket = documentRequest.getDocumentName()+documentRequest.getDocumentUuid();
			String key = buildFilePath(documentRequest);
			try {
				//response.getOutputStream().write(fileStorageService.dowloadDocumentByKey(bucket, key));
				response.getOutputStream().write(fileStorageService.dowloadDocumentByKey(key));
				response.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
    }*/
	
	/*@RequestMapping(value="/employee/downloadFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> dowloadS3Resource(DocumentResponse docResponse) throws Exception {
				
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf(docResponse.getFileType()));
		headers.setContentDispositionFormData("filename", docResponse.getFileName());
		
		String key = buildFilePath(docResponse);
		byte[] documentData = fileStorageService.dowloadDocumentByKey(key);
		
	    return new ResponseEntity<byte[]>(documentData, headers, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseStatus( HttpStatus.ACCEPTED)
	public void deleteS3Resource(@RequestBody DocumentRequest documentRequest,
							      HttpServletResponse response) {
		if (documentRequest != null) {
			String bucket = documentRequest.getDocumentName()+documentRequest.getDocumentUuid();
			String key = buildFilePath(documentRequest);
			//fileStorageService.deleteDocumentByKey(bucket, key);
		}
    }
	

	private String buildFilePath(DocumentRequest documentRequest){
		
		Path path = Paths.get(documentRequest.getCategoryName(),documentRequest.getSubCategoryName(),documentRequest.getFileName());
		return (path.toString());
	}
	
	private String buildFilePath(DocumentResponse docRes){
		
		Path path = Paths.get("H1B", docRes.getCategoryName(), docRes.getFileName());
		return (path.toString());
	}
}

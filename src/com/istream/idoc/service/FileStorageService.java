 package com.istream.idoc.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	
	public void  uploadDocumentByKey(MultipartFile file, String key);
	public byte[] dowloadDocument(String key);
	public void deleteDocumentByKey(String key);
	
}

package com.istream.idoc.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.istream.idoc.exception.IDocRuntimeException;
import com.istream.idoc.service.FileStorageService;
import com.istream.idoc.service.S3Service;

public class FileStorageServiceImpl implements FileStorageService {
	
	private S3Service s3Service;
	
	
	
	/**
	 * @return the s3Service
	 */
	public S3Service getS3Service() {
		return s3Service;
	}

	/**
	 * @param s3Service the s3Service to set
	 */
	public void setS3Service(S3Service s3Service) {
		this.s3Service = s3Service;
	}

	@Override
	public void uploadDocumentByKey(MultipartFile multipartFile, String key) {
		File file = null;
		try {
			file = convertMultipartFileToFile(multipartFile);
			// TODO: get more info to upload file
			s3Service.uploadDocument( key, file);
			file.delete();
		} catch (Exception e) {
			file.delete();
			throw new IDocRuntimeException("Error while uploading file:File Key:"+key, e);
		}
	}

	@Override
	public byte[] dowloadDocument(String key) {
		
//		AmazonS3 amazonS3 = s3Service.initalizeAmazonS3ClientBuilder();
		byte[] s3FileByteStream = null;
		try {
			
			s3FileByteStream = s3Service.getDocument(key);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return s3FileByteStream;
	}

	@Override
	public void deleteDocumentByKey(String key) {
		try {
			s3Service.deleteDocument(key);
		} catch (Exception e) {
			throw new IDocRuntimeException("Error while deleting the file. File Key:"+key, e);
		}
	}

	
	private File convertMultipartFileToFile(MultipartFile file) throws IOException
	{    
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile(); 
	    FileOutputStream fos = new FileOutputStream(convFile); 
	    fos.write(file.getBytes());
	    fos.close(); 
	    return convFile;
	}
}

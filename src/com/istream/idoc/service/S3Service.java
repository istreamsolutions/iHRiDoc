package com.istream.idoc.service;

import java.io.File;

import com.amazonaws.services.s3.model.S3Object;

public interface S3Service {
	void uploadDocument(String keyOrSoCalledPath, File fileToBeUploaded) throws Exception ;

	byte[] getDocument(String keyWithFileName) throws Exception ;

	void writeS3ToFile(S3Object object, File file) throws Exception;
	
	void deleteDocument(String keyWithFileNameToBeDeleted) throws Exception;
	
//	public byte[] getS3ObjectByKey(String key);
	
		
}

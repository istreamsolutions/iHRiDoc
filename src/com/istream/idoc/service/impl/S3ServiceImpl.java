package com.istream.idoc.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.istream.idoc.service.S3Service;

public class S3ServiceImpl implements S3Service {

	private String bucketName;
	private AmazonS3 amazonS3;

	public S3ServiceImpl(String bucketName) {
		
		
		//this.amazonS3 = getS3Object(bucketName);
		this.bucketName = bucketName;
	}
	@Override
	public void uploadDocument(String keyOrSoCalledPath, File fileToBeUploaded) throws Exception {

		AmazonS3 s3 = getS3Object();
		PutObjectResult putObjectResult = s3.putObject(new PutObjectRequest(
				bucketName, keyOrSoCalledPath, fileToBeUploaded));

		// PutObjectResults can give you different things.
		// Example - version number of a document.
		putObjectResult.getVersionId();

	}

	@Override
	public byte[] getDocument(String keyWithFileName) throws Exception {
		AmazonS3 s3 = getS3Object();
		S3Object s3Object = s3.getObject(new GetObjectRequest(getBucketName(),
				keyWithFileName));

		byte[] s3ObjByteStream = null;
		try {
			if (s3Object != null){
				s3ObjByteStream = IOUtils.toByteArray(s3Object.getObjectContent());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s3ObjByteStream;
	}

	@Override
	public void writeS3ToFile(S3Object object, File file) throws Exception {
		InputStream objectData = null;
		InputStream reader = null;
		OutputStream writer = null;
		try {
			objectData = object.getObjectContent();
			reader = new BufferedInputStream(objectData);
			writer = new BufferedOutputStream(new FileOutputStream(file));

			int read = -1;
			while ((read = reader.read()) != -1) {
				writer.write(read);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (null != objectData) {
				objectData.close();
			}

			if (null != writer) {
				writer.flush();
				writer.close();
			}
			if (null != reader) {
				reader.close();
			}
		}
	}

	@Override
	public void deleteDocument(String keyWithFileNameToBeDeleted)
			throws Exception {
		AmazonS3 s3 = getS3Object();
		s3.deleteObject(getBucketName(), keyWithFileNameToBeDeleted);
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	// We have to initialize AmazonS3 object all the time is as
	// AmazonS3ClientBuilder object is
	// not ThreadSafe.
	private final AmazonS3 getS3Object() {
		

	    /*BasicAWSCredentials awsCreds = new BasicAWSCredentials(
	            "AKIAIKLEIZ3CVW6HIPXQ",
	            "LbgyBeuCwjH6YkLTXOAGZ5nzNnL3GoWF3wevCAEZ");
	    AmazonS3 s3 = AmazonS3ClientBuilder.standard()
	            .withRegion(Regions.US_EAST_1)
	            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
	            .build();
*/		        
		AmazonS3 s3 = AmazonS3ClientBuilder
				.standard()
				.withRegion(Regions.US_EAST_1)
				.withCredentials(
						DefaultAWSCredentialsProviderChain.getInstance())
				.build();

		return s3;
	}

	/*private final AmazonS3 getS3Object() {
		AWSCredentials awsCredentials = DefaultAWSCredentialsProviderChain
				.getInstance().getCredentials();
		BasicAWSCredentials awsCreds = null;

		if (null != awsCredentials
				&& StringUtils.isNotBlank(awsCredentials.getAWSAccessKeyId())
				&& StringUtils.isNotBlank(awsCredentials.getAWSSecretKey())) {
			awsCreds = new BasicAWSCredentials(
					awsCredentials.getAWSAccessKeyId(),
					awsCredentials.getAWSSecretKey());
		}

		// Note: AmazonS3ClientBuilder is not thread safe so you need to
		// Initialize every request.
		AmazonS3 s3 = null;
		if (null == awsCreds) {
			s3 = AmazonS3ClientBuilder
					.standard()
					.withRegion(Regions.US_EAST_1)
					.withCredentials(
							new InstanceProfileCredentialsProvider(true))
					.build();

		} else {
			s3 = AmazonS3ClientBuilder
					.standard()
					.withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
					.build();
		}

		return s3;
	}
	*/
	 /*public byte[] getS3ObjectByKey(String key){
		
         S3Object s3Object = amazonS3.getObject(
                 new GetObjectRequest(bucketName, key));
         byte[] s3ObjByteStream = null;
		try {
			if (s3Object != null){
				s3ObjByteStream = IOUtils.toByteArray(s3Object.getObjectContent());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s3ObjByteStream;
		 
	 }*/
	 
	
	/**
	 * @return the amazonS3
	 */
	public AmazonS3 getAmazonS3() {
		return amazonS3;
	}

	/**
	 * @param amazonS3 the amazonS3 to set
	 */
	public void setAmazonS3(AmazonS3 amazonS3) {
		this.amazonS3 = amazonS3;
	}

}

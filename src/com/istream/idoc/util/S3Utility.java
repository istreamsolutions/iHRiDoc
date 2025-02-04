package com.istream.idoc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.UUID;

import org.apache.commons.io.IOUtils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;


public class S3Utility {
	
	private String bucketName;
	private AmazonS3 amazonS3;
	
	public S3Utility(String bucketName) {
		amazonS3 = initalizeAmazonS3ClientBuilder(bucketName);
		this.bucketName=bucketName;
	}
	
	public String getBucketName() {
		return bucketName;
	}


	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public AmazonS3 getAmazonS3() {
		return amazonS3;
	}

	public static void main(String[] args) throws IOException {
	        /*
	         * Create your credentials file at ~/.aws/credentials (C:\Users\USER_NAME\.aws\credentials for Windows users) 
	         * and save the following lines after replacing the underlined values with your own.
	         *
	         * [default]
	         * aws_access_key_id = YOUR_ACCESS_KEY_ID
	         * aws_secret_access_key = YOUR_SECRET_ACCESS_KEY
	         */

        	String bucketName = "my-first-s3-bucket-" + UUID.randomUUID();
			S3Utility s3Utility = new S3Utility(bucketName);
//		    AmazonS3 s3 = initalizeAmazonS3ClientBuilder();
		    AmazonS3 s3 = s3Utility.getAmazonS3();
		   
//	        String bucketName = "my-first-s3-bucket-" + UUID.randomUUID();
	        String key = "MyObjectKey";

	        System.out.println("===========================================");
	        System.out.println("Getting Started with Amazon S3");
	        System.out.println("===========================================\n");

	        try {
	            /*
	             * Create a new S3 bucket - Amazon S3 bucket names are globally unique,
	             * so once a bucket name has been taken by any user, you can't create
	             * another bucket with that same name.
	             *
	             * You can optionally specify a location for your bucket if you want to
	             * keep your data closer to your applications or users.
	             */
	            System.out.println("Creating bucket " + bucketName + "\n");
	            s3.createBucket(bucketName);

	            /*
	             * List the buckets in your account
	             */
	            System.out.println("Listing buckets");
	            for (Bucket bucket : s3.listBuckets()) {
	                System.out.println(" - " + bucket.getName());
	            }
	            System.out.println();

	            /*
	             * Upload an object to your bucket - You can easily upload a file to
	             * S3, or upload directly an InputStream if you know the length of
	             * the data in the stream. You can also specify your own metadata
	             * when uploading to S3, which allows you set a variety of options
	             * like content-type and content-encoding, plus additional metadata
	             * specific to your applications.
	             */
	            System.out.println("Uploading a new object to S3 from a file\n");
	            s3.putObject(new PutObjectRequest(bucketName, key, createSampleFile()));

	            /*
	             * Download an object - When you download an object, you get all of
	             * the object's metadata and a stream from which to read the contents.
	             * It's important to read the contents of the stream as quickly as
	             * possibly since the data is streamed directly from Amazon S3 and your
	             * network connection will remain open until you read all the data or
	             * close the input stream.
	             *
	             * GetObjectRequest also supports several other options, including
	             * conditional downloading of objects based on modification times,
	             * ETags, and selectively downloading a range of an object.
	             */
	            System.out.println("Downloading an object");
	            S3Object object = s3.getObject(new GetObjectRequest(bucketName, key));
	            System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
	            displayTextInputStream(object.getObjectContent());

	            /*
	             * List objects in your bucket by prefix - There are many options for
	             * listing the objects in your bucket.  Keep in mind that buckets with
	             * many objects might truncate their results when listing their objects,
	             * so be sure to check if the returned object listing is truncated, and
	             * use the AmazonS3.listNextBatchOfObjects(...) operation to retrieve
	             * additional results.
	             */
	            System.out.println("Listing objects");
	            ObjectListing objectListing = s3.listObjects(new ListObjectsRequest()
	                    .withBucketName(bucketName)
	                    .withPrefix("My"));
	            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
	                System.out.println(" - " + objectSummary.getKey() + "  " +
	                        "(size = " + objectSummary.getSize() + ")");
	            }
	            System.out.println();

	            /*
	             * Delete an object - Unless versioning has been turned on for your bucket,
	             * there is no way to undelete an object, so use caution when deleting objects.
	             */
	            System.out.println("Deleting an object\n");
	            s3.deleteObject(bucketName, key);

	            /*
	             * Delete a bucket - A bucket must be completely empty before it can be
	             * deleted, so remember to delete any objects from your buckets before
	             * you try to delete them.
	             */
	            System.out.println("Deleting bucket " + bucketName + "\n");
	            s3.deleteBucket(bucketName);
	        } catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException, which means your request made it "
	                    + "to Amazon S3, but was rejected with an error response for some reason.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
	        } catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException, which means the client encountered "
	                    + "a serious internal problem while trying to communicate with S3, "
	                    + "such as not being able to access the network.");
	            System.out.println("Error Message: " + ace.getMessage());
	        }
	    }


	    /**
	     * Creates a temporary file with text data to demonstrate uploading a file
	     * to Amazon S3
	     *
	     * @return A newly created temporary file with text data.
	     *
	     * @throws IOException
	     */
	    private static File createSampleFile() throws IOException {
	        File file = File.createTempFile("aws-java-sdk-", ".txt");
	        file.deleteOnExit();

	        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
	        writer.write("abcdefghijklmnopqrstuvwxyz\n");
	        writer.write("01234567890112345678901234\n");
	        writer.write("!@#$%^&*()-=[]{};':',.<>/?\n");
	        writer.write("01234567890112345678901234\n");
	        writer.write("abcdefghijklmnopqrstuvwxyz\n");
	        writer.close();

	        return file;
	    }

	    /**
	     * Displays the contents of the specified input stream as text.
	     *
	     * @param input
	     *            The input stream to display as text.
	     *
	     * @throws IOException
	     */
	    private static void displayTextInputStream(InputStream input) throws IOException {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	        while (true) {
	            String line = reader.readLine();
	            if (line == null) break;

	            System.out.println("    " + line);
	        }
	        System.out.println();
	}
	    
	    
    private AmazonS3 initalizeAmazonS3ClientBuilder(String bucketName) {
    	 AmazonS3 s3 = new AmazonS3Client();
         Region usWest2 = Region.getRegion(Regions.US_EAST_1);
         s3.setRegion(usWest2);
         //s3.createBucket(bucketName);
         return s3;
		 /*AWSCredentials awsCredentials = DefaultAWSCredentialsProviderChain.getInstance().getCredentials();
		  BasicAWSCredentials  basicAwsCreds = null;
		  if (null != awsCredentials
	                && StringUtils.isNotBlank(awsCredentials.getAWSAccessKeyId())
	                && StringUtils.isNotBlank(awsCredentials.getAWSSecretKey())) {
			  basicAwsCreds = new BasicAWSCredentials(
	                    awsCredentials.getAWSAccessKeyId(),
	                    awsCredentials.getAWSSecretKey());
	      }
		  	
		  AmazonS3 amazonS3 = null;
		  if (null == basicAwsCreds) {
			  amazonS3 = AmazonS3ClientBuilder
	                    .standard()
	                    .withRegion(Regions.US_EAST_1)
	                    .withCredentials(new InstanceProfileCredentialsProvider(true))
	                    .build();
		  } else {
			  amazonS3 = AmazonS3ClientBuilder
	                    .standard()
	                    .withRegion(Regions.US_EAST_1)
	                    .withCredentials(new AWSStaticCredentialsProvider(basicAwsCreds))
	                    .build();
		  }
		  return amazonS3;*/
		  
	 }
	 
	 public byte[] getS3ObjectByKey(AmazonS3 amazonS3,String bucketName, String key){
		
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
		 
	 }
	 
	 public boolean deleteS3ObjectByKey(AmazonS3 amazonS3,String bucketName, String key){
			
		 try {
			 amazonS3.deleteObject(new DeleteObjectRequest(bucketName, key));
			 return true;
	        } catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
	            return false;
	        } catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException.");
	            System.out.println("Error Message: " + ace.getMessage());
	            return false;
	        }
	 }

	 public boolean uploadS3ObjectByKey(AmazonS3 amazonS3,String bucketName, String key, File file) {
			
		 try {
			 amazonS3.putObject(new PutObjectRequest(bucketName, key, file));
			 return true;
	        } catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
	            return false;
	        } catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException.");
	            System.out.println("Error Message: " + ace.getMessage());
	            return false;
	        }
	 }
}

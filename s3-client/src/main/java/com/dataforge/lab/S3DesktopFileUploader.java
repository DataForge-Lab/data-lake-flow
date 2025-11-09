package com.dataforge.lab;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.FileUpload;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;

public class S3DesktopFileUploader {

	private static final String BUCKET_NAME = "data-lake-flow-raw-ingest";
	private static final String FILE_PATH = "./data/";
	private static final Logger logger = LoggerFactory.getLogger(S3DesktopFileUploader.class);
	private static final String PROFILE_NAME = "your-aws-profile"; // the new one
	private static final Region REGION = Region.EU_WEST_1; // adjust for your bucket
	private static final String FILE_NAME = "raw-input.csv"; // test file name
	private static final String S3_FOLDER = "uploads"; // test file name

	public static void main(String[] args) {
		// Call the multipart upload function
		multipartUploadWithTransferManager(FILE_PATH + FILE_NAME);
	}

	public static void multipartUploadWithTransferManager(String filePath) {
		String key = S3_FOLDER + "/" + FILE_NAME;

		// Create an asynchronous S3 client using your profile
		S3AsyncClient s3AsyncClient = S3AsyncClient.builder().region(REGION)
				.credentialsProvider(ProfileCredentialsProvider.create(PROFILE_NAME)).build();

		// Pass the async client into TransferManager
		S3TransferManager transferManager = S3TransferManager.builder().s3Client(s3AsyncClient).build();

		UploadFileRequest uploadFileRequest = UploadFileRequest.builder()
				.putObjectRequest(b -> b.bucket(BUCKET_NAME).key(key)).source(Paths.get(filePath)).build();

		try {
			FileUpload fileUpload = transferManager.uploadFile(uploadFileRequest);
			fileUpload.completionFuture().join(); // wait until upload finishes
			logger.info("File uploaded successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transferManager.close();
			s3AsyncClient.close();
		}
	}

}

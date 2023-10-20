package com.example.demooraclegcp.repository;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;

import lombok.extern.log4j.Log4j2;

import com.google.cloud.storage.Storage;


@Repository
@Log4j2
public class GCPRepository {
    @Value("${gcp.project_id}")
    String projectId;
    @Value("${gcp.bucket_name}")
    String bucketName;
    @Value("${filepath}")
    String path;

    public void upload(String filename) throws IOException{
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    	BlobId blobId = BlobId.of(bucketName,filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.createFrom(blobInfo, Paths.get(path+filename));
        log.info("File " + filename + " upload in bucket "+ bucketName); 
    }

}

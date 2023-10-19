package com.example.demo.repository;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Repository
public class GcpStorageUpload {
    @Value("${gcp.project_id}")
    private String projectId;
    @Value("${gcp.bucket_name}")
    private String bucketName="mzv_test";

    public void upload(String path, String filename) throws IOException{
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    	BlobId blobId = BlobId.of(bucketName,filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        String filePath = path + filename;
        storage.createFrom(blobInfo, Paths.get(filePath));
    }

}
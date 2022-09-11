package com.example.spring.practice.service;

import com.example.spring.practice.service.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename){
        return fileDir+filename;
    }
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }

        String originFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originFileName,storeFileName);
    }

    public List<UploadFile> storeFileList(List<MultipartFile> multipartFileList) throws IOException {
        List<UploadFile> uploadFiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFileList) {
            UploadFile uploadFile = storeFile(multipartFile);
            uploadFiles.add(uploadFile);
        }
        return uploadFiles;
    }
    private String createStoreFileName(String originFileName) {
        String ext = extractExt(originFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid +"."+ext;
    }

    private String extractExt(String originFileName) {
        int pos = originFileName.lastIndexOf(".");
        return originFileName.substring(pos+1);
    }
}

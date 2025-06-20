package org.flow.flowAssign.fileUpload.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    public void saveFile(MultipartFile file, String fileName, String filePath);
}

package org.flow.flowAssign.fileUpload.service;

import lombok.extern.slf4j.Slf4j;
import org.flow.flowAssign.fileExtensions.service.ExtensionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private ExtensionsService extensionsService;

    @Override
    public void saveFile(MultipartFile file, String fileName, String filePath) {
        try{
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);

            if(extensionsService.findByName(ext) == null){
                throw new IllegalArgumentException("허용되지 않은 확장자입니다.");
            }
            String baseDir = System.getProperty("user.dir");
            String uploadPath = baseDir + File.separator + "upload";

            File dir = new File(uploadPath);
            if (!dir.exists()){
                dir.mkdirs();
            }

            String savePath = uploadPath + File.separator + file.getOriginalFilename();
            file.transferTo(new File(savePath));
        }catch (IOException e){
            e.printStackTrace();
            log.info("파일 업로드 실패");
        }
    }
}

package org.flow.flowAssign.fileUpload.control;

import org.flow.flowAssign.fileExtensions.service.ExtensionsService;
import org.flow.flowAssign.fileUpload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    @Value("${file.upload-dir}")
    private String fileUploadDir;

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/uploadFile")
    public String uploadFile() {
        return "uploadFile";
    }

    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam("fileUploadInput") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        try{
            fileUploadService.saveFile(file,file.getOriginalFilename(),fileUploadDir);
        }catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/uploadFile";
        }
        return "redirect:/uploadFile";
    }
}

package org.flow.flowAssign.file.control;

import org.flow.flowAssign.file.dto.ExtensionsDTO;
import org.flow.flowAssign.file.service.ExtensionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Controller
public class ExtensionsController {
    @Autowired
    ExtensionsService extensionsService;

    @GetMapping("/extensionSetting")
    public String extensionSetting(Model model) {
        List<ExtensionsDTO> extensionsDTOList = extensionsService.findAll();
        System.out.println("extensionsDTOList: " + extensionsDTOList);
        model.addAttribute("extensions", extensionsDTOList);
        return "index";
    }

    @GetMapping("/uploadFile")
    public String uploadFile() {
        return "uploadFile";
    }

    @PostMapping("/extensions")
    @ResponseBody
    public ResponseEntity<List<ExtensionsDTO>> getExtensions() {
        List<ExtensionsDTO> extensions = extensionsService.findAll();
        System.out.println("extensions : " + extensions);
        return ResponseEntity.ok().body(extensions);
    }

    @PostMapping("/extensionsAdd")
    public ResponseEntity extensionsAdd(@RequestBody ExtensionsDTO extensionsDTO) {
        System.out.println("extensionsDTO: " + extensionsDTO);
        if(extensionsService.findByName(extensionsDTO.getName()) != null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미 존재하는 확장자입니다.");
        }
        System.out.println(extensionsService.findByName(extensionsDTO.getName()));
        try {
            extensionsService.save(extensionsDTO);
            return ResponseEntity.status(200).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러 발생");
        }
    }

    @PostMapping("/extensionsDelete")
    public ResponseEntity extensionsDelete(@RequestBody Map<String, String> extensionName) {
        System.out.println("extensionName: " + extensionName.get("name"));
        try {
            extensionsService.deleteByName(extensionName.get("name"));
            return ResponseEntity.status(200).build();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러 발생");
        }
    }

    @PostMapping("/updateExtensionsCheckY")
    public ResponseEntity updateExtensionsCheckY(@RequestBody ExtensionsDTO extensionsDTO) {
        try{
            System.out.println("updateExtensionsCheckY + extensionsDTO: " + extensionsDTO);
            extensionsService.updateChecked(extensionsDTO);
            return ResponseEntity.status(200).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러 발생");
        }
    }

    @PostMapping("/updateExtensionsCheckN")
    public ResponseEntity updateExtensionsCheckN(@RequestBody ExtensionsDTO extensionsDTO) {
        try{
            System.out.println("updateExtensionsCheckN + extensionsDTO: " + extensionsDTO);
            extensionsService.updateUnChecked(extensionsDTO);
            return ResponseEntity.status(200).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러 발생");
        }
    }
}

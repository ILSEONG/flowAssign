package org.flow.flowAssign.fileExtensions.control;

import org.flow.flowAssign.fileExtensions.dto.ExtensionsDTO;
import org.flow.flowAssign.fileExtensions.service.ExtensionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ExtensionsController {
    @Autowired
    private ExtensionsService extensionsService;

    @GetMapping("/extensionSetting")
    public String extensionSetting(Model model) {
        List<ExtensionsDTO> extensionsDTOList = extensionsService.findAll();
        model.addAttribute("extensions", extensionsDTOList);
        return "index";
    }


    @PostMapping("/extensions")
    @ResponseBody
    public ResponseEntity<List<ExtensionsDTO>> getExtensions() {
        List<ExtensionsDTO> extensions = extensionsService.findAll();
        return ResponseEntity.ok().body(extensions);
    }

    @PostMapping("/extensionsAdd")
    public ResponseEntity extensionsAdd(@RequestBody ExtensionsDTO extensionsDTO) {
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
        try {
            extensionsService.deleteByName(extensionName.get("name"));
            return ResponseEntity.status(200).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러 발생");
        }
    }

    @PostMapping("/updateExtensionsCheckY")
    public ResponseEntity updateExtensionsCheckY(@RequestBody ExtensionsDTO extensionsDTO) {
        try{
            extensionsService.updateChecked(extensionsDTO);
            return ResponseEntity.status(200).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러 발생");
        }
    }

    @PostMapping("/updateExtensionsCheckN")
    public ResponseEntity updateExtensionsCheckN(@RequestBody ExtensionsDTO extensionsDTO) {
        try{
            extensionsService.updateUnChecked(extensionsDTO);
            return ResponseEntity.status(200).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러 발생");
        }
    }
}

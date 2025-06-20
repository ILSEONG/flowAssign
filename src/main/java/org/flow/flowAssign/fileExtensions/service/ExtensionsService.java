package org.flow.flowAssign.fileExtensions.service;


import org.flow.flowAssign.fileExtensions.dto.ExtensionsDTO;

import java.util.List;

public interface ExtensionsService {
    List<ExtensionsDTO> findAll();
    void save(ExtensionsDTO extensionsDTO);
    ExtensionsDTO findByName(String name);
    // 체크 해제
    void updateUnChecked(ExtensionsDTO extensionsDTO);
    // 체크
    void updateChecked(ExtensionsDTO extensionsDTO);
    // 삭제
    void deleteByName(String extensionName);
}

package org.flow.flowAssign.file.service;

import org.flow.flowAssign.file.dto.ExtensionsDTO;
import org.flow.flowAssign.file.entity.Extensions;
import org.flow.flowAssign.file.repository.ExtensionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExtensionsServiceImpl implements ExtensionsService{

    @Autowired
    private ExtensionsRepository extensionsRepository;

    Extensions extensions = new Extensions();

    @Override
    public List<ExtensionsDTO> findAll() {
        List<Extensions> extensionList = extensionsRepository.findAllByOrderByNameAsc();
        List<ExtensionsDTO> extensionsDTOList = new ArrayList<>();
        for (Extensions extension : extensionList) {
            ExtensionsDTO extensionsDTO = extension.toDto();
            extensionsDTOList.add(extensionsDTO);
        }
        return extensionsDTOList;
    }

    // 저장
    @Override
    public void save(ExtensionsDTO extensionsDTO) {
        extensions = extensionsDTO.toEntity();
        extensionsRepository.save(extensions);
    }

    @Override
    public ExtensionsDTO findByName(String name) {
        extensions = extensionsRepository.findByName(name);
        if(extensions == null) return null;
        return extensions.toDto();
    }

    @Override
    public void updateUnChecked(ExtensionsDTO extensionsDTO) {
        extensions = extensionsDTO.toEntity();
        extensionsRepository.updateIsUsedNByName(extensions.getName());
    }

    @Override
    public void updateChecked(ExtensionsDTO extensionsDTO) {
        extensions = extensionsDTO.toEntity();
        extensionsRepository.updateIsUsedYByName(extensions.getName());
    }

    @Override
    public void deleteByName(String extensionName) {
        extensionsRepository.deleteByName(extensionName);
    }


}

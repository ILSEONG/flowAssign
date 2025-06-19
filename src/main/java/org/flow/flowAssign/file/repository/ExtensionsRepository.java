package org.flow.flowAssign.file.repository;

import org.flow.flowAssign.file.entity.Extensions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ExtensionsRepository extends JpaRepository<Extensions, String> {
    List<Extensions> findAll();

    @Modifying
    @Transactional
    @Query("UPDATE Extensions e SET e.isUsed = 'n' WHERE e.name = :name")
    void updateIsUsedNByName(Extensions extensions);

    @Modifying
    @Transactional
    @Query("UPDATE Extensions e SET e.isUsed = 'y' WHERE e.name = :name")
    void updateIsUsedYByName(Extensions extensions);

    @Modifying
    @Transactional
    @Query("DELETE Extensions e WHERE e.name = :extensionName")
    void deleteByName(@Param("extensionName") String extensionName);

}

package org.flow.flowAssign.fileExtensions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.flow.flowAssign.fileExtensions.dto.ExtensionsDTO;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder @ToString
@Table(name = "extensions")
public class Extensions {
    // 1. 확장자 이름
    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    // 2. 사용 가능한지 체크
    @Column(name = "isUsed", nullable = false)
    private char isUsed;

    // 3. 고정 확장자인지
    @Column(name = "isFixed", nullable = false)
    private char isFixed;

    // Entity to DTO
    public ExtensionsDTO toDto(){
        return ExtensionsDTO.builder()
                .name(this.getName())
                .isUsed(this.getIsUsed())
                .isFixed(this.getIsFixed())
                .build();
    }
}

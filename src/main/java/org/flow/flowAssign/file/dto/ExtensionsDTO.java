package org.flow.flowAssign.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flow.flowAssign.file.entity.Extensions;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtensionsDTO {
    // 확장자 이름
    private String name;
    // 사용 가능한지 체크
    private char isUsed;
    // 고정 확장자인지
    private char isFixed;

    // DTO to Entity
    public Extensions toEntity(){
        return Extensions.builder()
                .name(this.getName())
                .isUsed(this.getIsUsed())
                .isFixed(this.isFixed)
                .build();
    }
}

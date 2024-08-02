package org.project.kakaotalk.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomTypeEnum {

    PRIVATE("개인"),
    GROUP("그룹"),
    ;

    private final String value;
}

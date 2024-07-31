package org.project.moduleapi.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateRoomDto {

    private UUID roomId;
    private Long userId;
}

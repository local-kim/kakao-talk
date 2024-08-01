package org.project.kakaotalk.api.dto.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class CreateRoomResponseDto {

    private UUID roomId;
}

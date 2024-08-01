package org.project.kakaotalk.api.dto.input;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class CreateRoomInputDto {

    private UUID roomId;
    private Long userId;
}

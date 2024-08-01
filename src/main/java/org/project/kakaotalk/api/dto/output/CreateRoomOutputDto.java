package org.project.kakaotalk.api.dto.output;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class CreateRoomOutputDto {

    private UUID roomId;
}

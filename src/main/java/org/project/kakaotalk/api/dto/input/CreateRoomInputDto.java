package org.project.kakaotalk.api.dto.input;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateRoomInputDto {

    private Long inviterId;

    private Long inviteeId;
}

package org.project.kakaotalk.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateRoomRequestDto {

    @NotNull
    private Long inviterId;

    @NotNull
    private Long inviteeId;
}

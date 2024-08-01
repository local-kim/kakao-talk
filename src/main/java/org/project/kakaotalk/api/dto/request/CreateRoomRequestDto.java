package org.project.kakaotalk.api.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;

@Getter
public class CreateRoomRequestDto {

    @NotNull
    private UUID roomId;

    @NotNull
    private Long userId;
}

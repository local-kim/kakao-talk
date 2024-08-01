package org.project.kakaotalk.api.service;

import java.util.UUID;
import org.project.kakaotalk.api.dto.input.CreateRoomInputDto;
import org.project.kakaotalk.api.dto.output.CreateRoomOutputDto;

public interface RoomService {

    CreateRoomOutputDto createRoom(CreateRoomInputDto input);
}

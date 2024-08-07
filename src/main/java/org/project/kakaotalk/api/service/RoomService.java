package org.project.kakaotalk.api.service;

import org.project.kakaotalk.api.dto.input.CreateRoomInputDto;
import org.project.kakaotalk.api.dto.output.CreateRoomOutputDto;
import org.project.kakaotalk.api.dto.output.FindAllRoomsByUserOutputDto;

public interface RoomService {

    CreateRoomOutputDto createRoom(CreateRoomInputDto input);

    FindAllRoomsByUserOutputDto findAllRoomsByUser(Long userId);
}

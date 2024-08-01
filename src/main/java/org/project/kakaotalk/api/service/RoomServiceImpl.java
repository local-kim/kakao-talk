package org.project.kakaotalk.api.service;

import java.util.UUID;
import org.project.kakaotalk.api.dto.input.CreateRoomInputDto;
import org.project.kakaotalk.api.dto.output.CreateRoomOutputDto;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Override
    public CreateRoomOutputDto createRoom(CreateRoomInputDto input) {
        return CreateRoomOutputDto.builder()
            .roomId(input.getRoomId())
            .build();
//        return null;
    }
}

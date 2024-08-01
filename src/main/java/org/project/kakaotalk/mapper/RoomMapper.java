package org.project.kakaotalk.mapper;

import org.mapstruct.Mapper;
import org.project.kakaotalk.api.dto.input.CreateRoomInputDto;
import org.project.kakaotalk.api.dto.output.CreateRoomOutputDto;
import org.project.kakaotalk.api.dto.request.CreateRoomRequestDto;
import org.project.kakaotalk.api.dto.response.CreateRoomResponseDto;

@Mapper
public interface RoomMapper {

    CreateRoomInputDto requestToInput(CreateRoomRequestDto requestDto);
    CreateRoomResponseDto outputToResponse(CreateRoomOutputDto outputDto);
}

package org.project.kakaotalk.api.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.kakaotalk.api.dto.input.CreateRoomInputDto;
import org.project.kakaotalk.api.dto.output.CreateRoomOutputDto;
import org.project.kakaotalk.api.dto.request.CreateRoomRequestDto;
import org.project.kakaotalk.api.dto.response.CreateRoomResponseDto;
import org.project.kakaotalk.api.service.RoomService;
import org.project.kakaotalk.mapper.RoomMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RoomController {

    private final RoomMapper roomMapper;
    private final RoomService roomService;

    /**
     *
     * @param request
     * @return
     */
    @PostMapping("/api/room")
    ResponseEntity<CreateRoomResponseDto> createRoom(@RequestBody CreateRoomRequestDto request) {

        CreateRoomInputDto input = roomMapper.requestToInput(request);
        CreateRoomOutputDto output = roomService.createRoom(input);
        CreateRoomResponseDto response = roomMapper.outputToResponse(output);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(response);
    }
}

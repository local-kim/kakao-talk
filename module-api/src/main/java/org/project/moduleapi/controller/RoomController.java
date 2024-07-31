package org.project.moduleapi.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.moduleapi.dto.CreateRoomDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RoomController {

    @GetMapping("/api/room")
    ResponseEntity<?> createRoom(CreateRoomDto request) {

        return ResponseEntity.created(URI.create("/api/room/" + request.getRoomId()))
            .build();
    }
}

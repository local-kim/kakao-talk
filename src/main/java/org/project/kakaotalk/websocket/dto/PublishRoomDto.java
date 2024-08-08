package org.project.kakaotalk.websocket.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.project.kakaotalk.entity.RoomEntity;

@Getter
@Builder
public class PublishRoomDto {

    private RoomEntity roomEntity;

    private List<Long> userIdList;
}

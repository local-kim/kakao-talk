package org.project.kakaotalk.api.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindAllRoomsByUserResponseDto {

    private List<FindAllRoomsByUserInnerRoomResponseDto> roomList;

    @Getter
    @Builder
    public static class FindAllRoomsByUserInnerRoomResponseDto {
        private Long roomId;
        private String title;
        private Integer participantCount;
//        private LocalTime lastMessageTime;
//        private String lastMessageContent;
    }
}

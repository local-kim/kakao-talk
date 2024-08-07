package org.project.kakaotalk.api.dto.output;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindAllRoomsByUserOutputDto {

    private List<FindAllRoomsByUserInnerRoomOutputDto> roomList;

    @Getter
    @Builder
    public static class FindAllRoomsByUserInnerRoomOutputDto {
        private Long roomId;
        private String title;
        private Integer participantCount;
//        private LocalTime lastMessageTime;
//        private String lastMessageContent;
    }
}

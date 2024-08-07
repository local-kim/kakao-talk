package org.project.kakaotalk.api.dto.repository;

public interface FindAllRoomsByUserRepositoryDto {

    Long getRoomId();
    String getTitle();
    Integer getParticipantCount();
//    LocalTime getLastMessageTime;
//    String getLastMessageContent;
}

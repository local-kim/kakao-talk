package org.project.kakaotalk.repository;

import java.util.List;
import org.project.kakaotalk.api.dto.repository.FindAllRoomsByUserRepositoryDto;
import org.project.kakaotalk.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {

    @Query(value = "select user.id, r.room_id, r.title, r.participantCount "
        + "from user "
        + "inner join participant on participant.user_id = user.id "
        + "inner join "
            + "(select room_id, ifnull(title, group_concat(if(user.id = :userId, null, name) separator ', ')) as title, count(room_id) as participantCount "
            + "from room "
            + "inner join participant on participant.room_id = room.id "
            + "inner join user on user.id = participant.user_id "
            + "group by room_id) r "
        + "on r.room_id = participant.room_id "
        + "where user.id = :userId", nativeQuery = true)
    List<FindAllRoomsByUserRepositoryDto> findAllByUser(Long userId);
}

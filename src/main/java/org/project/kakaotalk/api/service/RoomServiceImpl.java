package org.project.kakaotalk.api.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.project.kakaotalk.api.dto.input.CreateRoomInputDto;
import org.project.kakaotalk.api.dto.output.CreateRoomOutputDto;
import org.project.kakaotalk.api.dto.output.FindAllRoomsByUserOutputDto;
import org.project.kakaotalk.api.dto.output.FindAllRoomsByUserOutputDto.FindAllRoomsByUserInnerRoomOutputDto;
import org.project.kakaotalk.entity.ParticipantEntity;
import org.project.kakaotalk.entity.RoomEntity;
import org.project.kakaotalk.entity.UserEntity;
import org.project.kakaotalk.global.enums.RoomTypeEnum;
import org.project.kakaotalk.global.exception.CustomException;
import org.project.kakaotalk.global.exception.ErrorEnum;
import org.project.kakaotalk.repository.ParticipantRepository;
import org.project.kakaotalk.repository.RoomRepository;
import org.project.kakaotalk.repository.UserRepository;
import org.project.kakaotalk.websocket.dto.PublishRoomDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RoomServiceImpl implements RoomService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ParticipantRepository participantRepository;

    /**
     *
     * @param input
     * @return
     */
    @Transactional
    @Override
    public CreateRoomOutputDto createRoom(CreateRoomInputDto input) {

        // TODO : PRIVATE, GROUP 채팅 모두 고려

        RoomEntity roomEntity = RoomEntity.builder()
            .type(RoomTypeEnum.PRIVATE)
            .build();

        // insert 채팅방
        roomRepository.save(roomEntity);

        // select 유저(초대한 사람, 초대받은 사람)
        UserEntity inviterEntity = userRepository.findById(input.getInviterId())
            .orElseThrow(() -> new CustomException(ErrorEnum.NOT_FOUND, "초대한 유저를 찾을 수 없음"));

        UserEntity inviteeEntity = userRepository.findById(input.getInviteeId())
            .orElseThrow(() -> new CustomException(ErrorEnum.NOT_FOUND, "초대받은 유저를 찾을 수 없음"));

        // insert 참여(초대한 사람, 초대받은 사람)
        ParticipantEntity participantEntity1 = ParticipantEntity.builder()
            .roomEntity(roomEntity)
            .userEntity(inviterEntity)
            .build();

        participantRepository.save(participantEntity1);

        ParticipantEntity participantEntity2 = ParticipantEntity.builder()
            .roomEntity(roomEntity)
            .userEntity(inviteeEntity)
            .build();

        participantRepository.save(participantEntity2);

        // 채팅방 발행 - /sub/room/{userId}
        PublishRoomDto publishRoomDto = PublishRoomDto.builder()
            .roomEntity(roomEntity)
            .userIdList(List.of(inviteeEntity.getId()))
            .build();

        redisTemplate.convertAndSend("room", publishRoomDto);

        return CreateRoomOutputDto.builder()
            .roomId(roomEntity.getId())
            .build();
    }

    @Override
    public FindAllRoomsByUserOutputDto findAllRoomsByUser(Long userId) {

        List<FindAllRoomsByUserOutputDto.FindAllRoomsByUserInnerRoomOutputDto> roomList = participantRepository.findAllByUser(userId).stream().map(room ->
            FindAllRoomsByUserInnerRoomOutputDto.builder()
                .roomId(room.getRoomId())
                .title(room.getTitle())
                .participantCount(room.getParticipantCount())
                .build()
        ).toList();

        return FindAllRoomsByUserOutputDto.builder()
            .roomList(roomList)
            .build();
    }
}

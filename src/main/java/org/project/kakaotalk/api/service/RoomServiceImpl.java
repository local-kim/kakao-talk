package org.project.kakaotalk.api.service;

import lombok.RequiredArgsConstructor;
import org.project.kakaotalk.api.dto.input.CreateRoomInputDto;
import org.project.kakaotalk.api.dto.output.CreateRoomOutputDto;
import org.project.kakaotalk.entity.ParticipantEntity;
import org.project.kakaotalk.entity.RoomEntity;
import org.project.kakaotalk.entity.UserEntity;
import org.project.kakaotalk.global.enums.RoomTypeEnum;
import org.project.kakaotalk.global.exception.CustomException;
import org.project.kakaotalk.global.exception.ErrorEnum;
import org.project.kakaotalk.repository.ParticipantRepository;
import org.project.kakaotalk.repository.RoomRepository;
import org.project.kakaotalk.repository.UserRepository;
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
            .room(roomEntity)
            .user(inviterEntity)
            .build();

        participantRepository.save(participantEntity1);

        ParticipantEntity participantEntity2 = ParticipantEntity.builder()
            .room(roomEntity)
            .user(inviteeEntity)
            .build();

        participantRepository.save(participantEntity2);

        // 채팅방 발행 - /sub/user/{userId}
        redisTemplate.convertAndSend("room", roomEntity);

        return CreateRoomOutputDto.builder()
            .roomId(roomEntity.getId())
            .build();
    }
}

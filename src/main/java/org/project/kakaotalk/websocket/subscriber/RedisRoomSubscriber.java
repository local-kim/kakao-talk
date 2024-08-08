package org.project.kakaotalk.websocket.subscriber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.kakaotalk.websocket.dto.PublishRoomDto;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisRoomSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String body = redisTemplate.getStringSerializer().deserialize(message.getBody());
            PublishRoomDto publishRoomDto = objectMapper.readValue(body, PublishRoomDto.class);

            publishRoomDto.getUserIdList().forEach(userId ->
                    messagingTemplate.convertAndSend("/sub/room/" + userId, publishRoomDto.getRoomEntity())   // TODO: 2 -> userId로 변경
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

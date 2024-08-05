package org.project.kakaotalk.websocket.publisher;

import lombok.RequiredArgsConstructor;
import org.project.kakaotalk.entity.MessageEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Transactional
    public void sendMessage(Long roomId, MessageEntity message) {
//        message.setRoomId(roomId);
        redisTemplate.convertAndSend("message", message);
    }
}

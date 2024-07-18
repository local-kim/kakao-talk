package org.project.modulewebsocket;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic channelTopic;

    @Transactional
    public void sendMessage(Long roomId, MessageEntity message) {
        message.setRoomId(roomId);
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }
}

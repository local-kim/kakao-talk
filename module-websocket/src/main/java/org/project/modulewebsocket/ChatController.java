package org.project.modulewebsocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.debug("소켓 연결됨");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        log.debug("소켓 연결 끊어짐");
    }

    /**
     * /pub/message로 보낸 메세지를 받아서 /sub/message를 구독 중인 클라이언트에게 보내는 메서드
     * @param roomId
     * @param message
     * @return MessageEntity를 보냄
     */
    @MessageMapping("/message/{roomId}")
    @SendTo("/sub/message/{roomId}")
    public MessageEntity sendMessage(@DestinationVariable Long roomId, MessageEntity message) {
        return MessageEntity.builder()
            .body(message.getBody())
            .build();
    }
}

package org.project.kakaotalk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@RequiredArgsConstructor
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 메세지 브로커에 대한 설정을 하는 메서드
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");    // 메세지 브로커가 클라이언트에게 메세지를 전달할 URI
        registry.setApplicationDestinationPrefixes("/pub"); // 클라이언트가 메세지 브로커에게 메세지를 발행할 URI
    }

    /**
     * 웹소켓 서버에 대한 설정을 하는 메서드
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")   // 웹소켓에 연결할 때 사용할 Endpoint
            .setAllowedOrigins("*");
    }

    /**
     * 웹소켓 요청을 인터셉트하여 처리할 핸들러를 지정하는 메서드
     * @param registration
     */
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(stompHandler);
//    }
}

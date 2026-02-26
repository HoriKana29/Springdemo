package org.example.springdemo.configuration;

import lombok.AllArgsConstructor;
import org.example.springdemo.Repository.UserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@AllArgsConstructor
public class WebsocketEventListener {

    private final UserRepository userRepository;
    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void onConnect(SessionConnectedEvent event) {
        userRepository.incrementUserCount();
        messagingTemplate.convertAndSend("/topic/user-number",userRepository.getUserCount());
    }

    @EventListener
    public void onDisconnect(SessionDisconnectEvent event) {
        userRepository.decrementUserCount();
        messagingTemplate.convertAndSend("/topic/user-number",userRepository.getUserCount());
    }
}

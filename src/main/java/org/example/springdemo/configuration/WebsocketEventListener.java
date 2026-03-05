package org.example.springdemo.configuration;

import lombok.AllArgsConstructor;
import org.example.springdemo.Repository.UserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.UUID;

@Component
@AllArgsConstructor
public class WebsocketEventListener {

    private final UserRepository userRepository;
    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void onConnect(SessionConnectedEvent event) {
        userRepository.incrementUserCount();
        messagingTemplate.convertAndSend("/topic/user-number",userRepository.getUserCount());
        // send user list
        messagingTemplate.convertAndSend("/topic/user-list", userRepository.getAllUsers());
    }

    @EventListener
    public void onDisconnect(SessionDisconnectEvent event) {
        if(event.getUser() == null) return;
        String uuid = event.getUser().getName();
        userRepository.removeUser(UUID.fromString(uuid));
        userRepository.decrementUserCount();
        messagingTemplate.convertAndSend("/topic/user-number",userRepository.getUserCount());
        messagingTemplate.convertAndSend("/topic/user-list", userRepository.getAllUsers());
    }
}

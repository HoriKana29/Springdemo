package org.example.springdemo.Controller;

import lombok.RequiredArgsConstructor;
import org.example.springdemo.DTO.CreateMessage;
import org.example.springdemo.Repository.MessageRepository;
import org.example.springdemo.Repository.UserRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class WebsocketController {
    private final MessageRepository myMessageRepository;
    private final UserRepository myUserRepository;
    private final SimpMessageSendingOperations messagingTemplate;

    // /app/chat/message
    @MessageMapping("/chat/message")
    public void sendMessage(@Payload CreateMessage createMessage, Principal principal){
        boolean isOperationSuccess =
        myMessageRepository.sendMessage(createMessage.getUserID(), createMessage.getMessage());
        if(isOperationSuccess){
            // broadcast to topic/messages all users
            messagingTemplate.convertAndSend("/topic/messages", myMessageRepository.getMessages());
        }
        else{
            // unicast to user that send failed.
            messagingTemplate.convertAndSendToUser
                    (principal.getName(), "/queue/errors","Fail to send Message");
        }
    }
}

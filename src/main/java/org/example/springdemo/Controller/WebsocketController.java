package org.example.springdemo.Controller;

import lombok.RequiredArgsConstructor;
import org.example.springdemo.DTO.*;
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

    // /app/chat/delete-message
    @MessageMapping("/chat/delete-message")
    public void deleteMessage(@Payload DeleteMessage deleteMessage, Principal principal){

        boolean isOperationSuccess =
                myMessageRepository.deleteMessage(deleteMessage.getUserID(),deleteMessage.getMessageID());

        if(isOperationSuccess){
            // broadcast updated message list
            messagingTemplate.convertAndSend("/topic/messages", myMessageRepository.getMessages()
            );
        }
        else{
            // send error to the user who requested delete
            messagingTemplate.convertAndSendToUser(
                    principal.getName(),
                    "/queue/errors",
                    "Fail to delete message"
            );
        }
    }

    // /app/chat/edit-message
    @MessageMapping("/chat/edit-message")
    public void editMessage(@Payload EditMessage editMessage, Principal principal){

        boolean isOperationSuccess =
                myMessageRepository.editMessage(
                        editMessage.getUserID(),
                        editMessage.getMessageID(),
                        editMessage.getNewMessage()
                );

        if(isOperationSuccess){
            // broadcast updated messages
            messagingTemplate.convertAndSend("/topic/messages", myMessageRepository.getMessages()
            );
        }
        else{
            messagingTemplate.convertAndSendToUser(
                    principal.getName(),
                    "/queue/errors",
                    "Fail to edit message"
            );
        }
    }

    @MessageMapping("/chat/typing")
    public void typing(@Payload TypingMessage message){

        myUserRepository.setTyping(
                message.getUserID(),
                message.isTyping()
        );

        messagingTemplate.convertAndSend(
                "/topic/typing",
                myUserRepository.getTypingUsers()
        );
    }
}

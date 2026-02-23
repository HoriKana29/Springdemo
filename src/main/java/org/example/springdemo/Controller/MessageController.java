package org.example.springdemo.Controller;


import lombok.RequiredArgsConstructor;
import org.example.springdemo.DTO.CreateMessage;
import org.example.springdemo.Model.MessageModel;
import org.example.springdemo.Repository.MessageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;

    @GetMapping
    public List<MessageModel> getMessages() {
        return messageRepository.getMessages();
    }

    @PostMapping("/send")
    public boolean sendMessage(@RequestBody CreateMessage Message) {
        return messageRepository.sendMessage(Message.getUuid(),Message.getMessage());
    }

    @DeleteMapping("/{userID}/{messageID}")
    public boolean deleteMessage(@PathVariable UUID userID, @PathVariable UUID messageID) {
        return messageRepository.deleteMessage(messageID, userID);
    }

}

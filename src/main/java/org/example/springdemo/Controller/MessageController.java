package org.example.springdemo.Controller;


import lombok.RequiredArgsConstructor;
import org.example.springdemo.DTO.CreateMessage;
import org.example.springdemo.DTO.EditMessage;
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
    public String sendMessage(@RequestBody CreateMessage message) {
        boolean result =
                messageRepository.sendMessage(message.getUserID(), message.getMessage());

        return result ? "OK" : "FAIL";
    }

    @DeleteMapping("/{userID}/{messageID}")
    public boolean deleteMessage(@PathVariable UUID userID, @PathVariable UUID messageID) {
        return messageRepository.deleteMessage(userID, messageID);
    }

    @GetMapping("/search")
    public List<MessageModel> searchMessages(@RequestParam String filter) {
        return messageRepository.searchMessages(filter);
    }

    @PutMapping("/edit")
    public boolean editMessage(@RequestBody EditMessage request) {
        return messageRepository.editMessage(
                request.getUserID(),
                request.getMessageID(),
                request.getNewMessage()
        );
    }

}

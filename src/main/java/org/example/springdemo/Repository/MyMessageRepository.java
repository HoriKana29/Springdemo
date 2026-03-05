package org.example.springdemo.Repository;

import lombok.RequiredArgsConstructor;
import org.example.springdemo.Model.MessageModel;
import org.example.springdemo.Model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class MyMessageRepository implements MessageRepository {

    private final List<MessageModel> messages = new ArrayList<>();
    private final UserRepository userRepository;

    @Override
    public List<MessageModel> getMessages() {
        return messages;
    }

    @Override
    public boolean sendMessage(UUID userID, String message) {
        System.out.println("=== SEND MESSAGE CALLED ===");
        System.out.println("Incoming userID: " + userID);

        UserModel userModel = userRepository.getUserByID(userID);
        if(userModel == null) {
            System.out.println("USER NOT FOUND");
            return false;
        }
        MessageModel messageModel = new MessageModel(UUID.randomUUID(), userModel, message,false);
        messages.add(messageModel);
        System.out.println("Message added");
        return true;
    }

    @Override
    public boolean deleteMessage(UUID userID, UUID messageID) {
        UserModel userModel = userRepository.getUserByID(userID);
        if(userModel == null) return false;
        MessageModel messageModel = getMessageByID(messageID);
        if(messageModel == null) return false;
        messageModel.setDeleted(true);
        return true;
    }

    @Override
    public MessageModel getMessageByID(UUID id) {
        for(MessageModel messageModel : messages) {
            if(messageModel.getMessageID().equals(id)) {
                return messageModel;
            }
        }
        return null;
    }

    @Override
    public List<MessageModel> searchMessages(String filter) {
        List<MessageModel> result = new ArrayList<>();

        for (MessageModel message : messages) {
            if (!message.getDeleted() &&
                    message.getMessage().toLowerCase().contains(filter.toLowerCase())) {
                result.add(message);
            }
        }

        return result;
    }

    @Override
    public boolean editMessage(UUID userID, UUID messageID, String newMessage) {
        MessageModel message = getMessageByID(messageID);
        if (message == null) return false;

        if (!message.getUser().getUserID().equals(userID)) return false;

        if (message.getDeleted()) return false;

        message.setMessage(newMessage);
        return true;
    }

}

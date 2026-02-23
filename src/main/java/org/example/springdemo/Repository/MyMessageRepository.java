package org.example.springdemo.Repository;

import lombok.RequiredArgsConstructor;
import org.example.springdemo.Model.MessageModel;
import org.example.springdemo.Model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        UserModel userModel = userRepository.getUserByID(userID);
        if(userModel == null) return false;
        MessageModel messageModel = new MessageModel(UUID.randomUUID(), userModel, message);
        messages.add(messageModel);
        return true;
    }

    @Override
    public boolean deleteMessage(UUID userID, UUID messageID) {
        UserModel userModel = userRepository.getUserByID(userID);
        if(userModel == null) return false;
        MessageModel messageModel = getMessageByID(messageID);
        if(messageModel == null) return false;
        messages.remove(messageModel);
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
}

package org.example.springdemo.Repository;

import org.example.springdemo.Model.MessageModel;

import java.util.List;
import java.util.UUID;

public interface MessageRepository {
    List<MessageModel> getMessages();
    boolean sendMessage(UUID userID, String message);
    boolean deleteMessage(UUID userID, UUID messageID);
    MessageModel getMessageByID(UUID id);
    List<MessageModel> searchMessages(String filter);
    boolean editMessage(UUID userID, UUID messageID, String newMessage);

}

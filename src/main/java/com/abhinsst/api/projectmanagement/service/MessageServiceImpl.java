package com.abhinsst.api.projectmanagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhinsst.api.projectmanagement.model.Chat;
import com.abhinsst.api.projectmanagement.model.Message;
import com.abhinsst.api.projectmanagement.model.User;
import com.abhinsst.api.projectmanagement.repository.MessageRepository;
import com.abhinsst.api.projectmanagement.repository.UserRepository;

@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ProjectService projectService;

  @Override
  public Message sendMessage(Long senderId, Long chatId, String content) throws Exception {
    User sender = userRepository.findById(senderId)
        .orElseThrow(() -> new Exception("User not found with id:" + senderId));

    Chat chat = projectService.getProjectById(projectId).getChat();

    Message message = new Message();
    message.setContent(content);
    message.setSender(sender);
    message.setCreatedAt(LocalDateTime.now());
    message.setChat(chat);
    Message saveMessage = messageRepository.save(message);

    chat.getMessaeg().add(saveMessage);
    return saveMessage;
  }

  @Override
  public List<Message> getMessageByProjectId(Long projectId) throws Exception {

    Chat chat = projectService.GetChatByProjectId(projectId);
    List<Message> findByChatIdOrderByCreatedAtAsc = messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
    return findByChatIdOrderByCreatedAtAsc;

  }

}

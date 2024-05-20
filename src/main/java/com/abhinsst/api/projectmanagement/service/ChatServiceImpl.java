package com.abhinsst.api.projectmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhinsst.api.projectmanagement.model.Chat;
import com.abhinsst.api.projectmanagement.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {
  @Autowired
  private ChatRepository chatRepository;

  @Override
  public Chat createChat(Chat chat) {
    return chatRepository.save(chat);

  }

}

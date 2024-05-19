package com.abhinsst.api.projectmanagement.service;

import java.util.List;

import com.abhinsst.api.projectmanagement.model.Message;

public interface MessageService {

  Message sendMessage(Long senderId, Long chatId, String content) throws Exception;

  List<Message> getMessageByProjectId(Long projectId) throws Exception;

}

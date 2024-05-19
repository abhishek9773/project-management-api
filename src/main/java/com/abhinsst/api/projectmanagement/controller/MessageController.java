package com.abhinsst.api.projectmanagement.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhinsst.api.projectmanagement.model.Chat;
import com.abhinsst.api.projectmanagement.model.Message;
import com.abhinsst.api.projectmanagement.model.User;
import com.abhinsst.api.projectmanagement.request.CreateMessageRequest;
import com.abhinsst.api.projectmanagement.service.MessageService;
import com.abhinsst.api.projectmanagement.service.ProjectService;
import com.abhinsst.api.projectmanagement.service.ProjectServiceImp;
import com.abhinsst.api.projectmanagement.service.UserService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  @Autowired
  private MessageService messageService;

  @Autowired
  private UserService userService;

  @Autowired
  private ProjectService projectService;

  @PostMapping("/send")
  public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request) throws Exception {

    User user = userService.findUserById(request.getSenderId());
    Chat chats = projectService.getProjectById(request.getProjectId()).getChat();

    if (user == null)
      throw new Exception("Chats not found");
    Message sentMessage = messageService.sendMessage(request.getSenderId(), request.getProjectId(),
        request.getContent());

    return ResponseEntity.ok(sentMessage);
  }

  @GetMapping("/chat/{projectid}")
  public ResponseEntity<List<Message>> getMessageByChatId(@PathVariable Long projectId) throws Exception {

    List<Message> message = messageService.getMessageByProjectId(projectId);
    return ResponseEntity.ok(message);

  }

}

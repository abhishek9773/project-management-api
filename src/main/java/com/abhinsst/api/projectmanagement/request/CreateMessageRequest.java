package com.abhinsst.api.projectmanagement.request;

import lombok.Data;

@Data
public class CreateMessageRequest {
  private Long senderId;
  private String content;
  private Long projectId;

}

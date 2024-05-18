package com.abhinsst.api.projectmanagement.model;

import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String content;
  private LocalDateTime createdAt;

  @ManyToOne
  private Chat chat;

  @ManyToOne
  private User sender;

}

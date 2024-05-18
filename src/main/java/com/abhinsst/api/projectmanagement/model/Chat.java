package com.abhinsst.api.projectmanagement.model;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.patterns.PerObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @OneToOne
  private Project project;

  @JsonIgnore
  @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Message> messaeg;

  private List<User> users = new ArrayList<>();

}

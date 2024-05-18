package com.abhinsst.api.projectmanagement.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
  /**3 ** */

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String fullName;
  private String email;
  private String password;

  @JsonIgnore
  @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
  private List<Issue> assignedIssues = new ArrayList<>();

  private int projectSize;

}

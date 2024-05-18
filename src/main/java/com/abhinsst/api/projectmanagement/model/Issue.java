package com.abhinsst.api.projectmanagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Issue {
  /** 4 */

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;
  private String description;
  private String status;
  private Long projectID;
  private String priority;
  private LocalDate dueDate;
  private List<String> tags = new ArrayList<>();

  @ManyToOne
  private User assignee;

  @JsonIgnore
  @ManyToOne
  private Project project;

  private List<Comments> comments = new ArrayList<>();

}

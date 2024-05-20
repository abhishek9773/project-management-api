package com.abhinsst.api.projectmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhinsst.api.projectmanagement.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {
  public List<Issue> findByProjectId(Long id);

}

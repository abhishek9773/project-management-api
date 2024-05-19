package com.abhinsst.api.projectmanagement.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.jdbc.Expectations;

import com.abhinsst.api.projectmanagement.model.Issue;
import com.abhinsst.api.projectmanagement.model.User;
import com.abhinsst.api.projectmanagement.request.IssueRequest;

public interface IssueService {

  Issue getIssueById(Long issueId) throws Exception;

  List<Issue> getIssueByProjectId(Long projectId) throws Exception;

  Issue createIssue(IssueRequest issue, User tokenUser) throws Exception;

  void deleteIssue(Long issueId, Long userid) throws Exception;

  Issue addUserToIssue(Long issueId, Long userId) throws Exception;

  Issue updateIssueStatus(Long issueId, String status) throws Exception;

}

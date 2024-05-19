package com.abhinsst.api.projectmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhinsst.api.projectmanagement.model.Issue;
import com.abhinsst.api.projectmanagement.model.Project;
import com.abhinsst.api.projectmanagement.model.User;
import com.abhinsst.api.projectmanagement.repository.IssueRepository;
import com.abhinsst.api.projectmanagement.request.IssueRequest;

@Service
public class IssueServiceImpl implements IssueService {

  @Autowired
  private IssueRepository issueRepository;

  @Autowired
  private ProjectService projectService;

  @Autowired
  private UserService userService;

  @Override
  public Issue getIssueById(Long issueId) throws Exception {
    Optional<Issue> issue = issueRepository.findById(issueId);
    if (issue.isPresent()) {
      return issue.get();

    }
    throw new Exception("No issues found wiht issueId" + issueId);
  }

  @Override
  public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
    return issueRepository.findByProjectID(projectId);

  }

  @Override
  public Issue createIssue(IssueRequest issueRequest, User user) throws Exception {
    Project project = projectService.getProjectById(issueRequest.getProjectId());

    Issue issue = new Issue();
    issue.setTitle(issueRequest.getTitle());
    issue.setDescription(issueRequest.getDescription());
    issue.setStatus(issueRequest.getStatus());
    issue.setProjectID(issue.getProjectID());
    issue.setPriority(issueRequest.getPriority());
    issue.setDueDate(issueRequest.getDueDate());

    issue.setProject(project);

    return issueRepository.save(issue);
  }

  @Override
  public void deleteIssue(Long issueId, Long userid) throws Exception {
    getIssueById(issueId);
    issueRepository.deleteById(issueId);
  }

  @Override
  public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
    User user = userService.findUserById(userId);
    Issue issue = getIssueById(issueId);
    issue.setAssignee(user);
    return issueRepository.save(issue);

  }

  @Override
  public Issue updateIssueStatus(Long issueId, String status) throws Exception {
    Issue issue = getIssueById(issueId);
    issue.setStatus(status);
    return issueRepository.save(issue);
  }

}

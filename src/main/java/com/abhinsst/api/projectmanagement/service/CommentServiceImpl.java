package com.abhinsst.api.projectmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhinsst.api.projectmanagement.model.Comments;
import com.abhinsst.api.projectmanagement.model.Issue;
import com.abhinsst.api.projectmanagement.model.User;
import com.abhinsst.api.projectmanagement.repository.CommentRepository;
import com.abhinsst.api.projectmanagement.repository.IssueRepository;
import com.abhinsst.api.projectmanagement.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private IssueRepository issueRepository;
  @Autowired
  private UserRepository userRepository;

  @Override
  public Comments createComment(Long issueId, Long userId, String content) {
    Optional<Issue> issueOptional = issueRepository.findById(issueId);
    Optional<User> userOptional = userRepository.findById(userId);

    if (issueOptional.isEmpty()) {
      throw new Exception("issue not found with id" + issueId);
    }
    if (userOptional.isEmpty()) {
      throw new Exception("user not found with id" + userId);
    }

    Issue issue = issueOptional.get();
    User user = userOptional.get();

    Comments comment = new Comments();

    comment.setIssue(issue);
    comment.setUser(user);
    comment.setCreatedDateTime(LocalDateTime.now());
    comment.setContent(content);

    Comments savedComment = commentRepository.save(comment);
    issue.getComments().add(savedComment);
    return savedComment;

  }

  @Override
  public void deleteComment(Long commentId, Long userId) throws Exception {

    Optional<Comments> commentOptional = commentRepository.findById(commentId);
    Optional<User> userOptional = userRepository.findById(userId);

    if (commentOptional.isEmpty()) {
      throw new Excpetion("comment not round with id" + commentId);

    }
    if (userOptional.isEmpty()) {
      throw new Exception("user not found with id ", +userId);
    }

    Comments comment = new Comments();
    User user = userOptional.get();

    if (comment.getUser().equals(user)) {
      commentRepository.delete(comment);
    } else {
      throw new Exception("User does not have permission to delete this comment!");
    }

  }

  @Override
  public List<Comments> findCommentByIssueId(Long issueId) {
    return commentRepository.findByIssueId(issueId);
  }

}

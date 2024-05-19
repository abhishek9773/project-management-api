package com.abhinsst.api.projectmanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhinsst.api.projectmanagement.model.Comments;
import com.abhinsst.api.projectmanagement.model.User;
import com.abhinsst.api.projectmanagement.request.CreateCommentRequest;
import com.abhinsst.api.projectmanagement.response.MessageResponse;
import com.abhinsst.api.projectmanagement.service.CommentService;
import com.abhinsst.api.projectmanagement.service.UserService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

  private CommentService commentService;

  private UserService userService;

  @PostMapping()
  public ResponseEntity<Comments> createComment(@RequestBody CreateCommentRequest req,
      @RequestHeader("Authorization") String jwt) throws Exception {

    User user = userService.findUserProfileByJwt(jwt);

    Comments createdComments = commentService.createComment(req.getIssueId(), user.getId(), req.getContent());
    return new ResponseEntity<>(createdComments, HttpStatus.CREATED);

  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId, @RequestHeader("Authorization") String jwt) throws Exception(){
    User user = userService.findUserProfileByJwt(jwt);
    commentService.deleteComment(commentId, user.getId());
    MessageResponse res = new MessageResponse();
    res.setMessage("Connect deleted successfully");
    return new ResponseEntity<>(res, HttpStatus.OK);

  }

  @GetMapping("/{issueId}")
  public ResponseEntity<List<Comments>> getCommentByIssueId(@PathVariable Long isueId) {
    List<Comments> comments = commentService.findCommentByIssueId(isueId);
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }

}

package com.abhinsst.api.projectmanagement.service;

import java.util.List;

import com.abhinsst.api.projectmanagement.model.Comments;

public interface CommentService {

  Comments createComment(Long issueId, Long userId, String comment);

  void deleteComment(Long commentId, Long userId) throws Exception;

  List<Comments> findCommentByIssueId(Long issueId);

}

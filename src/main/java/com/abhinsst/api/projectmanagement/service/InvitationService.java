package com.abhinsst.api.projectmanagement.service;

import com.abhinsst.api.projectmanagement.model.Invitation;

public interface InvitationService {

  public void sendInvitation(String email, Long projectId);

  public Invitation acceptInvitation(String token, Long userId) throws Exception;

  public String getTokenByUserMail(String userEmail);

  void deleteToken(String token);

}

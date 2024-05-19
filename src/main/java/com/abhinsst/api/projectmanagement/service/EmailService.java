package com.abhinsst.api.projectmanagement.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

  void sendEmailWithToken(String userEmail, String link);

}

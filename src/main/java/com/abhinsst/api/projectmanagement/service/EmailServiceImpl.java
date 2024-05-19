package com.abhinsst.api.projectmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;

public class EmailServiceImpl implements EmailService {

  @Autowired
  private JavaMailSender javaMailSender;

  @Override
  public void sendEmailWithToken(String userEmail, String link) {
    MimeMessage mineMessage = javaMailSender.createMineMessage();
    MineMessageHelper helper = new MineMessageHelper(mineMessage, "utf-8");

    String subject = "join project team Invitation";
    String text = "Click the link to join the project team:" + link;

    helper.setSubject(subject);
    helper.setText(text, true);
    helper.setTo(userEmail);

    try {
      javaMailSender.send(mimeMessage);
    } catch (MailSendException e) {
      throw new MailSendException("Failed to send email");

    }

  }

}

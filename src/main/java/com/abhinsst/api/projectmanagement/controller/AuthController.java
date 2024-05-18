package com.abhinsst.api.projectmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhinsst.api.projectmanagement.repository.UserRepository;
import com.abhinsst.api.projectmanagement.service.CustomeUserDetailsImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private CustomeUserDetailsImpl customeUserDetailsImpl;

}

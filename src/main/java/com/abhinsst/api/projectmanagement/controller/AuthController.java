package com.abhinsst.api.projectmanagement.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhinsst.api.projectmanagement.config.JwtProvider;
import com.abhinsst.api.projectmanagement.model.User;
import com.abhinsst.api.projectmanagement.repository.UserRepository;
import com.abhinsst.api.projectmanagement.request.LoginRequest;
import com.abhinsst.api.projectmanagement.response.AuthResponse;
import com.abhinsst.api.projectmanagement.service.CustomeUserDetailsImpl;
import java.lang.Exception;

@RestController
@RequestMapping("/auth")
public class AuthController {
  /* 6** */
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private CustomeUserDetailsImpl customeUserDetailsImpl;

  @PostMapping("/signup")
  public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
    User isUserExist = userRepository.findByEmail(user.getEmail());

    if (isUserExist != null) {
      throw new Exception("Email already exist please try with another one ");
    }

    User createdUser = new User();
    createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
    createdUser.setEmail(user.getEmail());
    createdUser.setFullName(user.getFullName());

    User saveUser = userRepository.save(createdUser);

    Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = JwtProvider.generateToken(authentication);

    AuthResponse res = new AuthResponse();
    res.setMessage("signup success");
    res.setJwt(jwt);

    return new ResponseEntity<>(res, HttpStatus.CREATED);

  }

  @PostMapping("/signin")
  public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {

    String username = loginRequest.getEmail();
    String password = loginRequest.getPassword();

    Authentication authentication = authenticated(username, password);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = JwtProvider.generateToken(authentication);

    AuthResponse res = new AuthResponse();
    res.setMessage("signup success");
    res.setJwt(jwt);

    return new ResponseEntity<>(res, HttpStatus.CREATED);

  }

  private Authentication authenticated(String username, String password) {
    UserDetails userDetails = customUserDetails.loadUserByUsername(username);
    if (userDetails == null) {
      throw new BadCredentialsException("invalid username or password");
    }
    if(!passwordEncoder.matches((password.userDetails.getPassword())){
      throw new BadCredentialsException("Invalid password");
    }
    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }
}

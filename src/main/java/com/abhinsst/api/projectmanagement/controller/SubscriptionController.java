package com.abhinsst.api.projectmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhinsst.api.projectmanagement.model.PlanType;
import com.abhinsst.api.projectmanagement.model.Subscription;
import com.abhinsst.api.projectmanagement.model.User;
import com.abhinsst.api.projectmanagement.service.SubscriptionService;
import com.abhinsst.api.projectmanagement.service.UserService;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
  @Autowired
  private SubscriptionService subscriptionService;

  @Autowired
  private UserService userService;

  @GetMapping("/user")
  public ResponseEntity<Subscription> getUserSubscription(@RequestHeader("Authorization") String jwt) {
    User user = userService.findUserProfileByJwt(jwt);

    Subscription subscription = subscriptionService.getUserSubscription(user.getId());
    return new ResponseEntity<>(subscription, HttpStatus.OK);
  }

  @PatchMapping("/upgrade")
  public ResponseEntity<Subscription> upgradeSubscription(@RequestHeader("Authorization") String jwt,
      @RequestParam PlanType planeType) throws Exception {
    User user = userService.findUserProfileByJwt(jwt);

    Subscription subscription = subscriptionService.upgradeSubscription(user.getId(), planeType);
    return new ResponseEntity<>(subscription, HttpStatus.OK);

  }

}

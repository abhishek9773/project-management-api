package com.abhinsst.api.projectmanagement.service;

import com.abhinsst.api.projectmanagement.model.PlanType;
import com.abhinsst.api.projectmanagement.model.Subscription;
import com.abhinsst.api.projectmanagement.model.User;

public interface SubscriptionService {

  Subscription createSubscription(User user);

  Subscription getUserSubscription(Long userId) throws Exception;

  Subscription upgradeSubscription(Long userId, PlanType planeType);

  boolean isValid(Subscription subscription);

}

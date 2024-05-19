package com.abhinsst.api.projectmanagement.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhinsst.api.projectmanagement.model.PlanType;
import com.abhinsst.api.projectmanagement.model.Subscription;
import com.abhinsst.api.projectmanagement.model.User;
import com.abhinsst.api.projectmanagement.repository.SubscriptionRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

  @Autowired
  private UserService userService;

  @Autowired
  private SubscriptionRepository subscriptionRepository;

  @Override
  public Subscription createSubscription(User user) {
    Subscription subscription = new Subscription();
    subscription.setUser(user);
    subscription.setSubscriptionStartDate(LocalDate.now());
    subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
    subscription.setValid(true);
    subscription.setPlaneType(PlanType.FREE);

    return subscriptionRepository.save(subscription);
  }

  @Override
  public Subscription getUserSubscription(Long userId) throws Exception {

    return subscriptionRepository.findByUserId(userId);
  }

  @Override
  public Subscription upgradeSubscription(Long userId, PlanType planeType) {
    Subscription subscription = subscriptionRepository.findByUserId(userId);
    subscription.setPlaneType(planeType);
    subscription.setSubscriptionStartDate(LocalDate.now());
    if (planeType.equals(planeType.ANNUALLY)) {
      subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));

    } else {
      subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(1));

    }
    return subscriptionRepository.save(subscription);
  }

  @Override
  public boolean isValid(Subscription subscription) {
    return;
  }

}

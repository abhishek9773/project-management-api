package com.abhinsst.api.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhinsst.api.projectmanagement.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

  Subscription findByUserId(Long userId);

}

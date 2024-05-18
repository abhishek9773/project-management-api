package com.abhinsst.api.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhinsst.api.projectmanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  /** 5 */

  User findByEmail(String email);

}

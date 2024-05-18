package com.abhinsst.api.projectmanagement.service;

import com.abhinsst.api.projectmanagement.model.User;

public interface UserService {

  User findUserProfileByJwt(String jwt) throws Exception;

  User findUserByEmail(String email) throws Exception;

  User findUserById(Long userId) throws Exception;

  User updateUserProjectSize(User user, int number);

}

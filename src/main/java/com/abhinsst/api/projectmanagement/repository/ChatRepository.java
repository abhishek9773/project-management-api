package com.abhinsst.api.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhinsst.api.projectmanagement.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}

package com.abhinsst.api.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhinsst.api.projectmanagement.model.Invitation;
import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

  Invitation findByToken(String token);

  Invitation findByEmail(String userEmail);

}

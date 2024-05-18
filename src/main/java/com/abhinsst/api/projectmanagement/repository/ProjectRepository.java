package com.abhinsst.api.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abhinsst.api.projectmanagement.model.Project;
import com.abhinsst.api.projectmanagement.model.User;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

  // List<Project> findByOwner(User owner);

  List<Project> findByNameContainingAndTeamContains(String partialName, User user);

  // @Query("SELECT p From Project p join p.team t where t=:user")
  // List<Project> findProjectByTeam(@Param("user") User user);

  List<Project> findByTeamContainingOrOwner(User user, User owner);

}

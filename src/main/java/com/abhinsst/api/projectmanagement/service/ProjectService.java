package com.abhinsst.api.projectmanagement.service;

import java.util.List;

import com.abhinsst.api.projectmanagement.model.Chat;
import com.abhinsst.api.projectmanagement.model.Project;
import com.abhinsst.api.projectmanagement.model.User;

public interface ProjectService {

  Project createProject(Project project, User user) throws Exception;

  List<Project> getProjectByTeam(User user, String category, String tag) throws Exception;

  Project getProjectById(Long projectId) throws Exception;

  void deleteProject(Long projectId, Long userId) throws Exception;

  Project updateProject(Project updatedProject, Long id) throws Exception;

  void addUserToProject(Long projectId, Long userId) throws Exception;

  void removeUserFromProject(Long projectId, Long userId) throws Exception;

  Chat GetChatByProjectId(Long projectId) throws Exception;

}

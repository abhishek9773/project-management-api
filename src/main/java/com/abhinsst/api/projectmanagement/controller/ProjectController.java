package com.abhinsst.api.projectmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhinsst.api.projectmanagement.model.Project;
import com.abhinsst.api.projectmanagement.model.User;
import com.abhinsst.api.projectmanagement.service.ProjectService;
import com.abhinsst.api.projectmanagement.service.UserService;

@RestController
@RequestMapping("/api/projects/")
public class ProjectController {
  @Autowired
  private ProjectService projectService;

  @Autowired
  private UserService userService;

  public ResponseEntity<List<Project>> getProjects(
      @RequestParam(required = false) String category,
      @RequestParam(required = false) String tag,
      @RequestHeader("Authorization") String jwt

  ) {

    User user = userService.findUserProfileByJwt(jwt);
    List<Project> projects = projectService.getProjectByTeam(user, category, tag);
    return null;

    return new ResponseEntity<>(projects, HttpStatus.OK);
  }

}

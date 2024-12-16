package com.challenge.bci.users.controller;

import com.challenge.bci.users.dto.UserRequest;
import com.challenge.bci.users.entity.User;
import com.challenge.bci.users.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @ApiResponse(responseCode = "201", description = "Operación exitosa",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = User.class)
      ))
  @PostMapping
  public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest userRequest){
    var user = userService.registerUser(userRequest);
    //return new ResponseEntity<>(user, HttpStatus.CREATED);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }
}

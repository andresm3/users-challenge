package com.challenge.bci.users.controller;

import com.challenge.bci.users.dto.UserRequest;
import com.challenge.bci.users.entity.Customer;
import com.challenge.bci.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * Description method for example: This is method for query vehicle and orbelite proxy
   *
   * @return MetadataResponse
   */

  @PostMapping(value ="/create")
  public ResponseEntity<Customer> createUser(@Valid @RequestBody UserRequest userRequest){
    var user = userService.registerUser(userRequest);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}

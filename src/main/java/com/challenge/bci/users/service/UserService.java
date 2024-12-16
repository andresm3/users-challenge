package com.challenge.bci.users.service;

import com.challenge.bci.users.dto.UserRequest;
import com.challenge.bci.users.entity.User;
import java.util.List;

public interface UserService {

  List<User> getUsers();

  User registerUser(UserRequest userRequest);
}

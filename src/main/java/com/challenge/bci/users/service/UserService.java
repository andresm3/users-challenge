package com.challenge.bci.users.service;

import com.challenge.bci.users.dto.UserRequest;
import com.challenge.bci.users.entity.Customer;
import java.util.List;

public interface UserService {

  List<Customer> getUsers();

  Customer registerUser(UserRequest userRequest);
}

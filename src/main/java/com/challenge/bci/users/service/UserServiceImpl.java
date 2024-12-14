package com.challenge.bci.users.service;

import com.challenge.bci.users.dto.UserRequest;
import com.challenge.bci.users.entity.Customer;
import com.challenge.bci.users.exceptions.CustomInformationException;
import com.challenge.bci.users.entity.Phone;
import com.challenge.bci.users.repository.UserRepository;
import com.challenge.bci.users.utils.Validations;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

  @Autowired
  UserRepository repository;

  public List<Customer> getUsers() {
    return repository.findAll();
  }

  public Customer registerUser(UserRequest userRequest){
    try {
      log.info("Trama de userRequest: {}", userRequest);
      if(!Validations.validateCreateUser(userRequest)){
        throw new CustomInformationException("Email not valid");
      }
      var userFound = repository.findByEmail(userRequest.getEmail());

      if(userFound != null){
        throw new CustomInformationException("User exists");
      }
      var userCreated = repository.save(fromDtoToModel(userRequest));
      return userCreated;
    }catch (Exception e){
      throw new CustomInformationException("Error: " + e.getMessage());
    }
  }

  private Customer fromDtoToModel(UserRequest userRequest){
    Customer customer = new Customer();
    customer.setName(userRequest.getName());
    customer.setEmail(userRequest.getEmail());
    customer.setPassword(userRequest.getPassword());

    List<Phone> phoneList = userRequest.getPhoneList().stream()
        .map(e -> new Phone(e))
        .collect(Collectors.toList());

    customer.setPhoneList(phoneList);
    return customer;
  }
}

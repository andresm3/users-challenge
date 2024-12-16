package com.challenge.bci.users.service;

import com.challenge.bci.users.dto.UserRequest;
import com.challenge.bci.users.entity.User;
import com.challenge.bci.users.exceptions.CustomInformationException;
import com.challenge.bci.users.entity.Phone;
import com.challenge.bci.users.repository.UserRepository;
import com.challenge.bci.users.utils.Validations;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

  @Autowired
  UserRepository repository;

  @Value("${app.password.regex}")
  private String passwordRegex;
  /*This regex will enforce these rules:
  At least one upper case English letter, (?=.*?[A-Z])
  At least one lower case English letter, (?=.*?[a-z])
  At least one digit, (?=.*?[0-9])
  At least one special character, (?=.*?[#?!@$%^&*-])
  Minimum eight in length .{8,} (with the anchors)*/

  public List<User> getUsers() {
    return repository.findAll();
  }

  public User registerUser(UserRequest userRequest){
    try {
      log.info("Trama de userRequest: {}", userRequest);
      if(!Validations.validateUserEmail(userRequest)){
        throw new CustomInformationException("Email ingresado no es valido.");
      }

      if(!this.checkUserPasswordCompliance(userRequest.getPassword())){
        throw new CustomInformationException("Password ingresado no cumple requerimiento minimo.");
      }

      var userFound = repository.findByEmail(userRequest.getEmail());
      if(userFound != null){
        throw new CustomInformationException("El correo ya esta registrado.");
      }
      var userCreated = repository.save(fromDtoToEntity(userRequest));
      return userCreated;
    }catch (Exception e){
      throw new CustomInformationException(e.getMessage());
    }
  }

  private User fromDtoToEntity(UserRequest userRequest){
    User user = new User();
    user.setName(userRequest.getName());
    user.setEmail(userRequest.getEmail());
    user.setPassword(userRequest.getPassword());
    user.setUuid(UUID.randomUUID().toString());

    List<Phone> phoneList = userRequest.getPhoneList().stream()
        .map(e -> {
          Phone phone = new Phone(e);
          phone.setUser(user);
          return phone;
        })
        .collect(Collectors.toList());

    user.setPhoneList(phoneList);
    user.setCreated(new Date());
    user.setModified(new Date());
    user.setActive(true);
    return user;
  }

  public boolean checkUserPasswordCompliance(String password){
    return Validations.regexValidator(passwordRegex, password);
  }
}

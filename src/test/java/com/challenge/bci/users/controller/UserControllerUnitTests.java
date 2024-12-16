package com.challenge.bci.users.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.challenge.bci.users.dto.PhoneRequest;
import com.challenge.bci.users.dto.UserRequest;
import com.challenge.bci.users.entity.Phone;
import com.challenge.bci.users.entity.User;
import com.challenge.bci.users.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@Slf4j
@WebMvcTest(UserController.class)
public class UserControllerUnitTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Autowired
  private ObjectMapper objectMapper;

  UserRequest request;
  User response;
  @BeforeEach
  public void setup(){
    PhoneRequest phoneRequest = PhoneRequest.builder()
        .number("987987987")
        .countryCode("51")
        .cityCode("11")
        .build();
    Phone phone = Phone.builder()
        .number("987987987")
        .countryCode("51")
        .cityCode("11")
        .build();
    List<PhoneRequest> phoneRequestList = List.of(phoneRequest);
    List<Phone> phoneList = List.of(phone);

    request = UserRequest.builder()
        .name("Lolo")
        .email("lolo@crema.pe")
        .password("Univer$itar10")
        .phoneList(phoneRequestList)
        .build();

    response = new User();
    response.setUuid("1234-1234-1324");
    response.setName("Lolo");
    response.setEmail("lolo@crema.pe");
    response.setPassword("Univer$itar10");
    response.setPhoneList(phoneList);

  }
  @Test
  void createUserTest() throws Exception{


    when(userService.registerUser(request)).thenReturn(response);

    mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(response)))
        .andExpect(status().isCreated());

    //verify(userService, times(1)).registerUser(request);
  }
}

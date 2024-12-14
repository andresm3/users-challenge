package com.challenge.bci.users.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;

@Data
public class UserRequest {

  @JsonProperty("name")
  @NotBlank(message = "Field name must be required")
  private String name;

  @JsonProperty("email")
  @NotBlank(message = "Field email must be required")
  private String email;

  @JsonProperty("password")
  @NotBlank(message = "Field password must be required")
  private String password;

  @JsonProperty("phones")
  List<PhoneRequest> phoneList;
}

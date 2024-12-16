package com.challenge.bci.users.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class UserRequest {

  @JsonProperty("name")
  @NotBlank(message = "Campo name es obligatorio")
  private String name;

  @JsonProperty("email")
  @NotBlank(message = "Campo email es obligatorio")
  private String email;

  @JsonProperty("password")
  @NotBlank(message = "Campo password es obligatorio")
  private String password;

  @JsonProperty("phones")
  @Valid
  @NotNull(message = "Campo phones es obligatorio")
  List<PhoneRequest> phoneList;
}

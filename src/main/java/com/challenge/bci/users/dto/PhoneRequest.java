package com.challenge.bci.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PhoneRequest {
  @JsonProperty("number")
  @Size(min = 9, max = 14)
  @NotBlank(message = "Campo number es obligatorio")
  private String number;
  @JsonProperty("citycode")
  @Size(min = 1, max = 3)
  @NotBlank(message = "Campo cityCode es obligatorio")
  private String cityCode;
  @JsonProperty("countrycode")
  @Size(min = 1, max = 3)
  @NotBlank(message = "Campo countryCode es obligatorio")
  private String countryCode;
}

package com.challenge.bci.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PhoneRequest {
  @JsonProperty("number")
  @NotBlank(message = "Field number must be required")
  private String number;
  @JsonProperty("citycode")
  @NotBlank(message = "Field citycode must be required")
  private String cityCode;
  @JsonProperty("countrycode")
  @NotBlank(message = "Field countrycode must be required")
  private String countryCode;
}

package com.challenge.bci.users.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {

  Map<String, String> mensaje;
}

package com.challenge.bci.users.utils;

import com.challenge.bci.users.dto.UserRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;

@Data
public class Validations {

  public static boolean validateCreateUser(UserRequest userRequest){
    return regexValidator(Constants.EMAIL, userRequest.getEmail());
  }

  public static boolean regexValidator(String regex, String value) {

    Boolean match = false;
    String responseMessage = "";

    Matcher regexMatcher = Pattern.compile(regex).matcher(value);
    if (regexMatcher.matches()) {
      match = true;
    }

    return match;
  }
}

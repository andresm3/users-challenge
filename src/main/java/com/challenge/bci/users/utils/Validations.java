package com.challenge.bci.users.utils;

import com.challenge.bci.users.dto.UserRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;

@Data
public class Validations {

  public static boolean validateUserEmail(UserRequest userRequest){
    return regexValidator(Constants.EMAIL, userRequest.getEmail());
  }

  public static boolean validateUserPhone(String number){
    return regexValidator(Constants.PHONE_NUMBER, number);
  }


  public static boolean regexValidator(String regex, String value) {

    Boolean match = false;

    Matcher regexMatcher = Pattern.compile(regex).matcher(value);
    if (regexMatcher.matches()) {
      match = true;
    }

    return match;
  }
}

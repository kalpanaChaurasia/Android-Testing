package com.rajeshbatth.android_testing.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: Rajesh Batth
 * Date: 18-Jun-2015.
 */
public final class Misc {

  private Misc() {
  }

  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  public static boolean validateEmail(final String email) {
    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }
}

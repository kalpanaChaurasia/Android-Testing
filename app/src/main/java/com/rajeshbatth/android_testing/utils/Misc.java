package com.rajeshbatth.android_testing.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: Rajesh Batth
 * Date: 18-Jun-2015.
 */
public class Misc {

  private static final Pattern EMAIL_PTRN = Pattern.compile(
      "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

  public static boolean validateEmailAddress(String email) {
    Matcher mtch = EMAIL_PTRN.matcher(email);
    return mtch.matches();
  }

  public static boolean isTextEmpty(String s) {
    return s == null || s.length() == 0;
  }
}

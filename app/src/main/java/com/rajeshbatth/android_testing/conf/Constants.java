package com.rajeshbatth.android_testing.conf;

import com.rajeshbatth.android_testing.BuildConfig;

/**
 * Contains the configuration variables of the project.
 */
public class Constants {

  /**
   * Duration for which SplashActivity will apear and then launch next activity.
   * For Debug builds it is 1 second and for others its 3 seconds
   */
  public static final int SPLASH_DURATION = BuildConfig.DEBUG ? 1000 : 3000;

  /**
   * Contains all the urls used in this application.
   */
  public static class Urls {
    public static final String API_HOST = "http://beta.json-generator.com";
  }

  /**
   * Holds the keys of all preference key names.
   */
  public static class Prefs {
    public static final String NAME = "android_testing_pref";
    public static final String USER_SIGNED_IN = "user_signed_in";
  }
}

package com.rajeshbatth.android_testing.account;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.rajeshbatth.android_testing.conf.Constants;
import com.rajeshbatth.android_testing.model.User;
import javax.inject.Inject;

/**
 * Author: Rajesh Batth
 * Date: 20-Jun-2015.
 */

public class AccountsManager {

  final SharedPreferences sharedPreferences;
  private User currentUser;

  @Inject public AccountsManager(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  public boolean isUserLoggedIn() {
    return sharedPreferences.getBoolean(Constants.Prefs.USER_SIGNED_IN, false);
  }

  public void logout() {
    sharedPreferences.edit().remove(Constants.Prefs.USER_SIGNED_IN).apply();
    currentUser = null;
  }

  public User getCurrentUser() {
    return currentUser;
  }

  public void onUserLoggedIn(@NonNull User currentUser) {
    sharedPreferences.edit().remove(Constants.Prefs.USER_SIGNED_IN).apply();
    this.currentUser = currentUser;
  }
}

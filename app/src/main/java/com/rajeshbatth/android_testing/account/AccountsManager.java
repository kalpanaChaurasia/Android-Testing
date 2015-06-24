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

  final SharedPreferences mSharedPreferences;
  private User mCurrentUser;

  @Inject public AccountsManager(SharedPreferences sharedPreferences) {
    mSharedPreferences = sharedPreferences;
  }

  public boolean isUserLoggedIn() {
    return mSharedPreferences.getBoolean(Constants.Prefs.USER_SIGNED_IN, false);
  }

  public void logout() {
    mSharedPreferences.edit().remove(Constants.Prefs.USER_SIGNED_IN).apply();
    mCurrentUser = null;
  }

  public User getCurrentUser() {
    return mCurrentUser;
  }

  public void onUserLoggedIn(@NonNull User currentUser) {
    mSharedPreferences.edit().remove(Constants.Prefs.USER_SIGNED_IN).apply();
    mCurrentUser = currentUser;
  }
}

package com.rajeshbatth.android_testing.account;

import com.rajeshbatth.android_testing.conf.Constants;
import com.rajeshbatth.android_testing.model.User;

import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.Module;

/**
 * Author: Rajesh Batth
 * Date: 20-Jun-2015.
 */

public class AccountsManager {

    final SharedPreferences mSharedPreferences;

    @Inject
    public AccountsManager(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    private User mCurrentUser;


    public boolean isUserLoggedIn() {
        return mSharedPreferences.getBoolean(Constants.Prefs.USER_SIGNED_IN, false);
    }

    public void logout() {
        mSharedPreferences.edit().remove(Constants.Prefs.USER_SIGNED_IN).apply();
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        mCurrentUser = currentUser;
    }

}

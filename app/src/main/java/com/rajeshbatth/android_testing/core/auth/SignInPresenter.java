package com.rajeshbatth.android_testing.core.auth;

import android.support.annotation.NonNull;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.api.AccountsApi;
import com.rajeshbatth.android_testing.model.User;
import com.rajeshbatth.android_testing.model.http.AuthResponse;
import com.rajeshbatth.android_testing.model.http.UserRequestParams;
import com.rajeshbatth.android_testing.utils.Misc;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by user on 6/27/2015.
 */
public class SignInPresenter {

  private final SignInCallbacks callbacks;
  private final AccountsApi accountsApi;
  private final AccountsManager accountsManager;

  SignInPresenter(@NonNull AccountsManager accountsManager, @NonNull AccountsApi accountsApi,
      @NonNull SignInCallbacks callbacks) {
    this.callbacks = callbacks;
    this.accountsApi = accountsApi;
    this.accountsManager = accountsManager;
  }

  public boolean validate() {
    callbacks.hideErrors();
    String email = callbacks.getEmail();
    if (Misc.isTextEmpty(email)) {
      callbacks.showEmptyEmailError();
      return false;
    }

    if (!Misc.validateEmailAddress(email)) {
      callbacks.showInvalidEmailError();
      return false;
    }

    String password = callbacks.getPassword();
    if (Misc.isTextEmpty(password)) {
      callbacks.showEmptyPasswordError();
      return false;
    }

    if (password.length() < SignInActivity.PASSWORD_MIN_LENGTH) {
      callbacks.showPasswordMinError();
      return false;
    }

    return true;
  }

  public void submit() {
    if (!validate()) {
      return;
    }
    UserRequestParams params = new UserRequestParams();
    params.setEmail(callbacks.getEmail());
    accountsApi.login(params, new Callback<AuthResponse>() {
      @Override
      public void success(AuthResponse authResponse, Response response) {
        onUserAuthenticated();
      }

      @Override
      public void failure(RetrofitError error) {
        onUserAuthenticated();
      }
    });
  }

  private void onUserAuthenticated() {
    User currentUser = new User();
    currentUser.setEmail(callbacks.getEmail());
    accountsManager.setActiveUser(currentUser);
    callbacks.launchHome();
  }

  interface SignInCallbacks {
    String getEmail();

    String getPassword();

    void hideErrors();

    void showEmptyEmailError();

    void showInvalidEmailError();

    void showEmptyPasswordError();

    void showPasswordMinError();

    void launchHome();
  }
}

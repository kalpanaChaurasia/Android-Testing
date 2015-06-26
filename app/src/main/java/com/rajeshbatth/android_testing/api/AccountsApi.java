package com.rajeshbatth.android_testing.api;

import com.rajeshbatth.android_testing.model.http.AuthResponse;
import com.rajeshbatth.android_testing.model.http.UserRequestParams;
import retrofit.Callback;
import retrofit.http.POST;

/**
 * Author: Rajesh Batth
 * Date: 20-Jun-2015.
 */
public interface AccountsApi {

  @POST("")
  void register(UserRequestParams params);

  @POST("")
  void login(UserRequestParams params, Callback<AuthResponse> responseCallback);
}

package com.rajeshbatth.android_testing.di.module;

import com.rajeshbatth.android_testing.api.AccountsApi;
import com.rajeshbatth.android_testing.api.HomeApi;
import com.rajeshbatth.android_testing.di.scope.PerActivity;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by rajesh.j on 6/19/2015.
 */
@Module
public class ApiModule {

  @PerActivity
  @Provides
  public HomeApi provideHomeApi(RestAdapter restAdapter) {
    return restAdapter.create(HomeApi.class);
  }

  @PerActivity
  @Provides
  public AccountsApi provideAccountsApi(RestAdapter restAdapter) {
    return restAdapter.create(AccountsApi.class);
  }
}

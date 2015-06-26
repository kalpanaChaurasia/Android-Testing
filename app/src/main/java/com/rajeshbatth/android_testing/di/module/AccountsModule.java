package com.rajeshbatth.android_testing.di.module;

import android.content.SharedPreferences;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.di.scope.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Author: Rajesh Batth
 * Date: 20-Jun-2015.
 */
@Module
public class AccountsModule {

  @PerActivity
  @Provides
  AccountsManager provideAccountsManager(SharedPreferences sharedPreferences) {
    return new AccountsManager(sharedPreferences);
  }
}

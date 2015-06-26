package com.rajeshbatth.android_testing.di.module;

import com.rajeshbatth.android_testing.account.AccountsManager;
import dagger.Module;
import dagger.Provides;
import org.mockito.Mockito;

/**
 * Author: Rajesh Batth
 * Date: 21-Jun-2015.
 */
@Module
public class TestAccountsModule {
  @Provides
  public AccountsManager provideAccountsManager() {
    return Mockito.mock(AccountsManager.class);
  }
}

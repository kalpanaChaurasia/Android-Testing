package com.rajeshbatth.android_testing.di.components;

import android.content.Context;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.api.AccountsApi;
import com.rajeshbatth.android_testing.di.module.AccountsModule;
import com.rajeshbatth.android_testing.di.module.NetworkModule;
import com.rajeshbatth.android_testing.di.scope.PerActivity;
import com.rajeshbatth.android_testing.ui.SignInActivity;
import com.rajeshbatth.android_testing.ui.SignUpActivity;
import dagger.Component;

/**
 * Author: Rajesh Batth
 * Date: 20-Jun-2015.
 */
@PerActivity @Component(dependencies = { ApplicationComponent.class }, modules = {
    NetworkModule.class, AccountsModule.class
}) public interface AccountsComponent {

  AccountsApi provideAccountsApi();

  AccountsManager provideAccountsManager();

  void inject(SignInActivity signInActivity);

  void inject(SignUpActivity signUpActivity);

  class Injector {

    static AccountsComponent sAccountsComponent;

    public static AccountsComponent getAccountsComponent(Context context) {
      if (sAccountsComponent == null) {
        sAccountsComponent = DaggerAccountsComponent.builder()
            .applicationComponent(ApplicationComponent.Injector.getApplicationComponent(context))
            .build();
      }
      return sAccountsComponent;
    }

    public static void setAccountsComponent(AccountsComponent splashComponent) {
      sAccountsComponent = splashComponent;
    }
  }
}

package com.rajeshbatth.android_testing.core.auth;

import android.content.Context;
import com.rajeshbatth.android_testing.BaseApplication;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.api.AccountsApi;
import com.rajeshbatth.android_testing.core.ApplicationComponent;
import com.rajeshbatth.android_testing.di.module.AccountsModule;
import com.rajeshbatth.android_testing.di.module.ApiModule;
import com.rajeshbatth.android_testing.di.scope.PerActivity;
import dagger.Component;
import java.lang.ref.WeakReference;

/**
 * Author: Rajesh Batth
 * Date: 20-Jun-2015.
 */
@PerActivity
@Component(dependencies = { ApplicationComponent.class }, modules = {
    ApiModule.class, AccountsModule.class
})
public interface AccountsComponent {

  AccountsApi provideAccountsApi();

  AccountsManager provideAccountsManager();

  void inject(SignInActivity signInActivity);

  void inject(SignUpActivity signUpActivity);

  class Holder {

    private static WeakReference<AccountsComponent> accountsComponentRef;

    public static AccountsComponent getAccountsComponent(Context context) {
      if (accountsComponentRef == null || accountsComponentRef.get() == null) {
        AccountsComponent accountsComponent = DaggerAccountsComponent.builder()
            .applicationComponent(BaseApplication.applicationComponent(context))
            .build();
        accountsComponentRef = new WeakReference<>(accountsComponent);
      }
      return accountsComponentRef.get();
    }

    public static void setAccountsComponent(AccountsComponent splashComponent) {
      accountsComponentRef = new WeakReference<>(splashComponent);
    }
  }
}

package com.rajeshbatth.android_testing.di.components;

import android.content.Context;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.di.module.AccountsModule;
import com.rajeshbatth.android_testing.di.scope.PerActivity;
import com.rajeshbatth.android_testing.ui.SplashActivity;
import dagger.Component;

/**
 * Author: Rajesh Batth
 * Date: 19-Jun-2015.
 */
@PerActivity
@Component(dependencies = { ApplicationComponent.class }, modules = { AccountsModule.class })
public interface SplashComponent {

  AccountsManager provideAccountsManager();

  void inject(SplashActivity splashActivity);

  class Injector {

    static SplashComponent sSplashComponent;

    public static SplashComponent getSplashComponent(Context context) {
      if (sSplashComponent == null) {
        sSplashComponent = DaggerSplashComponent.builder()
            .applicationComponent(ApplicationComponent.Injector.getApplicationComponent(context))
            .build();
      }
      return sSplashComponent;
    }

    public static void setSplashComponent(SplashComponent splashComponent) {
      sSplashComponent = splashComponent;
    }
  }
}

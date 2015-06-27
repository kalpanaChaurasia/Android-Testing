package com.rajeshbatth.android_testing.core.splash;

import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.core.ApplicationComponent;
import com.rajeshbatth.android_testing.di.module.TestAccountsModule;
import com.rajeshbatth.android_testing.di.scope.PerActivity;
import dagger.Component;

/**
 * Author: Rajesh Batth
 * Date: 19-Jun-2015.
 */
@PerActivity
@Component(dependencies = { ApplicationComponent.class }, modules = { TestAccountsModule.class })
public interface TestSplashComponent extends SplashComponent {

  AccountsManager provideAccountsManager();

  void inject(SplashActivityTest splashActivityTest);
}

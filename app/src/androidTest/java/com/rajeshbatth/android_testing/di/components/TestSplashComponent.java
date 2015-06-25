package com.rajeshbatth.android_testing.di.components;

import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.di.module.TestAccountsModule;
import com.rajeshbatth.android_testing.di.scope.PerActivity;
import com.rajeshbatth.android_testing.ui.SplashActivityTest;
import dagger.Component;

/**
 * Author: Rajesh Batth
 * Date: 19-Jun-2015.
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {TestAccountsModule.class})
public interface TestSplashComponent extends SplashComponent {

    AccountsManager provideAccountsManager();

    void inject(SplashActivityTest splashActivityTest);

}

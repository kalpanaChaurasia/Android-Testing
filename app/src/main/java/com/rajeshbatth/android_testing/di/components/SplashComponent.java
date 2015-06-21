package com.rajeshbatth.android_testing.di.components;

import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.di.module.AccountsModule;
import com.rajeshbatth.android_testing.di.module.AndroidModule;
import com.rajeshbatth.android_testing.ui.SplashActivity;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: Rajesh Batth
 * Date: 19-Jun-2015.
 */
@Singleton
@Component(dependencies = {AndroidModule.class}, modules = {AccountsModule.class})
public interface SplashComponent {

    AccountsManager provideAccountsManager();

    void inject(SplashActivity splashActivity);

    class Injector {

        static SplashComponent sSplashComponent;

        public static SplashComponent getSplashComponent(Context context) {
            if (sSplashComponent == null) {
                sSplashComponent = DaggerSplashComponent.builder()
                        .androidModule(new AndroidModule(context))
                        .build();
            }
            return sSplashComponent;
        }

        public static void setSplashComponent(SplashComponent splashComponent) {
            sSplashComponent = splashComponent;
        }
    }
}

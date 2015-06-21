package com.rajeshbatth.android_testing.di.components;

import com.rajeshbatth.android_testing.di.module.AndroidModule;
import com.rajeshbatth.android_testing.ui.SplashActivity;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: Rajesh Batth
 * Date: 19-Jun-2015.
 */
@Singleton
@Component(modules = {AndroidModule.class})
public interface SplashComponent {

    SharedPreferences provideSharedPreferences();

    void inject(SplashActivity splashActivity);
    void inject(SplashComponent splashComponent);

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

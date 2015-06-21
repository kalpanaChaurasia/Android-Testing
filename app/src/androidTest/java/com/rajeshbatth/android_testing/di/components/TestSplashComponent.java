package com.rajeshbatth.android_testing.di.components;

import com.rajeshbatth.android_testing.di.module.TestAndroidModule;


import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: Rajesh Batth
 * Date: 19-Jun-2015.
 */
@Singleton
@Component(modules = {TestAndroidModule.class})
public interface TestSplashComponent extends SplashComponent {
    SharedPreferences provideSharedPreferences();


}

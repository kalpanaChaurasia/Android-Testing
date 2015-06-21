package com.rajeshbatth.android_testing.di.module;

import org.mockito.Mockito;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Rajesh Batth
 * Date: 19-Jun-2015.
 */
@Module
public class TestAndroidModule {

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(){
        return Mockito.mock(SharedPreferences.class);
    }
}

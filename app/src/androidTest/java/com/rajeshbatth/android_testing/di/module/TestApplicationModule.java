package com.rajeshbatth.android_testing.di.module;

import android.content.SharedPreferences;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rajesh.j on 6/24/2015.
 */
@Module
public class TestApplicationModule {

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return Mockito.mock(SharedPreferences.class);
    }

}

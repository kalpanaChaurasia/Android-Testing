package com.rajeshbatth.android_testing.di.module;

import com.rajeshbatth.android_testing.conf.Constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Rajesh Batth
 * Date: 19-Jun-2015.
 */
@Module
public class AndroidModule {

    final Context mAppContext;

    public AndroidModule(@NonNull Context appContext) {
        mAppContext = appContext;
    }

    @Provides
    @Singleton
    public Context provideAppContext() {
        return mAppContext;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return mAppContext.getSharedPreferences(Constants.Prefs.NAME, Context.MODE_PRIVATE);
    }

}

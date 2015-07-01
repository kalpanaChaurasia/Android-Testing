package com.rajeshbatth.android_testing.di.module;

import android.support.v7.app.AppCompatActivity;
import com.rajeshbatth.android_testing.di.scope.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Author: Rajesh Batth
 * Date: 24-Jun-2015.
 */
@Module
public class ActivityModule {

  private final AppCompatActivity activity;

  public ActivityModule(AppCompatActivity appCompatActivity) {
    activity = appCompatActivity;
  }

  @Provides
  @PerActivity
  public AppCompatActivity provideAppCompatActivity() {
    return activity;
  }
}

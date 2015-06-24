package com.rajeshbatth.android_testing.di.components;

import android.content.Context;
import com.rajeshbatth.android_testing.api.HomeApi;
import com.rajeshbatth.android_testing.di.module.NetworkModule;
import com.rajeshbatth.android_testing.di.scope.PerActivity;
import com.rajeshbatth.android_testing.ui.HomeActivity;
import dagger.Component;

/**
 * Created by rajesh.j on 6/19/2015.
 */
@PerActivity
@Component(dependencies = { ApplicationComponent.class }, modules = { NetworkModule.class })
public interface HomeComponent {
  HomeApi provideHomeApi();

  void inject(HomeActivity homeActivity);

  class Injector {

    static HomeComponent sHomeComponent;

    public static HomeComponent getHomeComponent(Context context) {
      if (sHomeComponent == null) {
        sHomeComponent = DaggerHomeComponent.builder()
            .applicationComponent(ApplicationComponent.Injector.getApplicationComponent(context))
            .build();
      }
      return sHomeComponent;
    }

    public static void setHomeComponent(HomeComponent homeComponent) {
      sHomeComponent = homeComponent;
    }
  }
}

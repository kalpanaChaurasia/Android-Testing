package com.rajeshbatth.android_testing.di.components;

import android.content.Context;
import android.content.SharedPreferences;
import com.rajeshbatth.android_testing.BaseApplication;
import com.rajeshbatth.android_testing.di.module.ApplicationModule;
import com.squareup.okhttp.OkHttpClient;
import dagger.Component;
import javax.inject.Singleton;
import retrofit.RestAdapter;

/**
 * Created by rajesh.j on 6/24/2015.
 */
@Singleton @Component(modules = { ApplicationModule.class }) public interface ApplicationComponent {

  Context provideAppContext();

  SharedPreferences provideSharedPreferences();

  OkHttpClient provideHttpClient();

  RestAdapter provideRestAdapter();

  void inject(BaseApplication baseApplication);

  class Holder {
    private static ApplicationComponent sApplicationComponent;

    public static ApplicationComponent getApplicationComponent(Context context) {
      if (sApplicationComponent == null) {
        sApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(context))
            .build();
      }
      return sApplicationComponent;
    }

    public static void setApplicationComponent(ApplicationComponent applicationComponent) {
      sApplicationComponent = applicationComponent;
    }
  }
}

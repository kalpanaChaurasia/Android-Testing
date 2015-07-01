package com.rajeshbatth.android_testing.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.rajeshbatth.android_testing.conf.Constants;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Author: Rajesh Batth
 * Date: 19-Jun-2015.
 */
@Module
public class ApplicationModule {

  final Context appContext;

  public ApplicationModule(@NonNull Context appContext) {
    this.appContext = appContext;
  }

  @Provides
  @Singleton
  public Context provideAppContext() {
    return appContext;
  }

  @Provides
  @Singleton
  public SharedPreferences provideSharedPreferences() {
    return appContext.getSharedPreferences(Constants.Prefs.NAME, Context.MODE_PRIVATE);
  }

  @Singleton
  @Provides
  public OkHttpClient provideHttpClient() {
    return new OkHttpClient();
  }

  @Singleton
  @Provides
  public RestAdapter provideRestAdapter(OkHttpClient httpClient) {
    return new RestAdapter.Builder().setClient(new OkClient(httpClient))
        .setEndpoint(Constants.Urls.API_HOST)
        .build();
  }
}

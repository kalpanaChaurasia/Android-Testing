package com.rajeshbatth.android_testing.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.rajeshbatth.android_testing.conf.Constants;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.mockito.Mockito;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by rajesh.j on 6/24/2015.
 */
@Module
public class TestApplicationModule {

  final Context appContext;

  public TestApplicationModule(@NonNull Context appContext) {
    this.appContext = appContext.getApplicationContext();
  }

  @Provides
  @Singleton
  public Context provideAppContext() {
    return appContext;
  }

  @Provides
  @Singleton
  public SharedPreferences provideSharedPreferences() {
    return Mockito.mock(SharedPreferences.class);
  }

  @Singleton
  @Provides
  public OkHttpClient provideHttpClient() {
    OkHttpClient client = new OkHttpClient();
    client.networkInterceptors().add(new StethoInterceptor());
    return client;
  }

  @Singleton
  @Provides
  public RestAdapter provideRestAdapter(OkHttpClient httpClient) {
    return new RestAdapter.Builder().setClient(new OkClient(httpClient))
        .setEndpoint(Constants.Urls.API_HOST)
        .build();
  }
}

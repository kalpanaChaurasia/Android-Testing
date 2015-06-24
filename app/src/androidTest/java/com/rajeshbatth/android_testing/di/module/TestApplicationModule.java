package com.rajeshbatth.android_testing.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.rajeshbatth.android_testing.conf.Constants;
import com.squareup.okhttp.OkHttpClient;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by rajesh.j on 6/24/2015.
 */
@Module
public class TestApplicationModule {

    final Context mAppContext;

    public TestApplicationModule(@NonNull Context appContext) {
        mAppContext = appContext.getApplicationContext();
    }

    @Provides
    @Singleton
    public Context provideAppContext() {
        return mAppContext;
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
        return new RestAdapter.Builder()
                .setClient(new OkClient(httpClient))
                .setEndpoint(Constants.Urls.API_HOST)
                .build();
    }

}

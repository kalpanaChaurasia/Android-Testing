package com.rajeshbatth.android_testing.di.module;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.rajeshbatth.android_testing.api.AccountsApi;
import com.rajeshbatth.android_testing.api.HomeApi;
import com.rajeshbatth.android_testing.conf.Constants;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by rajesh.j on 6/19/2015.
 */
@Module
public class NetworkModule {


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

    @Provides
    public HomeApi provideHomeApi(RestAdapter restAdapter) {
        return restAdapter.create(HomeApi.class);
    }

    @Provides
    public AccountsApi provideAccountsApi(RestAdapter restAdapter) {
        return restAdapter.create(AccountsApi.class);
    }
}

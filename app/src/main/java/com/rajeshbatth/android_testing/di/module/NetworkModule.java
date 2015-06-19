package com.rajeshbatth.android_testing.di.module;

import com.rajeshbatth.android_testing.api.HomeApi;
import com.rajeshbatth.android_testing.conf.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by rajesh.j on 6/19/2015.
 */
@Module
public class NetworkModule {
    @Singleton
    @Provides
    public RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder().setEndpoint(Constants.API_HOST).build();
    }

    @Provides
    public HomeApi provideHomeApi(RestAdapter restAdapter) {
        return restAdapter.create(HomeApi.class);
    }
}

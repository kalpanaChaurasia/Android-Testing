package com.rajeshbatth.android_testing.di.module;

import com.rajeshbatth.android_testing.api.HomeApi;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by rajesh.j on 6/19/2015.
 */
@Module
public class TestNetworkModule {

    @Singleton
    @Provides
    public HomeApi provideHomeApi(RestAdapter restAdapter) {
        return Mockito.mock(HomeApi.class);
    }
}

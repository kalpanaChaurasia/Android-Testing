package com.rajeshbatth.android_testing.di.module;

import com.rajeshbatth.android_testing.api.HomeApi;
import com.rajeshbatth.android_testing.conf.Constants;

import org.mockito.Mockito;

import android.os.AsyncTask;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.android.MainThreadExecutor;

/**
 * Created by rajesh.j on 6/19/2015.
 */
@Module
public class TestNetworkModule {

    @Singleton
    @Provides
    public RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(Constants.API_HOST)
                .setExecutors(AsyncTask.THREAD_POOL_EXECUTOR, new MainThreadExecutor())
                .build();
    }

    @Singleton
    @Provides
    public HomeApi provideHomeApi(RestAdapter restAdapter) {
        return Mockito.mock(HomeApi.class);
    }
}

package com.rajeshbatth.android_testing.di.module;

import com.rajeshbatth.android_testing.api.HomeApi;
import com.rajeshbatth.android_testing.di.scope.PerActivity;
import dagger.Module;
import dagger.Provides;
import org.mockito.Mockito;
import retrofit.RestAdapter;

/**
 * Created by rajesh.j on 6/19/2015.
 */
@Module
public class TestNetworkModule {

    @PerActivity
    @Provides
    public HomeApi provideHomeApi(RestAdapter restAdapter) {
        return Mockito.mock(HomeApi.class);
    }
}

package com.rajeshbatth.android_testing.di.components;

import com.rajeshbatth.android_testing.api.HomeApi;
import com.rajeshbatth.android_testing.di.module.NetworkModule;
import com.rajeshbatth.android_testing.ui.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rajesh.j on 6/19/2015.
 */
@Singleton
@Component(modules = {NetworkModule.class})
public interface HomeComponent {
    HomeApi provideHomeApi();

    void inject(HomeActivity homeActivity);
}

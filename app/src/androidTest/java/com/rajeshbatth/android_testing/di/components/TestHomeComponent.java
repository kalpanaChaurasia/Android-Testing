package com.rajeshbatth.android_testing.di.components;

import com.rajeshbatth.android_testing.di.module.TestNetworkModule;
import com.rajeshbatth.android_testing.ui.HomeActivityTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rajesh.j on 6/19/2015.
 */
@Singleton
@Component(modules = {TestNetworkModule.class})
public interface TestHomeComponent extends HomeComponent {
    void inject(HomeActivityTest homeActivity);
}

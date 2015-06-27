package com.rajeshbatth.android_testing.core.home;

import com.rajeshbatth.android_testing.core.TestApplicationComponent;
import com.rajeshbatth.android_testing.di.module.TestNetworkModule;
import com.rajeshbatth.android_testing.di.scope.PerActivity;
import dagger.Component;

/**
 * Created by rajesh.j on 6/19/2015.
 */
@PerActivity
@Component(dependencies = { TestApplicationComponent.class }, modules = { TestNetworkModule.class })
public interface TestHomeComponent extends HomeComponent {
  void inject(HomeActivityTest homeActivity);
}

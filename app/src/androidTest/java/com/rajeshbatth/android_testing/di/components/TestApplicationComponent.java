package com.rajeshbatth.android_testing.di.components;

import com.rajeshbatth.android_testing.di.module.TestApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rajesh.j on 6/24/2015.
 */
@Singleton
@Component(modules = {TestApplicationModule.class})
public interface TestApplicationComponent extends ApplicationComponent {

}

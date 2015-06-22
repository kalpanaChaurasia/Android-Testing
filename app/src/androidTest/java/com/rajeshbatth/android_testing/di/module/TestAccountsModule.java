package com.rajeshbatth.android_testing.di.module;

import com.rajeshbatth.android_testing.account.AccountsManager;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Rajesh Batth
 * Date: 21-Jun-2015.
 */
@Module
public class TestAccountsModule {
    @Singleton
    @Provides
    public AccountsManager provideAccountsManager(){
        return Mockito.mock(AccountsManager.class);
    }
}

package com.rajeshbatth.android_testing.di.module;

import android.content.SharedPreferences;

import com.rajeshbatth.android_testing.account.AccountsManager;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Rajesh Batth
 * Date: 20-Jun-2015.
 */
@Module
public class AccountsModule {

    @Provides
    AccountsManager provideAccountsManager(SharedPreferences sharedPreferences) {
        return new AccountsManager(sharedPreferences);
    }
}

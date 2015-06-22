package com.rajeshbatth.android_testing.di.module;

import com.rajeshbatth.android_testing.account.AccountsManager;

import android.content.SharedPreferences;

import dagger.Module;

/**
 * Author: Rajesh Batth
 * Date: 20-Jun-2015.
 */
@Module
public class AccountsModule {

    AccountsManager provideAccountsManager(SharedPreferences sharedPreferences) {
        return new AccountsManager(sharedPreferences);
    }
}

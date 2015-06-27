package com.rajeshbatth.android_testing.core.splash;

import android.support.annotation.NonNull;
import com.rajeshbatth.android_testing.account.AccountsManager;

/**
 * Created by user on 6/27/2015.
 */
public class SplashPresenter {

  final AccountsManager accountsManager;
  final SplashCallBacks splashCallBacks;

  public SplashPresenter(@NonNull AccountsManager manager, @NonNull SplashCallBacks callBacks) {
    this.accountsManager = manager;
    this.splashCallBacks = callBacks;
  }

  void launchNext() {
    if (accountsManager.isUserLoggedIn()) {
      splashCallBacks.launchHome();
    } else {
      splashCallBacks.launchAuth();
    }
  }

  public interface SplashCallBacks {
    void launchHome();

    void launchAuth();
  }
}

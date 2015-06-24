package com.rajeshbatth.android_testing.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import butterknife.ButterKnife;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.conf.Constants;
import com.rajeshbatth.android_testing.di.components.SplashComponent;
import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

  @Inject AccountsManager mAccountsManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    ButterKnife.inject(this);
    SplashComponent.Injector.getSplashComponent(this).inject(this);
    launchWithDelay();
  }

  void launchWithDelay() {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        launchNextActivity();
      }
    }, Constants.SPLASH_DURATION);
  }

  private void launchNextActivity() {
    finish();
    if (mAccountsManager.isUserLoggedIn()) {
      startActivity(new Intent(SplashActivity.this, HomeActivity.class));
    } else {
      startActivity(new Intent(SplashActivity.this, SignInActivity.class));
    }
  }
}

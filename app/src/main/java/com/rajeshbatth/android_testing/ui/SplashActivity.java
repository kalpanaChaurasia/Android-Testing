package com.rajeshbatth.android_testing.ui;

import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.di.components.SplashComponent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {

    @Inject
    AccountsManager mAccountsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        injectDependency();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchNextActivity();
            }
        }, 2000);
    }

    private void injectDependency() {
        SplashComponent.Injector.getSplashComponent(this).inject(this);
    }

    private void launchNextActivity() {
        if (mAccountsManager.isUserLoggedIn()) {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, SignInActivity.class));
        }
    }


}

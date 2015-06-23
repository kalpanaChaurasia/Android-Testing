package com.rajeshbatth.android_testing.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.utils.TaskListener;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    public static final String TAG = "SplashActivityLog";
    /**
     * Used for testing, to check whether tests should be idle wait while app is doing some tasks.
     */
    protected TaskListener mTaskListener;
    AccountsManager mAccountsManager;
    private boolean mIsRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        Log.d(TAG, "onCreate");
        launchWithDelay();
    }

    void launchWithDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchNextActivity();
            }
        }, 5000);
    }


    private void launchNextActivity() {
        if (mAccountsManager == null) {
            SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
            mAccountsManager = new AccountsManager(sharedPreferences);
        }
        finish();
        if (mAccountsManager.isUserLoggedIn()) {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, SignInActivity.class));
        }
    }

}

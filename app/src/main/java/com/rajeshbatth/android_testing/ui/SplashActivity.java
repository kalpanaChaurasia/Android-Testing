package com.rajeshbatth.android_testing.ui;

import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.account.AccountsManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "SplashActivityLog";

    AccountsManager mAccountsManager;

    /**
     * Used for testing, to check whether tests should be idle wait while app is doing some tasks.
     */
    protected TaskListener mTaskListener;

    private boolean mIsRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        Log.d(TAG, "onCreate");
        setTaskRunning(true);
//        launchWithDelay();
    }

    @OnClick(R.id.splash_msg)
    void launchWithDelay() {
        setTaskRunning(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchNextActivity();
                setTaskRunning(false);
            }
        }, 5000);
    }


    public void setTaskListener(TaskListener taskListener) {
        mTaskListener = taskListener;
        if (mIsRunning && mTaskListener != null) {
            mTaskListener.onTaskStarted();
        }
    }

    public void setTaskRunning(boolean isRunning) {
        if (mTaskListener != null) {
            mIsRunning = isRunning;
            if (mIsRunning) {
                mTaskListener.onTaskStarted();
            } else {
                mTaskListener.onTaskEnded();
            }
        }
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

    public interface TaskListener {

        void onTaskStarted();

        void onTaskEnded();
    }

}

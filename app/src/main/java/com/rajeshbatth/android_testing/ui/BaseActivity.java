package com.rajeshbatth.android_testing.ui;


import com.rajeshbatth.android_testing.ui.SplashActivity.TaskListener;

import android.support.v7.app.AppCompatActivity;

/**
 * Author: Rajesh Batth
 * Date: 21-Jun-2015.
 */
public class BaseActivity extends AppCompatActivity {
    protected TaskListener mTaskListener;

    private boolean mIsRunning;

    public void setTaskListener(TaskListener taskListener) {
        mTaskListener = taskListener;
        if (mIsRunning && mTaskListener!=null) {
            mTaskListener.onTaskStarted();
        }
    }

    public void setTaskRunning(boolean isRunning){
        if (mTaskListener != null) {
            mIsRunning = isRunning;
            if (mIsRunning) {
                mTaskListener.onTaskStarted();
            }else {
                mTaskListener.onTaskEnded();
            }
        }
    }
}

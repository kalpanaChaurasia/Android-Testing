package com.rajeshbatth.android_testing;

import android.support.test.espresso.IdlingResource;
import android.util.Log;

import com.rajeshbatth.android_testing.ui.SplashActivity;
import com.rajeshbatth.android_testing.utils.TaskListener;

/**
 * Author: Rajesh Batth
 * Date: 21-Jun-2015.
 */
public class MyIdlingResource implements IdlingResource, TaskListener {

    private boolean mIsTaskRunning;

    private ResourceCallback mResourceCallback;

    @Override
    public String getName() {
        return "My Idling Resource";
    }

    @Override
    public boolean isIdleNow() {
        return !mIsTaskRunning;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        mResourceCallback = resourceCallback;
    }

    @Override
    public void onTaskStarted() {
        mIsTaskRunning = true;
        Log.d(SplashActivity.TAG, "onTaskStarted");
    }

    @Override
    public void onTaskEnded() {
        Log.d(SplashActivity.TAG, "onTaskEnded");
        mIsTaskRunning = false;
        if (mResourceCallback != null) {
            mResourceCallback.onTransitionToIdle();
        }
    }
}

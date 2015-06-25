package com.rajeshbatth.android_testing;

import android.support.test.espresso.IdlingResource;
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
    }

    @Override
    public void onTaskEnded() {
        mIsTaskRunning = false;
        if (mResourceCallback != null) {
            mResourceCallback.onTransitionToIdle();
        }
    }
}
